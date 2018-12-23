package com.niraj.jcommander.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MutuallyExclusiveFieldValidator.class})
public @interface MutuallyExclusiveFields {
 
    String message() default "{Two Fields Can not be Present at same Time}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
 
    String baseField();
 
    String matchField();
 
}