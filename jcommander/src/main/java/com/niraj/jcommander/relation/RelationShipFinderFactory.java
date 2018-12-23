package com.niraj.jcommander.relation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.niraj.jcommander.exception.FamilyTreeException;

@Component
public class RelationShipFinderFactory {

	
	@Autowired
	private List<RelationShipFinder> list;

	public RelationShipFinder getRelationShipFinder(String relation) {
			return list.stream()
				.filter(rf -> rf.getRelationName().name().equalsIgnoreCase(relation))
				.findFirst()
				.orElseThrow(() -> new FamilyTreeException("Relation not yet suppoerted "+relation));
	}

}
