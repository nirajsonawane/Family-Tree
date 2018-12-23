package com.niraj.jcommander.command;

import javax.validation.Validation;
import javax.validation.Validator;

public interface Command<T> {

	public static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	public T run();
	
	public void cleanup();

	public default boolean validate() {	
		return validator.validate(this).isEmpty();
	}

}
