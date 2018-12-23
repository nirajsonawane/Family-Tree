package com.niraj.jcommander;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.niraj.jcommander.converter.MaleConverter;
import com.niraj.jcommander.converter.PersonConverter;
import com.niraj.jcommander.domain.Person;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MaleConverterTest {

	
	@Test
	public void shouldCreatePersonObject()
	{
		MaleConverter converter = new MaleConverter();
		Person object = converter.convert("Niraj");
		Assert.assertNotNull(object);
		Assert.assertEquals("Niraj", object.getName());
		Assert.assertEquals("Male", object.getGender());
	}
}
