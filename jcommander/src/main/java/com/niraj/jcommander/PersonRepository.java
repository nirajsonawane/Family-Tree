package com.niraj.jcommander;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.niraj.jcommander.domain.Person;

@Component
public class PersonRepository {

	private List<Person> list = new ArrayList<>();

	public boolean isPresent(Person object) {		
		return list.stream().filter(per -> per.getName().equals(object.getName())).count() == 1;
	}

	public void add(Person person) {
		list.add(person);

	}
	
	public int size()
	{
		return list.size();
	}

	public Person getPerson(Person parent) {
		return  list.stream()
		.filter(p -> p.getName().equalsIgnoreCase(parent.getName()))
		.findFirst()
		.orElseThrow(() -> new FamilyTreeException("Parent Does not exists"));
		
	}
	
	public List<Person> getAll()
	{
		return list;
	}
	

}
