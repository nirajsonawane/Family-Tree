package com.niraj.jcommander;

import java.security.InvalidParameterException;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.beust.jcommander.JCommander;
import com.niraj.jcommander.command.AddChildCommand;
import com.niraj.jcommander.command.Command;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddChildCommandTest {

	@Autowired
	private AddChildCommand addChildCommand;

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

}
