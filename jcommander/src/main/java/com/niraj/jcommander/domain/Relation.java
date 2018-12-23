package com.niraj.jcommander.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.niraj.jcommander.util.GenderEnum;
import com.niraj.jcommander.util.StremUtils;

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
		return filterList(childs,StremUtils.MALE_FILTER);
	}


	public List<Person> getDaughters() {
		return filterList(childs,StremUtils.FEMALE_FILTER);
	}

	public Optional<Person> getFather() {
		return filterList(parents,StremUtils.MALE_FILTER).stream().findFirst();
	}

	public Optional<Person> getMother() {
		return filterList(parents, StremUtils.FEMALE_FILTER).stream().findFirst();
	}
	
	public List<Person> filterList(List<Person> list , Predicate<Person> predicate) {
		return list.stream().filter(predicate).collect(Collectors.toList());
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
