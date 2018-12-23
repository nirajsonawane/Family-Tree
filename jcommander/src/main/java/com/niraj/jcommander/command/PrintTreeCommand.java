package com.niraj.jcommander.command;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.niraj.jcommander.CommondProcessor;
import com.niraj.jcommander.FamilyTreeService;
import com.niraj.jcommander.converter.FemaleConverter;
import com.niraj.jcommander.converter.MaleConverter;
import com.niraj.jcommander.converter.PersonConverter;
import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.validator.MutuallyExclusiveFields;

import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Parameters(separators = "=")
@ToString
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrintTreeCommand implements Command<String> {

	private static final Logger log = LoggerFactory.getLogger(PrintTreeCommand.class);

	@Autowired
	@Exclude
	FamilyTreeService personService;

	@Parameter(names = "Print", converter = PersonConverter.class)
	@NotNull
	private Person person;

	@Override
	public String run() {
		log.info("Print Tree Command");
		//personService.printTree(person);
		personService.printAll();
		return null;

	}

	@Override
	public void cleanup() {
		person = null;
	}

}
