package com.niraj.jcommander.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.exception.FamilyTreeException;

@Component
public class PersonRepository {

	private List<Person> list = new ArrayList<>();

	public boolean isPresent(Person object) {
		return list.stream().filter(per -> per.getName().equals(object.getName())).count() == 1;
	}

	public void add(Person person) {
		list.add(person);

	}

	public int size() {
		return list.size();
	}

	public Person get(Person person) {
		return list.stream()
				.filter(p -> p.getName().equalsIgnoreCase(person.getName()))
				.findFirst()
				.orElseThrow(() -> new FamilyTreeException("Person Does not exists " + person.getName()));

	}

	public List<Person> getAll() {
		return list;
	}

}
