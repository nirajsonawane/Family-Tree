package com.niraj.jcommander.relation;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.exception.FamilyTreeException;
import com.niraj.jcommander.util.RelationNameEnum;

@Component
public class MotherRelationshipFinder extends RelationShipFinder {

	private static final Logger log = LoggerFactory.getLogger(MotherRelationshipFinder.class);

	@Override
	public String findRelation( Person person) {
		Person findPerson = familyTreeService.findPerson(person);
		log.info("Finding Mother for {}", findPerson);
		Person sons = findPerson.getRelations()
				.getMother().orElseThrow(()->new FamilyTreeException("No Mother"));
		log.info("Mother for {} are {}", sons.getName());
		return sons.getName();
	}

	@Override
	@PostConstruct
	void setRelationName() {
		this.relationName=RelationNameEnum.MOTHER;
		
	}

	

}
