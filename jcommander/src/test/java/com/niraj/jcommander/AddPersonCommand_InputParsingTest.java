package com.niraj.jcommander;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.beust.jcommander.JCommander;
import com.niraj.jcommander.command.AddChildCommand;
import com.niraj.jcommander.command.AddPersonCommand;
import com.niraj.jcommander.command.SearchRelationCommand;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddPersonCommand_InputParsingTest {

	@Autowired
	private AddPersonCommand addPersonCommand;
	@Autowired
	private SearchRelationCommand searchRelationCommand;

	@After
	public void Cleanup() {
		addPersonCommand.cleanup();
	}

	@Test
	public void shouldParseCommandCorreclty_AddMale() {
		String[] input = { "Male=Ashok"};
		JCommander jCommander = new JCommander();
		jCommander.addObject(addPersonCommand);
		jCommander.parse(input);
		Assert.assertTrue(addPersonCommand.validate());

	}
	@Test
	public void shouldParseCommandCorreclty_AddFemale() {
		String[] input = { "Female=Ashok", };
		JCommander jCommander = new JCommander();
		jCommander.addObject(addPersonCommand);
		jCommander.parse(input);
		Assert.assertTrue(addPersonCommand.validate());

	}
	@Test
	public void should_Not_ParseCommand_mutuallyExclusiveFields() {
		String[] input = { "Female=Alka", "Male=Ashok"};
		JCommander jCommander = new JCommander();
		jCommander.addObject(addPersonCommand);
		jCommander.parse(input);
		Assert.assertFalse(addPersonCommand.validate());

	}
	@Test
	public void should_Not_ParseAnyInvalideCommand() {
		String[] input = { "Random=Alka"};
		JCommander jCommander = new JCommander();
		jCommander.addObject(addPersonCommand);
		jCommander.addObject(searchRelationCommand);
		jCommander.parse(input);
		Assert.assertFalse(addPersonCommand.validate());
	}
	
	


}
