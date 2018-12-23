package com.niraj.jcommander.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.niraj.jcommander.FamilyTreeService;
import com.niraj.jcommander.PersonRepository;
import com.niraj.jcommander.domain.Person;



public class PersonExistsValidator implements ConstraintValidator<PersonExists, Person> {
	@Autowired
	PersonRepository personRepository;

	private static final Logger log = LoggerFactory.getLogger(FamilyTreeService.class);

	@Override
	public boolean isValid(Person object, ConstraintValidatorContext context) {

		log.info("Validating Person if Existes {}",object);
		return personRepository.isPresent(object);

	}

}
