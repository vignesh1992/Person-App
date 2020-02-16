package com.application.person.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.application.person.entity.PersonEntity;
import com.application.person.model.Person;
import com.application.person.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonManagerTest {

	@Autowired
	PersonRepository personRepository;

	private PersonManager personManager;

	@Before
	public void setup() {
		personManager = new PersonManager(personRepository);
	}

	@Test
	@Transactional
	public void findAllPeople_returnsAllPersons() {
		PersonEntity personEntity = getPersonEntity();

		personRepository.save(personEntity);

		List<Person> personList = personManager.findAllPeople();
		assertEquals(personList.size(), 1);
		assertEquals(personList.get(0).getFirstName(), "user1");
	}

	@Test
	@Transactional
	public void findPersonByFirstName_returnsAPerson() {
		PersonEntity personEntity = getPersonEntity();

		personRepository.save(personEntity);

		List<Person> person = personManager.fetchByCustomQuery("user1", null, null, null);
		assertEquals(person.get(0).getFirstName(), "user1");
	}

	@Test
	@Transactional
	public void createPerson_createsPersonInRepository() {
		Person person = personManager.createPerson(getPersonEntity().toPerson());
		assertEquals(person.getLastName(), "last");
	}

	@Test
	@Transactional
	public void deleteAll_deletesAllPersonsInRepositoryAndReturnsTrue() {
		PersonEntity personEntity = getPersonEntity();
		personRepository.save(personEntity);

		assertTrue(personManager.deletePeople());
	}

	private PersonEntity getPersonEntity() {
		return PersonEntity.builder().age(26).first_name("user1").last_name("last").favourite_colour("red").build();
	}
}