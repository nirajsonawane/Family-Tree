package com.niraj.jcommander.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.niraj.jcommander.util.StreamUtils;

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
		return StreamUtils.filterList(childs, StreamUtils.MALE_FILTER);

	}

	public List<Person> getDaughters() {
		return StreamUtils.filterList(childs, StreamUtils.FEMALE_FILTER);
	}

	public Optional<Person> getFather() {
		return StreamUtils.filterList(parents, StreamUtils.MALE_FILTER).stream().findFirst();

	}

	public Optional<Person> getMother() {
		return StreamUtils.filterList(parents, StreamUtils.FEMALE_FILTER).stream().findFirst();
	}

	@Override
	public String toString() {

		String child = StreamUtils.getNamesAsStringFromList(childs);
		String parent = StreamUtils.getNamesAsStringFromList(parents);
		String spString = "";
		if (null != spouse)
			spString = spouse.getName();
		return "Relation [childs=" + child + ", parents=" + parent + ", spouse=" + spString + "]";
	}

}
