package com.niraj.jcommander.relation;

import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.util.RelationName;

@Component
public class CousinRelationshipFinder extends RelationShipFinder {

	private static final Logger log = LoggerFactory.getLogger(CousinRelationshipFinder.class);

	@Override
	public String findRelation( Person person) {
		Person findPerson = familyTreeService.findPerson(person);
		log.info("Finding Cousin for {}", findPerson);

		String cousinsAsString = findPerson.getParentBelongsToCurrentFamilyTree().get(0)
				.getSiblings()
				.stream() 
				.map(uncles -> uncles.getRelations().getChilds())
				.flatMap(x -> x.stream())
				.map(cousins -> cousins.getName())
				.collect(Collectors.joining(","));

		log.info("Cousings For {} are {}",findPerson.getName(),cousinsAsString );
		
		return cousinsAsString;

	}

	@Override
	@PostConstruct
	void setRelationName() {
		this.relationName=RelationName.COUSIN;
	}

	
}
