package com.interview.peoplemanager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.peoplemanager.entities.Person;
import com.interview.peoplemanager.services.PersonService;

@RestController
@RequestMapping(value = "/people/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {
	@Autowired
	private PersonService personService;

	@PostMapping()
	public ResponseEntity<Person> createPerson(@RequestBody final Person person) {

		return ResponseEntity.status(HttpStatus.CREATED).body(person);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable(value = "id", required = true) final Integer id)
			throws NotFoundException {
		Optional<Person> personFound = personService.getPersonById(id);
		if (personFound.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(personFound.get());
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable(value = "id", required = true) final int id,
			@RequestBody final Person person) throws NotFoundException {
		Optional<Person> personFound = personService.getPersonById(id);
		if (personFound.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			personService.updatePerson(id, person);
			personFound = personService.getPersonById(id);
			return ResponseEntity.status(HttpStatus.OK).body(personFound.get());
		}
	}

	@GetMapping()
	public ResponseEntity<List<Person>> getAllPeople() {
		List<Person> people = personService.getAllPeople();
		return ResponseEntity.ok(people);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletePerson(@PathVariable("id") Integer id) {
		personService.deletePerson(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
