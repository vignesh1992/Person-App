package com.application.person;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.application.person.model.Authorities;
import com.application.person.model.User;
import com.application.person.service.UserManager;

@SpringBootApplication
@EntityScan(basePackages = { "com.application.person.entity" })
@ComponentScan("com.application.person")
@EnableJpaRepositories(basePackages = { "com.application.person.repository" })
public class PersonApplication {

	private UserManager userManager;

	@Autowired
	PersonApplication(UserManager userManager) {
		this.userManager = userManager;
	}

	public static void main(String[] args) {
		SpringApplication.run(PersonApplication.class, args);
	}

	@PostConstruct
	public void init() {

		List<Authorities> adminAuthority = new ArrayList<>();
		adminAuthority.add(Authorities.valueOf("ROLE_ADMIN"));

		List<Authorities> regularUserAuthority = new ArrayList<>();
		regularUserAuthority.add(Authorities.valueOf("ROLE_USER"));

		userManager.createUser(new User("regularuser", "Welcome@12", true, regularUserAuthority));
		userManager.createUser(new User("adminuser", "Welcome@34", true, adminAuthority));
	}

}
