package com.interview.peoplemanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interview.peoplemanager.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>  {

}
