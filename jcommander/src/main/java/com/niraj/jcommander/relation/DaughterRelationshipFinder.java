package com.niraj.jcommander.relation;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.util.RelationNameEnum;
import com.niraj.jcommander.util.StreamUtils;

@Component
public class DaughterRelationshipFinder extends RelationShipFinder {

	private static final Logger log = LoggerFactory.getLogger(DaughterRelationshipFinder.class);

	@Override
	public String findRelation( Person person) {
		Person findPerson = familyTreeService.findPerson(person);
		log.info("Finding Daughter for {}", findPerson);
		
		String daughter =StreamUtils.getNamesAsStringFromList(findPerson.getRelations().getDaughters());		 
		log.info("Daughter for {} are {}", person.getName(),daughter);
		return daughter;
	}

	@Override
	@PostConstruct
	void setRelationName() {
		this.relationName=RelationNameEnum.DAUGHTER;
		
	}

	

}
