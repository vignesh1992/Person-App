package com.application.person.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.person.model.AuthenticationResponse;
import com.application.person.util.JwtUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	@ApiOperation(value = "Logging in using Form Based Authentication to obtain access token", notes = "Does a IDM Authentication and secures the accesstoken", authorizations = {
			@Authorization(value = "basicAuth") })
	public ResponseEntity<?> createAuthenticationToken(HttpServletRequest request) throws Exception {

		final String authorizationHeader = request.getHeader("Authorization");

		String username = null;
		String password = null;
		String base64Encoded = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
			base64Encoded = authorizationHeader.substring(6);

			byte[] byteArray = Base64.decodeBase64(base64Encoded.getBytes());

			String decoded = new String(byteArray);

			String[] split = decoded.split(":");

			if (split.length != 0) {
				username = split[0];
				password = split[1];
			}
		}

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}
