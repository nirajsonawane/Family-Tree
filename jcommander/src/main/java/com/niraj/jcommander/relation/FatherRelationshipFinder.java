package com.niraj.jcommander.relation;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.exception.FamilyTreeException;
import com.niraj.jcommander.util.RelationNameEnum;

@Component
public class FatherRelationshipFinder extends RelationShipFinder {

	private static final Logger log = LoggerFactory.getLogger(FatherRelationshipFinder.class);

	@Override
	public String findRelation( Person person) {
		Person findPerson = familyTreeService.findPerson(person);
		log.info("Finding Father for {}", findPerson);
		Person sons = findPerson.getRelations()
				.getFather().orElseThrow(()->new FamilyTreeException("No Father For " + person.getName()));
		log.info("Father for {} are {}", person.getName(), sons.getName());
		return sons.getName();
	}

	@Override
	@PostConstruct
	void setRelationName() {
		this.relationName=RelationNameEnum.FATHER;
		
	}
}
