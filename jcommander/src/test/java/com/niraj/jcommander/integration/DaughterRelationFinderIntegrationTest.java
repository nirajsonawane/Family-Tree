package com.niraj.jcommander.integration;

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
public class DaughterRelationFinderIntegrationTest {

	@Autowired
	private CommondProcessor commondProcessor;

	@Before
	public void Cleanup() {
		String[] input = { "Clean=true" };
		commondProcessor.process(input);
	}

	@Test
	public void shouldFindDaughterForFather() {
		String[] input1 = { "Male=Ashok" };
		String[] input2 = { "Husband=Ashok", "Wife=Alka" };
		String[] input3 = { "Father=Ashok", "Daughter=Niraj" };
		String[] input4 = { "Person=Ashok", "Relation=Daughter" };
		commondProcessor.process(input1);
		commondProcessor.process(input2);
		commondProcessor.process(input3);
		String process = commondProcessor.process(input4);
		Assert.assertTrue(process.contains("Niraj"));

	}
	@Test
	public void shouldFindSonForMother() {
		String[] input1 = { "Male=Ashok" };
		String[] input2 = { "Husband=Ashok", "Wife=Alka" };
		String[] input3 = { "Father=Ashok", "Daughter=Niraj" };
		String[] input4 = { "Person=Alka", "Relation=Daughter" };
		commondProcessor.process(input1);
		commondProcessor.process(input2);
		commondProcessor.process(input3);
		String process = commondProcessor.process(input4);
		Assert.assertTrue(process.contains("Niraj"));

	}
	@Test
	public void shouldFindSonForFatherMother_MultipleSon() {
		String[] input1 = { "Male=Ashok" };
		String[] input2 = { "Husband=Ashok", "Wife=Alka" };
		String[] input3 = { "Father=Ashok", "Daughter=Niraj" };
		String[] input4 = { "Father=Ashok", "Daughter=Niraj2" };
		String[] input5 = { "Person=Alka", "Relation=Daughter" };
		String[] input6 = { "Person=Ashok", "Relation=Daughter" };
		commondProcessor.process(input1);
		commondProcessor.process(input2);
		commondProcessor.process(input3);
		commondProcessor.process(input4);
		String process = commondProcessor.process(input5);
		String process2 = commondProcessor.process(input6);
		Assert.assertTrue(process.contains("Niraj"));
		Assert.assertTrue(process.contains("Niraj2"));
		Assert.assertTrue(process2.contains("Niraj"));
		Assert.assertTrue(process2.contains("Niraj2"));

	}

}
