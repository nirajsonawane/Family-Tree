package com.niraj.jcommander;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.niraj.jcommander.converter.FemaleConverter;
import com.niraj.jcommander.converter.MaleConverter;
import com.niraj.jcommander.domain.Person;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FemaleConverterTest {

	
	@Test
	public void shouldCreatePersonObject()
	{
		FemaleConverter converter = new FemaleConverter();
		Person object = converter.convert("Niraj");
		Assert.assertNotNull(object);
		Assert.assertEquals("Niraj", object.getName());
		Assert.assertEquals("Female", object.getGender());
	}
}
