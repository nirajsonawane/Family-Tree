package com.niraj.jcommander.command;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.niraj.jcommander.service.FamilyTreeServiceImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Parameters(separators = "=")
@ToString
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CleanTreeCommand implements Command<String> {

	private static final Logger log = LoggerFactory.getLogger(CleanTreeCommand.class); 

	@Autowired
	FamilyTreeServiceImpl personService;

	@Parameter(names = "Clean")
	@NotNull
	private String clean;

	@Override
	public String run() {
		log.info("Clearing the family Tree");	
		personService.cleanFamilyTree();
		return "Tree is Clean";

	}

	@Override
	public void cleanup() {
		clean = null;
	}

}
