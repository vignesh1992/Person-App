package com.application.person.model;

import java.io.Serializable;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String jwt;

}