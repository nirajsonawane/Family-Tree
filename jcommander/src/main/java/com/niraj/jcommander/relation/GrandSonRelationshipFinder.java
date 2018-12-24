package com.niraj.jcommander.relation;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.util.RelationNameEnum;
import com.niraj.jcommander.util.StreamUtils;

@Component
public class GrandSonRelationshipFinder extends RelationShipFinder {

	private static final Logger log = LoggerFactory.getLogger(GrandSonRelationshipFinder.class);

	@Override
	public String findRelation(Person person) {
		Person findPerson = familyTreeService.findPerson(person);
		log.info("Finding Grand Sons for {}", findPerson);
		String grandChlids = findGrandChilds(findPerson,StreamUtils.MALE_FILTER);
		log.info("Grand Sons for {} are {}", findPerson, grandChlids);
		return grandChlids;

	}

	@Override
	@PostConstruct
	void setRelationName() {
		this.relationName = RelationNameEnum.GRANDSON;
	}

}
