package com.application.person.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest implements Serializable {

	// refer this 
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;

}