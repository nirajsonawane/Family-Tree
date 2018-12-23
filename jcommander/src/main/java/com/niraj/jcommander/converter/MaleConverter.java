package com.niraj.jcommander.converter;

import com.beust.jcommander.IStringConverter;
import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.util.GenderEnum;

public class MaleConverter implements IStringConverter<Person> {

	@Override
	public Person convert(String value) {

		return Person.builder().name(value)
				.gender(GenderEnum.MALE.name()).build();
	}

}
