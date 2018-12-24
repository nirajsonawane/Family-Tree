package com.niraj.jcommander.relation;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.service.FamilyTreeServiceImpl;
import com.niraj.jcommander.util.RelationNameEnum;
import com.niraj.jcommander.validator.PersonExists;

public abstract class RelationShipFinder {

	@Autowired
	protected FamilyTreeServiceImpl familyTreeService;
	
	protected RelationNameEnum relationName;

	abstract public String findRelation(@PersonExists Person person);
	
	abstract void setRelationName();
	
	public  RelationNameEnum getRelationName()
	{
		return relationName;
	}

	protected String findGrandChilds(Person findPerson,Predicate<Person> filter) {
		return findPerson.getRelations()
				.getChilds()
				.stream()
				.map(child -> child.getRelations().getChilds())
				.flatMap(List::stream)
				.filter(filter)
				.map(Person::getName)
				.collect(Collectors.joining(","));
		
	}

	protected String findGrandParents(Person findPerson, Predicate<Person> condition) {
		return findPerson.getParent()
				.stream()
				.map(Person::getParent) 
				.flatMap(List::stream)
				.filter(condition)
				.map(Person::getName)
				.collect(Collectors.joining(","));
	}
	
	
}
