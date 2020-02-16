package com.application.person.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.application.person.entity.PersonEntity;
import com.application.person.model.Person;

@Repository
public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {

	EntityManager em;

	public PersonRepositoryCustomImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Person> findByCustomQuery(String firstName, String lastName, Integer age, String favouriteColour) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PersonEntity> cq = cb.createQuery(PersonEntity.class);
		Root<PersonEntity> root = cq.from(PersonEntity.class);

		cq.select(root);

		cq.orderBy(cb.desc(root.get("id")));

		ArrayList<Predicate> predicates = new ArrayList<>();

		java.util.function.Predicate<String> nullOrEmptyString = x -> x != null && !x.isEmpty();

		if (nullOrEmptyString.test(firstName)) {
			predicates.add(cb.equal(root.get("first_name"), firstName));
		}

		if (nullOrEmptyString.test(lastName)) {
			predicates.add(cb.equal(root.get("last_name"), lastName));
		}

		if (nullOrEmptyString.test(favouriteColour)) {
			predicates.add(cb.equal(root.get("favourite_colour"), favouriteColour));
		}

		if (age != null) {
			predicates.add(cb.equal(root.get("age"), age));
		}

		if (!predicates.isEmpty()) {
			cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		}

		TypedQuery<PersonEntity> query = em.createQuery(cq);

		List<PersonEntity> resultList = query.getResultList();

		return resultList.stream().map(p -> p.toPerson()).collect(Collectors.toList());

	}

}
