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
import com.niraj.jcommander.command.SearchRelationCommand;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddChildCommandParsingTest {

	@Autowired
	private AddChildCommand addChildCommand;
	@Autowired
	private SearchRelationCommand searchRelationCommand;

	@After
	public void Cleanup() {
		addChildCommand.cleanup();
	}

	@Test
	public void shouldParseCommandCorreclty_AddSonToFather() {
		String[] input = { "Father=Ashok", "Son=Niraj" };
		JCommander jCommander = new JCommander();
		jCommander.addObject(addChildCommand);
		jCommander.parse(input);
		Assert.assertTrue(addChildCommand.validate());

	}
	@Test
	public void shouldParseCommandCorreclty_AddSonToMother() {
		String[] input = { "Mother=Ashok", "Son=Niraj" };
		JCommander jCommander = new JCommander();
		jCommander.addObject(addChildCommand);
		jCommander.parse(input);
		Assert.assertTrue(addChildCommand.validate());

	}
	@Test
	public void shouldParseCommandCorreclty_AddDaughterToFather() {
		String[] input = { "Father=Ashok", "Daughter=Rachana" };
		JCommander jCommander = new JCommander();
		jCommander.addObject(addChildCommand);
		jCommander.parse(input);
		Assert.assertTrue(addChildCommand.validate());

	}
	@Test
	public void shouldParseCommandCorreclty_AddDaughterToMother() {
		String[] input = { "Mother=Alka", "Daughter=Rachana" };
		JCommander jCommander = new JCommander();
		jCommander.addObject(addChildCommand);
		jCommander.parse(input);
		Assert.assertTrue(addChildCommand.validate());

	}
	@Test
	public void should_Not_ParseCommand_AddDaughterSonToMother() {
		String[] input = { "Mother=Alka", "Daughter=Rachana","Son=Niraj" };
		JCommander jCommander = new JCommander();
		jCommander.addObject(addChildCommand);
		jCommander.parse(input);
		Assert.assertFalse(addChildCommand.validate());

	}
	@Test
	public void should_Not_ParseAnyInvalideCommand() {
		String[] input = { "Random=Alka", "Anthing=Rachana"};
		JCommander jCommander = new JCommander();
		jCommander.addObject(addChildCommand);
		jCommander.addObject(searchRelationCommand);
		jCommander.parse(input);
		Assert.assertFalse(addChildCommand.validate());
	}
	
	


}
