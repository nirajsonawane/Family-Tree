package com.niraj.jcommander.relation;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.util.RelationNameEnum;
import com.niraj.jcommander.util.StreamUtils;

@Component
public class GrandMotherRelationshipFinder extends RelationShipFinder {

	private static final Logger log = LoggerFactory.getLogger(GrandMotherRelationshipFinder.class);

	@Override
	public String findRelation(Person person) {
		Person findPerson = familyTreeService.findPerson(person);
		log.info("Finding GrandMother for {}", findPerson);
		String grandParents = super.findGrandParents(findPerson, StreamUtils.FEMALE_FILTER);
		log.info("GrandMother for {} are {} ", person, grandParents);
		return grandParents;

	}

	@Override
	@PostConstruct
	void setRelationName() {
		this.relationName = RelationNameEnum.GRANDMOTHER;
	}

}
