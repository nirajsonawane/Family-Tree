package com.niraj.jcommander.relation;

import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.util.RelationName;

@Component
public class SisterRelationshipFinder extends RelationShipFinder {

	private static final Logger log = LoggerFactory.getLogger(SisterRelationshipFinder.class);

	@Override
	public String findRelation(Person person) {
		Person findPerson = familyTreeService.findPerson(person);
		log.info("Finding Sisters for {}", person);
		String sisters = findPerson.getSiblings()
				.stream()
				.filter(subling -> subling.getGender().equalsIgnoreCase("Female"))
				.map(Person::getName)
				.collect(Collectors.joining(","));
		log.info("Sisters for {} are {}", person, sisters);
		return sisters;

	}

	@Override
	@PostConstruct
	void setRelationName() {
		this.relationName= RelationName.SISTER;
		
	}



}
