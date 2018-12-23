package com.niraj.jcommander.relation;

import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.util.RelationName;

@Component
public class SonRelationshipFinder extends RelationShipFinder {

	private static final Logger log = LoggerFactory.getLogger(SonRelationshipFinder.class);

	@Override
	public String findRelation( Person person) {
		Person findPerson = familyTreeService.findPerson(person);
		log.info("Finding Sons for {}", person);
		String sons = findPerson.getRelations()
				.getSons()
				.stream()
				.map(Person::getName)
				.collect(Collectors.joining(","));
		log.info("Sons for {} are {}", sons);
		return sons;
	}

	@Override
	@PostConstruct
	void setRelationName() {
		this.relationName=RelationName.SON;
		
	}

	

}
