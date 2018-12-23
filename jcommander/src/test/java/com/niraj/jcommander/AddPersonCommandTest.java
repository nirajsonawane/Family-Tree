package com.niraj.jcommander;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.beust.jcommander.JCommander;
import com.niraj.jcommander.command.AddChildCommand;
import com.niraj.jcommander.command.AddPersonCommand;
import com.niraj.jcommander.command.SearchRelationCommand;
import com.niraj.jcommander.service.FamilyTreeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddPersonCommandTest {

	
	@InjectMocks
	private AddPersonCommand addPersonCommand;

	@Autowired
	private SearchRelationCommand searchRelationCommand;
	
	@Mock
	private FamilyTreeService familyTreeService;
	
	

	@After
	public void Cleanup() {
		addPersonCommand.cleanup();
	}

	@Test
	public void shouldAddMaleToFamilyTree() {
		String[] input = { "Male=Ashok"};
		JCommander jCommander = new JCommander();
		jCommander.addObject(addPersonCommand);
		jCommander.addObject(searchRelationCommand);
		jCommander.parse(input);
		Mockito.doNothing().when(familyTreeService).addPerson(Mockito.any());
		String output = addPersonCommand.run();
		Assert.assertEquals("Welcome  Ashok", output);
		
	}
	@Test
	public void shouldAddFemaleToFamilyTree() {
		String[] input = { "Female=Alka"};
		JCommander jCommander = new JCommander();
		jCommander.addObject(addPersonCommand);
		jCommander.addObject(searchRelationCommand);
		jCommander.parse(input);
		Mockito.doNothing().when(familyTreeService).addPerson(Mockito.any());
		String output = addPersonCommand.run();
		Assert.assertEquals("Welcome  Alka", output);
		
	}


}
