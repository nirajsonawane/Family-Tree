package com.niraj.jcommander.command;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.niraj.jcommander.converter.PersonConverter;
import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.relation.RelationShipFinderFactory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Parameters(separators = "=")
@ToString
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SearchRelationCommand implements Command<String> {

	private static final Logger log = LoggerFactory.getLogger(SearchRelationCommand.class);

	@Autowired
	private RelationShipFinderFactory factory;

	@Parameter	
	private List<String> parameters = new ArrayList<>();
	
	
	

	@Parameter(names = { "Person" }, converter = PersonConverter.class)
	@NotNull
	private Person person;

	@Parameter(names = "Relation")
	@NotNull
	private String relation;

	@Override
	public String run() {

		String sisters = factory.getRelationShipFinder(relation).findRelation(person);
		log.info("{} : {} ",relation, sisters);

		return sisters;
	}

	@Override
	public void cleanup() {

		person = null;
		relation = null;
	}

}
