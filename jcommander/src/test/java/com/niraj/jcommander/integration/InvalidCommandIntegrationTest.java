package com.niraj.jcommander.integration;

import java.security.InvalidParameterException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.niraj.jcommander.CommondProcessor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvalidCommandIntegrationTest {

	@Autowired
	private CommondProcessor commondProcessor;

	@Before
	public void Cleanup() {
		String[] input = { "Clean=true" };
		commondProcessor.process(input);
	}

	@Test(expected=InvalidParameterException.class)
	public void shouldUpdateWifeToHusband() {
		String[] input1 = { "Random=Ashok" };
		commondProcessor.process(input1);
		

	}

}
