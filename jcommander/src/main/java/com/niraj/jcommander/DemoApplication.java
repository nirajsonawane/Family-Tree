package com.niraj.jcommander;

import java.util.Arrays;
import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	public static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
		CommondProcessor commondProcessor = applicationContext.getBean(CommondProcessor.class);
		String[] test1 = { "Male=Ashok" };
	    String[] test2 = { "Husband=Ashok", "Wife=Alka" };
		String[] test3 = { "Father=Ashok", "Son=Niraj"};		
		String[] test4 = { "Father=Ashok", "Daughter=Rachana"};
		String[] test5 = { "Husband=Niraj", "Wife=Gayatri"};
		String[] test6 = { "Father=Niraj", "Daughter=Sarvadnay"};
		String[] test7 = { "Mother=Rachana", "Son=Arayan"};
		String[] test8 = { "Husband=Arayan", "Wife=Kavita"};
		String[] test9 = { "Mother=Gayatri", "Son=Mohit"};
		
		
		String[] test10 = { "Print=Ashok"};
		String[] test13 = { "Father=Ashok", "Son=Niraj2"};
		String[] test11 = { "Person=Niraj" ,"Relation=Brother"};
		String[] test12 = { "Person=Niraj" ,"Relation=Sister"};
		String[] test14 = { "Person=Ashok" ,"Relation=Son"};
		String[] test15 = { "Mother=Sarvadnay", "Daughter=SarvadnayChild"};
		String[] test16 = { "Person=SarvadnayChild" ,"Relation=grandmother"};
		String[] test17 = { "Person=SarvadnayChild" ,"Relation=grandfather"};
		String[] test18 = { "Person=Ashok" ,"Relation=grandson"};
		String[] test19 = { "Person=Arayan" ,"Relation=cousin"};
		String[] test20 = { "Person=Arayan" ,"Relation=uncle"};
		String[] test21 = { "Person=Arayan" ,"Relation=aunt"};
		
		/*String[] test22 = { "Male=Dilip"};
		String[] test23 = { "Father=Dilip","Daughter=Gayatri" };
		String[] test24 = { "Person=Sarvadnay" ,"Relation=grandfather"};*/
		
		
		//String[] test3 = { "Mother=Alka","Son=Niraj" };
		//String[] test5 = { "Father=Ashok","Son=Niraj" };
		//String[] test4 = { "Person=Niraj","Relation=Brother"};		
		//List<String[]> list = Arrays.asList(test1,test2,test3,test4,test5);
		List<String[]> list = Arrays.asList(test1,test2,test3,test4,test5,test6,test7,test8,test9,test13,test11,test12,test10,test14,test15,test16,
				                           test17,test18,test19,test20,test21);
		//List<String[]> list = Arrays.asList(test1,test2,test10);
		
		
		list.forEach((item) ->commondProcessor.process(item));
		/*while(true)
		{
			System.out.println("Input:");
			Scanner in = new Scanner(System.in);			  
	        String inpoutLine = in.nextLine(); 
	        System.out.println("You entered string "+inpoutLine);	
	        commondProcessor.process(inpoutLine.split(" "));
		}*/
		
			
		//commondProcessor.process(args);
		
		
		
		
/*		SearchRelationCommand searchRelationCommand = applicationContext.getBean(SearchRelationCommand.class);
		UpdateSpouseCommand updateSpouseCommand = applicationContext.getBean(UpdateSpouseCommand.class);
		AddChildCommand addChildCommand = applicationContext.getBean(AddChildCommand.class);
		AddPersonCommand addPersonCommand = applicationContext.getBean(AddPersonCommand.class);
		

		String[] testArgs2 = { "Husband=Niraj", "Wife=Gayatri" };
		String[] testArgs3 = { "Male=Niraj" };
		String[] testArgs1 = { "Husband=Niraj", "Wife=Gayatri" };
	   
	   JCommander.newBuilder()
				.addObject(addPersonCommand)
				.addObject(searchRelationCommand)
				.addObject(updateSpouseCommand)
				.addObject(addChildCommand)
				.build()
				.parse(testArgs2);

		System.out.println(updateSpouseCommand);
		System.out.println(addPersonCommand);
		System.out.println(addPersonCommand.validate());
		 
		
		// System.out.println(validator.validate(addPersonCommand).isEmpty());
*/
	}

}
