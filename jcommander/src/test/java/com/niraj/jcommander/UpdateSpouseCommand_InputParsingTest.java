package com.niraj.jcommander;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.niraj.jcommander.command.SearchRelationCommand;
import com.niraj.jcommander.command.UpdateSpouseCommand;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateSpouseCommand_InputParsingTest {

	@Autowired
	private UpdateSpouseCommand updateSpouseCommand;
	@Autowired
	private SearchRelationCommand searchRelationCommand;

	@After
	public void Cleanup() {
		updateSpouseCommand.cleanup();
	}

	@Test
	public void shouldParseCommandCorreclty_HusbandFirst() {
		String[] input = { "Husband=Ashok","Wife=Alka"};
		JCommander jCommander = new JCommander();
		jCommander.addObject(updateSpouseCommand);
		jCommander.addObject(searchRelationCommand);
		jCommander.parse(input);
		Assert.assertTrue(updateSpouseCommand.validate());

	}
	
	@Test	
	public void shouldParseCommandCorreclty_WifeFirst() {
		String[] input = { "Wife=Alka","Husband=Ashok"};
		JCommander jCommander = new JCommander();
		jCommander.addObject(updateSpouseCommand);
		jCommander.addObject(searchRelationCommand);
		jCommander.parse(input);
		Assert.assertTrue(updateSpouseCommand.validate());
	}
	@Test(expected=ParameterException.class)
	public void shouldParseCommandCorreclty_RandomWrongInput() {
		String[] input = { "Wife=Alka","Wife=Ashok"};
		JCommander jCommander = new JCommander();
		jCommander.addObject(updateSpouseCommand);
		jCommander.addObject(searchRelationCommand);
		jCommander.parse(input);
		Assert.assertFalse(updateSpouseCommand.validate());

	}

	
	


}
