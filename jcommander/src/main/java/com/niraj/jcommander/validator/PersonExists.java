package com.niraj.jcommander.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PersonExistsValidator.class)
@Target({ ElementType.METHOD,ElementType.ANNOTATION_TYPE,ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonExists {

	String message() default "Person Does Not Exists";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}