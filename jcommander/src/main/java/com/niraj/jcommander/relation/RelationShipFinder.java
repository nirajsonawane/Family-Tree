package com.niraj.jcommander.relation;

import org.springframework.beans.factory.annotation.Autowired;

import com.niraj.jcommander.FamilyTreeService;
import com.niraj.jcommander.RelationName;
import com.niraj.jcommander.domain.Person;

public abstract class RelationShipFinder {

	@Autowired
	protected FamilyTreeService familyTreeService;
	
	protected RelationName relationName;

	abstract public String findRelation(Person person);
	
	abstract void setRelationName();
	
	public  RelationName getRelationName()
	{
		return relationName;
	}
	
}
