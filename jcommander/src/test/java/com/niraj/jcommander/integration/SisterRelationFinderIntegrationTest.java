package com.niraj.jcommander.integration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.niraj.jcommander.CommondProcessor;
import com.niraj.jcommander.util.InputFileReader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SisterRelationFinderIntegrationTest {

	@Autowired
	private CommondProcessor commondProcessor;

	@Before
	public void Cleanup() {
		String[] input = { "Clean=true" };
		commondProcessor.process(input);
	}

	@Test
	public void shouldFindSisterForPerson() {
		
		InputFileReader reader = new InputFileReader("testInput.txt");
		Stream<String> readFile=null;
		try {
			readFile = reader.readFile();
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		readFile.forEach(str->commondProcessor.process(str.split(" ")));
		String[] input1 = { "Person=Piers", "Relation=Sister" };
		String process = commondProcessor.process(input1);
		Assert.assertTrue(process.contains("Sally"));
		
		String[] input2 = { "Person=Alex", "Relation=Sister" };
		String process2 = commondProcessor.process(input2);
		Assert.assertTrue(process2.contains("Nisha"));
		
		
		String[] input3 = { "Person=Paul", "Relation=Sister" };
		String process3 = commondProcessor.process(input3);
		Assert.assertTrue(process3.contains("Ruth"));
	}
}
