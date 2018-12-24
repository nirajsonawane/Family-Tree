package com.niraj.jcommander;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beust.jcommander.JCommander;
import com.niraj.jcommander.command.Command;

@Component
public class CommondProcessor { 

	private static final Logger log = LoggerFactory.getLogger(CommondProcessor.class);
	
	@Autowired 
	private List<Command<String>> commands;

	public String process(String args[]) {

		log.info("Recvied Command  {}",Arrays.toString(args));
		commands.forEach(Command::cleanup);
		JCommander jCommander = new JCommander();
		commands.forEach(jCommander::addObject);	
		jCommander.parse(args);
		validatedCommand(args);
		Object commandOutPut = commands.stream()
										.filter(Command::validate)
										.findFirst().get().run();
		log.info("Command  OutPut {} ",commandOutPut);
		return commandOutPut.toString();
	} 
	
 
	private void validatedCommand(String[] args) {

		if (1 != commands.stream().filter(Command::validate).count())
			throw new InvalidParameterException("Invalid Command " + Arrays.toString(args));

	}

}
