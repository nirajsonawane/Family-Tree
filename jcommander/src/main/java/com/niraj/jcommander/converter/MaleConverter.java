package com.niraj.jcommander.converter;

import com.beust.jcommander.IStringConverter;
import com.niraj.jcommander.domain.Person;

public class MaleConverter implements IStringConverter<Person> {

	@Override
	public Person convert(String value) {

		return Person.builder().name(value)
				.gender("Male").build();
	}

}
