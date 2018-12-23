package com.niraj.jcommander;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Validation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.niraj.jcommander.util.InputFileReader;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	public static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
 
	public static void main(String[] args) throws IOException, URISyntaxException {
 
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		CommondProcessor commondProcessor = applicationContext.getBean(CommondProcessor.class);

		System.out.println(
				"To Load FamilyTree From ClassPath File input.txt Type Yes or No To build using command Line :");
		Scanner in = new Scanner(System.in);
		String inpoutLine = in.nextLine();
		System.out.println("You entered string " + inpoutLine);

		if (inpoutLine.equalsIgnoreCase("Yes")) {
			InputFileReader reader = new InputFileReader("input.txt");
			Stream<String> readFile = reader.readFile();
			List<String[]> collect = readFile.map(str -> str.split(" ")).collect(Collectors.toList());
			collect.forEach(item -> commondProcessor.process(item));
		}
		while (true) {
			System.out.println("Input :: ");
			inpoutLine = in.nextLine();
			System.out.println("You entered string " + inpoutLine);
			System.out.println("************** OutPut ****************");
			commondProcessor.process(inpoutLine.split(" "));
		}

	}

}
