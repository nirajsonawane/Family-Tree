package com.niraj.jcommander.util;

import java.util.function.Predicate;

import com.niraj.jcommander.domain.Person;

public class StremUtils {

	public static Predicate<Person> MALE_FILTER = child -> child.getGender().equalsIgnoreCase(GenderEnum.MALE.name());
	public static Predicate<Person> FEMALE_FILTER = child -> child.getGender().equalsIgnoreCase(GenderEnum.FEMALE.name());
	
	
}

