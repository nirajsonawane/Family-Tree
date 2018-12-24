package com.niraj.jcommander.relation;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.util.RelationNameEnum;
import com.niraj.jcommander.util.StreamUtils;

@Component
public class SisterRelationshipFinder extends RelationShipFinder {

	private static final Logger log = LoggerFactory.getLogger(SisterRelationshipFinder.class);

	@Override
	public String findRelation(Person person) {
		Person findPerson = familyTreeService.findPerson(person);
		log.info("Finding Sisters for {}", person);
		String sisters = StreamUtils.getNamesAsStringFromList(findPerson.getSiblings(StreamUtils.FEMALE_FILTER));
		log.info("Sisters for {} are {}", person, sisters);
		return sisters;

	}

	@Override
	@PostConstruct
	void setRelationName() {
		this.relationName = RelationNameEnum.SISTER;

	}

}
