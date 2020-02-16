package com.application.person.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.person.model.Person;
import com.application.person.service.PersonManager;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/people")
public class PersonController {

	@Autowired
	private PersonManager personManager;

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "To Add a person", notes = "Create a person - accessible only to user with ADMIN role", httpMethod = "POST", authorizations = {
			@Authorization(value = "bearerToken") })
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		Person personResult = personManager.createPerson(person);
		return ResponseEntity.status(HttpStatus.CREATED).body(personResult);
	}

	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@ApiOperation(value = "To Find a person", notes = "Find a particular person - accessible only to user with USER as well as ADMIN role", httpMethod = "GET", authorizations = {
			@Authorization(value = "bearerToken") })
	public ResponseEntity<Person> findPersonById(@PathVariable long id) {
		Person person = personManager.fetchPersonById(id);
		return ResponseEntity.ok(person);
	}

	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@ApiOperation(value = "To get the details of the Person", notes = "Get details of a particular person - accessible to both user with USER as well as ADMIN role", httpMethod = "GET", authorizations = {
			@Authorization(value = "bearerToken") })
	public ResponseEntity<List<Person>> findPersonById(
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "age", required = false) Integer age,
			@RequestParam(value = "favouriteColour", required = false) String favouriteColour) {
		List<Person> person = personManager.fetchByCustomQuery(firstName, lastName, age, favouriteColour);
		return ResponseEntity.ok(person);
	}

	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "To update details of the Person", notes = "Update a particular person - accessible only to user with ADMIN role", httpMethod = "PUT", authorizations = {
			@Authorization(value = "bearerToken") })
	public ResponseEntity<Person> updatePerson(@PathVariable Long id, @Valid @RequestBody Person person) {
		Person personValue = personManager.updatePerson(id, person);
		return ResponseEntity.status(HttpStatus.CREATED).body(personValue);
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "To delete a particular Person", notes = "Delte particular person - accessible only to user with ADMIN role", httpMethod = "DELETE", authorizations = {
			@Authorization(value = "bearerToken") })
	public ResponseEntity<String> deletePerson(@PathVariable Long id) {
		personManager.deletePerson(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "To delete all the persons in the system", notes = "Delete all persons - accessible only to user with ADMIN role", httpMethod = "DELETE", authorizations = {
			@Authorization(value = "bearerToken") })
	public ResponseEntity<String> deletePeople() {
		personManager.deletePeople();
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
