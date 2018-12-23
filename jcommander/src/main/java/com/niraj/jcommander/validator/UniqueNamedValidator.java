package com.niraj.jcommander.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.repository.PersonRepository;
import com.niraj.jcommander.service.FamilyTreeServiceImpl;



public class UniqueNamedValidator implements ConstraintValidator<UniqueName, Person> {
	
	@Autowired
	PersonRepository personRepository;

	private static final Logger log = LoggerFactory.getLogger(FamilyTreeServiceImpl.class);

	@Override
	public boolean isValid(Person object, ConstraintValidatorContext context) {

		log.info("Validating Person for Duplicate {}",object);
		return !personRepository.isPresent(object);

	}

}
