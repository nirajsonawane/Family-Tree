package com.niraj.jcommander.command;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.niraj.jcommander.converter.FemaleConverter;
import com.niraj.jcommander.converter.MaleConverter;
import com.niraj.jcommander.converter.PersonConverter;
import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.service.FamilyTreeService;
import com.niraj.jcommander.validator.MutuallyExclusiveFields;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Parameters(separators = "=")
@ToString
@Component
@MutuallyExclusiveFields(baseField = "son", matchField = "daughter")
public class AddChildCommand implements Command<String> {

	private static final Logger log = LoggerFactory.getLogger(AddChildCommand.class);
	
	
	@Autowired
	private FamilyTreeService service;
	
	

	@Parameter(names = { "Mother", "Father" },converter=PersonConverter.class )
	@NotNull
	private Person parent;

	@Parameter(names = "Son", converter = MaleConverter.class)
	private Person son;

	@Parameter(names = "Daughter", converter = FemaleConverter.class)
	private Person daughter;

	@Override
	public String run() {
		log.info("Add Child");
		Person child = Optional.ofNullable(son).orElse(daughter);
		service.addChild(parent, child);
		return "Welcome  " + child.getName(); 
	}

	@Override
	public void cleanup() {
		parent = null;
		son = null;
		daughter = null;

	}

}
