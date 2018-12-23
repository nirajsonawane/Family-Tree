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
public class UpdateSpouseCommandIntegrationTest {

	@Autowired
	private CommondProcessor commondProcessor;

	@Before
	public void Cleanup() {
		String[] input = { "Clean=true" };
		commondProcessor.process(input);
	}

	@Test
	public void shouldUpdateWifeToHusband() {
		String[] input1 = { "Male=Ashok" };
		String[] input2 = { "Husband=Ashok", "Wife=Alka" };
		commondProcessor.process(input1);
		String process = commondProcessor.process(input2);
		Assert.assertEquals("Welcome Alka", process);

	}

}
