package com.application.person.repository;

import java.util.List;

import com.application.person.model.Person;

public interface PersonRepositoryCustom {

	List<Person> findByCustomQuery(String firstName, String lastName, Integer age,
			String favouriteColour);

}
