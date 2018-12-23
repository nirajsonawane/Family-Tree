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
	private List<Command> commands;

	public void process(String args[]) {

		log.info("Recvied Command  {}",Arrays.toString(args));
		JCommander jCommander = new JCommander();
		commands.forEach(System.out::println);
		commands.forEach(command -> jCommander.addObject(command));
		// jCommander.setCaseSensitiveOptions(true);
		jCommander.parse(args);
		validatedCommand();
		Object commandOutPut = commands.stream()
				             .filter(Command::validate)
				             .findFirst().get().run();
		log.info("Command  OutPut {} ",commandOutPut);
		commands.forEach(Command::cleanup);
	}
 
	private void validatedCommand() {

		if (1 != commands.stream().filter(Command::validate).count())
			throw new InvalidParameterException("Invalid Parameter");

	}

}
