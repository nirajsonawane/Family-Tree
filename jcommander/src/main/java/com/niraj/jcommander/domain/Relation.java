package com.niraj.jcommander.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.Getter;

public class Relation {

	@Getter
	private final List<Person> childs = new ArrayList<>();
	@Getter
	private final List<Person> parents = new ArrayList<>();
	@Getter
	private Person spouse;

	public void addChilds(Person child) {
		childs.add(child);
	}

	public void addParent(Person parent) {
		parents.add(parent);
	}

	public void addSpouse(Person spouse) {
		this.spouse = spouse;
	}

	public List<Person> getSons() {
		return childs.stream().filter(child -> child.getGender().equals("Male")).collect(Collectors.toList());
	}

	public List<Person> getDaughters() {
		return childs.stream().filter(child -> child.getGender().equals("Female")).collect(Collectors.toList());
	}

	public Optional<Person> getFather() {
		return parents.stream().filter(p -> p.getGender().equals("Male")).findFirst();
	}

	public Optional<Person> getMother() {
		return parents.stream().filter(m -> m.getGender().equals("Female")).findFirst();
	}
	
	
	

	@Override
	public String toString() {

		String child = childs.stream().map(c -> c.getName()).collect(Collectors.joining(","));
		String parent = parents.stream().map(c -> c.getName()).collect(Collectors.joining(","));
		String spString = "";
		if (null != spouse)
			spString = spouse.getName();
		return "Relation [childs=" + child + ", parents=" + parent + ", spouse=" + spString + "]";
	}

}
