package com.niraj.jcommander.util;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.niraj.jcommander.domain.Person;

public class StreamUtils {

	private StreamUtils()
	{
		
	}
	public static Predicate<Person> MALE_FILTER = child -> child.getGender().equalsIgnoreCase(GenderEnum.MALE.name());

	public static Predicate<Person> FEMALE_FILTER = child -> child.getGender()
			.equalsIgnoreCase(GenderEnum.FEMALE.name());

	public static String getNamesAsStringFromList(List<Person> list) {
		return list.stream().map(Person::getName).collect(Collectors.joining(","));

	}
	
	public static List<Person> filterList(List<Person> list,Predicate<Person> condition) {
		return list.stream().filter(condition).collect(Collectors.toList());

	}

}
