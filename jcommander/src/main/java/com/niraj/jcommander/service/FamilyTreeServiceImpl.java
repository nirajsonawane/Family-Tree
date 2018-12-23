package com.niraj.jcommander.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.repository.PersonRepository;
import com.niraj.jcommander.validator.PersonExists;
import com.niraj.jcommander.validator.UniqueName;

@Component
@Validated
public class FamilyTreeServiceImpl  implements FamilyTreeService{

	private static final Logger log = LoggerFactory.getLogger(FamilyTreeServiceImpl.class);

	@Autowired
	private PersonRepository personRepository;

	
	@Override
	public void addPerson(Person person) {
		personRepository.add(person);
		log.info("Person Added To Family Tree");
	} 

	@Override
	public void addChild(@PersonExists Person parent, @UniqueName Person child) {
		Person parentFromList = personRepository.get(parent);
		log.info("Parent Found {}", parentFromList);
		parentFromList.getRelations().addChilds(child);
		child.getRelations().addParent(parentFromList);
		
		if(null!=parentFromList.getRelations().getSpouse())
		{
			parentFromList.getRelations().getSpouse().getRelations().addChilds(child);
			child.getRelations().addParent(parentFromList.getRelations().getSpouse());
		}
		log.info("Updated Child {}",child);
		personRepository.add(child);

	}

	@Override
	public void updateSpouse(Person husband, Person wife) {
		boolean updateWife = personRepository.isPresent(husband);
		boolean updateHusband = personRepository.isPresent(wife);
		if(updateWife)
			updateWifeToHusband(husband,wife);
		if(updateHusband)
			updateHusbandToWife(husband,wife);
		
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
	public void printTree(@PersonExists Person person)
	{
		//System.out.println("Total Pepole in Repo " + personRepository.size());
		Person persionFromList = personRepository.get(person);
		System.out.println(persionFromList.printPerson());
		List<Person> childs = persionFromList.getRelations().getChilds();
		childs.forEach(p->System.out.print(p.printPerson()));
		if(persionFromList.getRelations().getChilds().size()>0)
		System.out.println();
		childs.forEach(p->{
			p.getRelations().getChilds().forEach(child->printTree(child));
		});
		
	}
	
	@Override
	public Person findPerson(Person person)
	{
		return personRepository.get(person);
	}

	@Override
	public void printAll()
	{
		personRepository.getAll().forEach(System.out::println);
	}
	

}
