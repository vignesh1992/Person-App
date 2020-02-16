package com.application.person.entity;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.application.person.model.Person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PERSON")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String first_name;

	private String last_name;

	private Integer age;

	private String favourite_colour;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> hobby;

	public Person toPerson() {
		return Person.builder().firstName(this.first_name).lastName(this.last_name).age(this.age)
				.favouriteColour(this.favourite_colour).hobby(this.hobby).build();

	}

}
