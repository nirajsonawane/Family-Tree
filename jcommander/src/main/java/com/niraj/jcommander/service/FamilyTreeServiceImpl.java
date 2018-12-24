package com.niraj.jcommander.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.repository.PersonRepository;

@Component
@Validated
public class FamilyTreeServiceImpl implements FamilyTreeService {

	private static final Logger log = LoggerFactory.getLogger(FamilyTreeServiceImpl.class);

	@Autowired
	private PersonRepository personRepository;

	@Override
	public void addPerson(Person person) {
		personRepository.add(person);
		log.info("Person Added To Family Tree");
	}

	@Override
	public void addChild(Person parent, Person child) {
		Person parentFromList = personRepository.get(parent);
		log.info("Parent Found {}", parentFromList);
		parentFromList.getRelations().addChilds(child); 
		child.getRelations().addParent(parentFromList);

		if (parentFromList.isMarried()) {
			Person spouse = parentFromList.getRelations().getSpouse();
			spouse.getRelations().addChilds(child);
			child.getRelations().addParent(spouse);
			log.info("Updating Spouse {}",spouse);
		}
		log.info("Updated Child {}", child);
		personRepository.add(child);

	}

	@Override
	public String updateSpouse(Person husband, Person wife) {
		boolean updateWife = personRepository.isPresent(husband);		
		if (updateWife) {
			updateWifeToHusband(husband, wife);
			return "Welcome " + wife.getName();
		} else {
			updateHusbandToWife(husband, wife);
			return "Welcome " + husband.getName();
		}

	}

	private void updateHusbandToWife(Person husband, Person wife) {

		Person wifeFromRepo = personRepository.get(wife);
		husband.getRelations().addSpouse(wifeFromRepo);
		wifeFromRepo.getRelations().addSpouse(husband);
		personRepository.add(husband);

	}

	private void updateWifeToHusband(Person husband, Person wife) {
		Person husbandFromRepo = personRepository.get(husband);
		husbandFromRepo.getRelations().addSpouse(wife);
		wife.getRelations().addSpouse(husbandFromRepo);
		personRepository.add(wife);

	}

	

	@Override
	public Person findPerson(Person person) {
		return personRepository.get(person);
	}

	/*@Override
	public void printAll() {
		personRepository.getAll().forEach(System.out::println);
	}*/

	@Override
	public void cleanFamilyTree() {
		personRepository.clear();
	}

}
