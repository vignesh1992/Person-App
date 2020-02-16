package com.application.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.person.entity.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long>, PersonRepositoryCustom {

}
