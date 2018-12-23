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
import com.niraj.jcommander.command.SearchRelationCommand;
import com.niraj.jcommander.service.FamilyTreeService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class AddChildCommandTest {

	
	@InjectMocks
	private AddChildCommand addChildCommand;

	@Autowired
	private SearchRelationCommand searchRelationCommand;
	
	@Mock
	private FamilyTreeService familyTreeService;
	
	

	@After
	public void Cleanup() {
		addChildCommand.cleanup();
	}

	@Test
	public void shouldAddSonToFamilyTree() {
		String[] input = { "Father=Ashok", "Son=Niraj"};
		JCommander jCommander = new JCommander();
		jCommander.addObject(addChildCommand);
		jCommander.addObject(searchRelationCommand);
		jCommander.parse(input);
		Mockito.doNothing().when(familyTreeService).addChild(Mockito.any(), Mockito.any());
		String output = addChildCommand.run();
		Assert.assertEquals("Welcome  Niraj", output);
		
	}
	@Test
	public void shouldAddDaughterToFamilyTree() {
		String[] input = { "Father=Ashok", "Daughter=Rachana"};
		JCommander jCommander = new JCommander();
		jCommander.addObject(addChildCommand);
		jCommander.addObject(searchRelationCommand);
		jCommander.parse(input);
		Mockito.doNothing().when(familyTreeService).addChild(Mockito.any(), Mockito.any());
		String output = addChildCommand.run();
		Assert.assertEquals("Welcome  Rachana", output);
		
	}


}
