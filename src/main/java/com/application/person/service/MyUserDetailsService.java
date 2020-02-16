package com.application.person.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.application.person.model.MyUserDetails;
import com.application.person.entity.UserEntity;
import com.application.person.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public MyUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		Optional<UserEntity> user = userRepository.findByUserName(s);

		if (user.isPresent()) {
			return new MyUserDetails(user.get());
		}

		throw new UsernameNotFoundException("User not found");

	}

}