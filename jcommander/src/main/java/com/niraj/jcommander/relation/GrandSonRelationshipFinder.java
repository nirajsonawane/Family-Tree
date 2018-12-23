package com.niraj.jcommander.relation;

import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.util.RelationName;
import com.niraj.jcommander.validator.PersonExists;

@Component
public class GrandSonRelationshipFinder extends RelationShipFinder {

	private static final Logger log = LoggerFactory.getLogger(GrandSonRelationshipFinder.class);

	@Override
	public String findRelation( Person person) {
		Person findPerson = familyTreeService.findPerson(person);
		log.info("Finding Grand Sons for {}", findPerson);

		String grandChlids = findPerson.getRelations()
				.getChilds()
				.stream()
				.map(child -> child.getRelations().getChilds())
				.flatMap(x -> x.stream())
				.filter(grandChild -> grandChild.getGender().equalsIgnoreCase("Male"))
				.map(grandChild -> grandChild.getName())
				.collect(Collectors.joining(","));

		log.info("Grand Sons for {} are {}", findPerson, grandChlids);

		return grandChlids;

	}

	@Override
	@PostConstruct
	void setRelationName() {
		this.relationName = RelationName.GRANDSON;
	}

}
