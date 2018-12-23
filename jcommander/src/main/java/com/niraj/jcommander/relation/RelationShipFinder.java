package com.niraj.jcommander.relation;

import org.springframework.beans.factory.annotation.Autowired;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.service.FamilyTreeServiceImpl;
import com.niraj.jcommander.util.RelationName;
import com.niraj.jcommander.validator.PersonExists;

public abstract class RelationShipFinder {

	@Autowired
	protected FamilyTreeServiceImpl familyTreeService;
	
	protected RelationName relationName;

	abstract public String findRelation(@PersonExists Person person);
	
	abstract void setRelationName();
	
	public  RelationName getRelationName()
	{
		return relationName;
	}
	
}
