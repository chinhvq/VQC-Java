package com.luv2code.springdemo.mvc.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target({ FIELD, METHOD })
@Constraint(validatedBy = CourseCodeConstraintValidator.class)
public @interface CourseCode {

	// default course code
	public String[] value() default {"LUV", "TOP"};	

	// default error message
	public String message() default "must start with LUV or TOP";

	// default group
	public Class<?>[] groups() default {};

	// default pay-loads
	public Class<? extends Payload>[] payload() default {};

}
