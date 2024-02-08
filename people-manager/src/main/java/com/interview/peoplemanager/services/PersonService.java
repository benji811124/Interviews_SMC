package com.interview.peoplemanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.interview.peoplemanager.entities.Person;
import com.interview.peoplemanager.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public List<Person> getAllPeople() {

		return personRepository.findAll();
	}

	public Optional<Person> getPersonById(Integer id) throws NotFoundException {
		Optional<Person> retrievePerson = personRepository.findById(id);

		return retrievePerson;

	}

	public Person savePerson(Person person) {

		return personRepository.save(person);
	}

	public Person updatePerson(int id ,Person person) throws NotFoundException {

		person.setId(id);
		return personRepository.save(person);
	}

	public void deletePerson(Integer id) {

		personRepository.deleteById(id);

	}

}
