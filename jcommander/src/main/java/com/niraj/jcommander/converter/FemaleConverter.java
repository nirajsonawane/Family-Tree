package com.niraj.jcommander.converter;

import com.beust.jcommander.IStringConverter;
import com.niraj.jcommander.domain.Person;

public class FemaleConverter implements IStringConverter<Person> {

	@Override
	public Person convert(String value) {
		return Person.builder().name(value).gender("Female").build();

	}

}
