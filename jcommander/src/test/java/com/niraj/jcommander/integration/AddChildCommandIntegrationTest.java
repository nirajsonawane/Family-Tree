package com.niraj.jcommander.integration;

import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.niraj.jcommander.CommondProcessor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddChildCommandIntegrationTest {

	@Autowired
	private CommondProcessor commondProcessor;

	@Before
	public void Cleanup() {
		String[] input = { "Clean=true" };
		 commondProcessor.process(input);
	}

	@Test
	public void shouldAddMaleToFamilyTree() {
		System.out.println("shouldAddMaleToFamilyTree");
		String[] input = { "Male=Ashok" };
		String output = commondProcessor.process(input);
		Assert.assertEquals("Welcome  Ashok", output);
	}
	@Test
	public void shouldAddFeMaleToFamilyTree() {
			System.out.println("shouldAddFeMaleToFamilyTree");
		String[] input = { "Female=Alka" };
		String output = commondProcessor.process(input);
		Assert.assertEquals("Welcome  Alka", output);
	}

	@Test(expected=ConstraintViolationException.class)
	public void shouldThrwoExcpetionForDuplicatePerson() {
		System.out.println("shouldThrwoExcpetionForDuplicatePerson");
		String[] input = { "Female=Alka" };
		commondProcessor.process(input);
		commondProcessor.process(input);
		

	}

}
