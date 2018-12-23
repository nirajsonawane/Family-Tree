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
import com.niraj.jcommander.converter.FemaleConverter;
import com.niraj.jcommander.converter.MaleConverter;
import com.niraj.jcommander.domain.Person;
import com.niraj.jcommander.service.FamilyTreeServiceImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Parameters(separators = "=")
@ToString
@Component
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UpdateSpouseCommand implements Command<String>{
	
	private static final Logger log = LoggerFactory.getLogger(UpdateSpouseCommand.class);
	
	@Autowired
	private FamilyTreeServiceImpl personRepository;

	@NotNull
	@Parameter(names = "Husband",converter=MaleConverter.class)
	private Person husband;

	@Parameter(names = "Wife",converter=FemaleConverter.class)
	@NotNull
	private Person wife;
 
	@Override
	public String run() {
		log.info("Updating Spouse Info");
		personRepository.updateSpouse(husband,wife);
		return null;
	}

	@Override
	public void cleanup() {
		husband=null;
		wife=null;
		
	}

}
