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
public class AddPersonCommandIntegrationTest {

	@Autowired
	private CommondProcessor commondProcessor;
 
	@Before
	public void Cleanup() { 
		String[] input = { "Clean=true" };
		 commondProcessor.process(input);
	}

	@Test 
	public void shouldAddsonToFamilyTree_SingleFather() {
	
		String[] input1 = { "Male=Ashok"};
		String[] input2 = { "Father=Ashok", "Son=Niraj"};
		String[] input4 = { "Father=Ashok", "Son=Niraj2"};
		String[] input5 = { "Person=Ashok" ,"Relation=Son"};
		commondProcessor.process(input1);
		commondProcessor.process(input2);
		commondProcessor.process(input4);
		String sons = commondProcessor.process(input5);
		Assert.assertTrue(sons.contains("Niraj"));
		Assert.assertTrue(sons.contains("Niraj2"));
	}
	@Test 
	public void shouldAddsonToFamilyTree_SingleMother() {
	
		String[] input1 = { "Female=Ashok"};
		String[] input2 = { "Mother=Ashok", "Son=Niraj"};
		String[] input4 = { "Mother=Ashok", "Son=Niraj2"};
		String[] input5 = { "Person=Ashok" ,"Relation=Son"};
		commondProcessor.process(input1);
		commondProcessor.process(input2);
		commondProcessor.process(input4);
		String sons = commondProcessor.process(input5);
		Assert.assertTrue(sons.contains("Niraj"));
		Assert.assertTrue(sons.contains("Niraj2"));
	}
	
	@Test(expected=ConstraintViolationException.class) 
	public void shouldAddsonToFamilyTree_MotherDoesNotExits() {
		String[] input2 = { "Mother=Ashok", "Son=Niraj"};
		commondProcessor.process(input2);
		
	}
	@Test(expected=ConstraintViolationException.class) 
	public void shouldAddsonToFamilyTree_FatherDoesNotExits() {
		String[] input2 = { "Father=Ashok", "Son=Niraj"};
		commondProcessor.process(input2);
		
	}
	
	@Test
	public void shouldAddsonToBothMotherAndFather() {
		String[] input1 = { "Male=Ashok"};
		String[] input2 = { "Husband=Ashok", "Wife=Alka"};
		String[] input3 = { "Father=Ashok", "Son=Niraj"};
		String[] input4 = { "Person=Alka" ,"Relation=Son"};
		commondProcessor.process(input1);
		commondProcessor.process(input2);
		commondProcessor.process(input3);
		String process = commondProcessor.process(input4);
		Assert.assertTrue(process.contains("Niraj"));
	}
	
}
