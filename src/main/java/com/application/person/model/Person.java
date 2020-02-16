package com.application.person.model;

import java.util.List;

import com.application.person.entity.PersonEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

	private String firstName;

	private String lastName;

	private Integer age;

	private String favouriteColour;

	private List<String> hobby;

	public PersonEntity toPersonEntity() {
		return PersonEntity.builder().first_name(this.firstName).last_name(this.lastName).age(this.age)
				.favourite_colour(this.favouriteColour).hobby(this.hobby).build();
	}

}
