package com.niraj.jcommander.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MutuallyExclusiveFieldValidator implements ConstraintValidator<MutuallyExclusiveFields, Object> {
 
    private String baseField;
    private String matchField;
 
    @Override
    public void initialize(MutuallyExclusiveFields constraint) {
        baseField = constraint.baseField();
        matchField = constraint.matchField();
    }
 
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        try {
            Object baseFieldValue = getFieldValue(object, baseField);
            Object matchFieldValue = getFieldValue(object, matchField);            
            if(null==baseFieldValue && null==matchFieldValue)
            	return false;
            
            if(null!=baseFieldValue && null!=matchFieldValue)
            	return false;
            
            return true;
            
        } catch (Exception e) {
        	e.printStackTrace();
            // log error
            return false;
        }
    }
 
    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        java.lang.reflect.Field passwordField = clazz.getDeclaredField(fieldName);
        passwordField.setAccessible(true);
        return passwordField.get(object);
    }
 
}