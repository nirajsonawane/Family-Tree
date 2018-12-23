package com.niraj.jcommander.relation;

import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.util.RelationName;

@Component
public class BrotherRelationshipFinder extends RelationShipFinder {

	private static final Logger log = LoggerFactory.getLogger(BrotherRelationshipFinder.class);

	@Override
	public String findRelation( Person person) {
		Person findPerson = familyTreeService.findPerson(person);
		log.info("Finding Brothers for {}", person);
		String brothers= findPerson.getSiblings()
				.stream()
				.filter(brother -> brother.getGender().equalsIgnoreCase("Male"))
				.map(brother -> brother.getName())
				.collect(Collectors.joining(","));
		log.info("Brothers for {} are {}", findPerson,brothers);
		return brothers;
		

	}

	@Override
	@PostConstruct
	void setRelationName() {
		this.relationName=RelationName.BROTHER;
	}

	

}
