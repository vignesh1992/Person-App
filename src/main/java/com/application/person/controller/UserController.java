package com.application.person.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.person.model.User;
import com.application.person.service.UserManager;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserManager userManager;

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "To Add a user who can manage the person in the system", notes = "Add an user - accessible only to user with ADMIN role", httpMethod = "POST", authorizations = {
			@Authorization(value = "bearerToken") })
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User userValue = userManager.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userValue);
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "To Retrieve all users", notes = "Get a perticular user - accessible only to user with ADMIN role", httpMethod = "GET", authorizations = {
			@Authorization(value = "bearerToken") })
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> userValues = userManager.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(userValues);
	}
}
