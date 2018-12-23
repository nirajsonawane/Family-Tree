package com.niraj.jcommander.command;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.niraj.jcommander.converter.FemaleConverter;
import com.niraj.jcommander.converter.MaleConverter;
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
@MutuallyExclusiveFields(baseField = "mother", matchField = "father")
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AddPersonCommand implements Command<String> {

	@Autowired	
	private FamilyTreeService familyTreeService;	
	
	@Parameter(names = { "Female" }, converter = FemaleConverter.class)
	private Person mother;

	@Parameter(names = "Male", converter = MaleConverter.class)
	private Person father; 

	@Override
	public String run() {		
		Person person = Optional.ofNullable(mother).orElse(father);
		familyTreeService.addPerson(Optional.ofNullable(mother).orElse(father));
		return "Welcome  " + person.getName();
	} 

	@Override
	public void cleanup() {
		mother=null;
		father=null;
		
	}

}
