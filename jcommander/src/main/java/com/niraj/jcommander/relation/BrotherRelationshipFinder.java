package com.niraj.jcommander.relation;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.util.RelationNameEnum;
import com.niraj.jcommander.util.StreamUtils;

@Component
public class BrotherRelationshipFinder extends RelationShipFinder {

	private static final Logger log = LoggerFactory.getLogger(BrotherRelationshipFinder.class);

	@Override
	public String findRelation( Person person) {
		Person findPerson = familyTreeService.findPerson(person);
		log.info("Finding Brothers for {}", person);		
		String brothers= StreamUtils.getNamesAsStringFromList(findPerson.getSiblings(StreamUtils.MALE_FILTER));
		log.info("Brothers for {} are {}", findPerson,brothers);
		return brothers;
		

	}

	@Override
	@PostConstruct
	void setRelationName() {
		this.relationName=RelationNameEnum.BROTHER;
	}

	

}
