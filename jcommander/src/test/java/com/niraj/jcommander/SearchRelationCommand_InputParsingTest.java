package com.niraj.jcommander;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.beust.jcommander.JCommander;
import com.niraj.jcommander.command.SearchRelationCommand;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchRelationCommand_InputParsingTest {

	@Autowired
	private SearchRelationCommand searchRelationCommand;

	@After
	public void Cleanup() {
		searchRelationCommand.cleanup();
	}

	@Test
	public void shouldParseCommandCorreclty_SearchRelation() {
		String[] input = { "Person=Niraj", "Relation=Brother"};
		
		JCommander jCommander = new JCommander();
		jCommander.addObject(searchRelationCommand);
		jCommander.parse(input);
		Assert.assertTrue(searchRelationCommand.validate());

	}
		


}
