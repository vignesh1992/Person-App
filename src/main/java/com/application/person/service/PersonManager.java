package com.application.person.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.person.entity.PersonEntity;
import com.application.person.exception.PersonNotFoundException;
import com.application.person.model.Person;
import com.application.person.repository.PersonRepository;

@Service
public class PersonManager {

	private PersonRepository personRepository;

	@Autowired
	public PersonManager(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public List<Person> findAllPeople() {
		List<PersonEntity> peopleList = personRepository.findAll();

		if (!peopleList.isEmpty()) {
			return peopleList.stream().map(e -> e.toPerson()).collect(Collectors.toList());
		} else {
			return new ArrayList<Person>();
		}
	}

	public Person fetchPersonById(Long id) {
		Optional<PersonEntity> personEntity = personRepository.findById(id);

		if (personEntity.isPresent()) {
			return personEntity.get().toPerson();
		} else {
			throw new PersonNotFoundException("No person found for this id");
		}
	}

	public Person createPerson(Person person) {
		PersonEntity save = personRepository.save(person.toPersonEntity());
		return save.toPerson();
	}

	public Person updatePerson(Long id, @Valid Person person) {

		Optional<PersonEntity> personValue = personRepository.findById(id);

		if (personValue.isPresent()) {
			PersonEntity entity = person.toPersonEntity();
			entity.setId(id);
			PersonEntity save = personRepository.save(entity);
			return save.toPerson();
		} else {
			throw new PersonNotFoundException("No person found for this id");
		}
	}

	public Boolean deletePerson(Long id) {
		Optional<PersonEntity> personValue = personRepository.findById(id);

		if (personValue.isPresent()) {
			personRepository.deleteById(id);
			return true;
		} else {
			throw new PersonNotFoundException("No person found for this id");
		}
	}

	public Boolean deletePeople() {
		personRepository.deleteAll();
		return true;
	}

	public List<Person> fetchByCustomQuery(String firstName, String lastName, Integer age, String favouriteColour) {

		List<Person> queryResult = personRepository.findByCustomQuery(firstName, lastName, age, favouriteColour);

		if (!queryResult.isEmpty()) {
			return queryResult;
		}
		return new ArrayList<>();
	}
}
