package com.niraj.jcommander.service;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.validator.PersonExists;
import com.niraj.jcommander.validator.UniqueName;

public interface FamilyTreeService {

	//void printAll();

	Person findPerson(@PersonExists Person person);

	String updateSpouse(Person husband, Person wife);

	void addChild(@PersonExists Person parent, @UniqueName Person child);

	void addPerson(@UniqueName Person person);

	void cleanFamilyTree();

}
