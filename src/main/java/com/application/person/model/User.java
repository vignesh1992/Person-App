package com.application.person.model;

import java.util.List;
import java.util.stream.Collectors;

import com.application.person.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	private String userName;

	private String password;

	@Builder.Default
	private boolean active = true;

	private List<Authorities> roles;

	public UserEntity toUserEntity() {

		List<String> roleInString = this.roles.stream().map(x -> x.toString()).collect(Collectors.toList());

		return UserEntity.builder().userName(this.userName).password(this.password).active(this.active)
				.roles(roleInString).build();
	}
}
