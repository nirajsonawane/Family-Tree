package com.niraj.jcommander.relation;

import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.util.RelationNameEnum;
import com.niraj.jcommander.util.StremUtils;

@Component
public class GrandDaughterRelationshipFinder extends RelationShipFinder {

	private static final Logger log = LoggerFactory.getLogger(GrandDaughterRelationshipFinder.class);

	@Override
	public String findRelation( Person person) {
		Person findPerson = familyTreeService.findPerson(person);
		log.info("Finding Grand Daughter for {}", findPerson);

		String grandChlids = findPerson.getRelations()
				.getChilds()
				.stream()
				.map(child -> child.getRelations().getChilds())
				.flatMap(x -> x.stream())
				.filter(StremUtils.FEMALE_FILTER)
				.map(grandChild -> grandChild.getName())
				.collect(Collectors.joining(","));

		log.info("Grand Daughter for {} are {}", findPerson, grandChlids);

		return grandChlids;

	}

	@Override
	@PostConstruct
	void setRelationName() {
		this.relationName = RelationNameEnum.GRANDAUGHTER;
	}

}
