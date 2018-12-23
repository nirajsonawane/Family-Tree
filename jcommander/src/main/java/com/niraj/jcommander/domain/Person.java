package com.niraj.jcommander.domain;

import java.util.List;
import java.util.stream.Collectors;

import com.niraj.jcommander.exception.FamilyTreeException;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class Person {

	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private String gender;
	@Getter
	private final Relation relations = new Relation();

	public Boolean isMarried() {
		return null != relations.getSpouse();
	}

	public List<Person> getSiblings() {
		return getAnyParent().getRelations()
				.getChilds()
				.stream()
				.filter(per -> !(per.getName().equalsIgnoreCase(name)))
				.collect(Collectors.toList());
	}

	public String printPerson() {
		String spouseName = "";
		if (null != relations.getSpouse()) {
			spouseName = relations.getSpouse().getName();
		}

		return "[ " + name + "-" + spouseName + " ]";
	}

	public Person getAnyParent() {
		return relations.getFather()
				.orElse(relations.getMother()
				.orElseThrow(() -> new FamilyTreeException("No Parent for "+name)));
	}

	public List<Person> getParent() { 
		return relations.getParents();
	}
	public List<Person> getParentBelongsToCurrentFamilyTree() { 
		return relations.getParents().stream().filter(p->!p.getParent().isEmpty()).collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", gender=" + gender + ", relations=" + relations + "]";
	}

}
