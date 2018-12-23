package com.niraj.jcommander.relation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.util.RelationName;

import lombok.extern.java.Log;

@Component
public class UncleRelationshipFinder extends RelationShipFinder {

	private static final Logger log = LoggerFactory.getLogger(UncleRelationshipFinder.class);

	@Override
	public String findRelation(Person person) {
		Person findPerson = familyTreeService.findPerson(person);
		log.info("Finding Uncle for {}", findPerson);
		String directUncle = ""; 
		String indirectUncle = "";

		Map<String, List<Person>> uncleAuntMap = findPerson.getParentBelongsToCurrentFamilyTree()
				.stream()
				.map(parent -> parent.getSiblings())
				.flatMap(x -> x.stream())
				.collect(Collectors.groupingBy(Person::getGender));

		log.info("Map {}",uncleAuntMap);
		
		if (uncleAuntMap.containsKey("Male")) {

			directUncle = uncleAuntMap.get("Male")
					.stream()
					.map(uncl -> uncl.getName())
					.collect(Collectors.joining(","));
		}

		if (uncleAuntMap.containsKey("Female")) {

			indirectUncle = uncleAuntMap.get("Female")
					.stream()
					.filter(Person::isMarried)
					.map(aunt -> aunt.getRelations().getSpouse().getName())
					.collect(Collectors.joining(","));
		}

		log.info("Uncle For {} are {} and {}", findPerson.getName(), directUncle, indirectUncle);
		return directUncle + "," +indirectUncle;

	}

	@Override
	@PostConstruct
	void setRelationName() {
		this.relationName = RelationName.UNCLE;
	}

}
