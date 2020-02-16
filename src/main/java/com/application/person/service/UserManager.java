package com.application.person.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.application.person.entity.UserEntity;
import com.application.person.model.User;
import com.application.person.repository.UserRepository;

@Component
public class UserManager {

	private final UserRepository userRepository;

	@Autowired
	public UserManager(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User createUser(User user) {
		UserEntity userEntity = user.toUserEntity();
		UserEntity save = userRepository.save(userEntity);
		return save.toUser();
	}

	public List<User> getAllUsers() {
		List<UserEntity> allUsers = userRepository.findAll();
		return allUsers.stream().map(s -> s.toUser()).collect(Collectors.toList());
	}
}
