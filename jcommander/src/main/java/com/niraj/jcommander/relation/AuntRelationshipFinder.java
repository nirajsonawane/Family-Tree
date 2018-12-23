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
public class AuntRelationshipFinder extends RelationShipFinder {

	private static final Logger log = LoggerFactory.getLogger(AuntRelationshipFinder.class);

	@Override
	public String findRelation( Person person) {
		Person findPerson = familyTreeService.findPerson(person);
		log.info("Finding Aunt for {}", findPerson);
 
		String directAunts = "";
		String indirectAunts = "";
		Map<String, List<Person>> uncleAuntMap = findPerson.getParentBelongsToCurrentFamilyTree()
				.stream() 
				.map(parent -> parent.getSiblings())
				.flatMap(x -> x.stream())
				.collect(Collectors.groupingBy(Person::getGender));

		if (uncleAuntMap.containsKey(GenderEnum.FEMALE.name())) {
 
			directAunts = uncleAuntMap.get(GenderEnum.FEMALE.name())
					.stream()
					.map(aunt -> aunt.getName())
					.collect(Collectors.joining(","));
		}

		if (uncleAuntMap.containsKey(GenderEnum.MALE.name())) {

			indirectAunts = uncleAuntMap.get(GenderEnum.MALE.name())
					.stream()
					.filter(Person::isMarried)
					.map(uncle -> uncle.getRelations().getSpouse().getName())
					.collect(Collectors.joining(","));
		}

		log.info("Aunt For {} are {} and {}", findPerson.getName(), directAunts, indirectAunts);

		return directAunts + indirectAunts;

	}

	@PostConstruct
	@Override
	void setRelationName() {
		relationName = RelationNameEnum.AUNT;
	}

}
