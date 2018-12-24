package com.niraj.jcommander.relation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.util.GenderEnum;
import com.niraj.jcommander.util.RelationNameEnum;

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
				.map(Person::getSiblings)
				.flatMap(List::stream)
				.collect(Collectors.groupingBy(Person::getGender));

		log.info("Map {}",uncleAuntMap);
		
		if (uncleAuntMap.containsKey(GenderEnum.MALE.name())) {

			directUncle = uncleAuntMap.get(GenderEnum.MALE.name())
					.stream()
					.map(Person::getName)
					.collect(Collectors.joining(","));
		}

		if (uncleAuntMap.containsKey(GenderEnum.FEMALE.name())) {

			indirectUncle = uncleAuntMap.get(GenderEnum.FEMALE.name())
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
		this.relationName = RelationNameEnum.UNCLE;
	}

}
