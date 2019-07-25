package com.jck.mpa.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StudentFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Student.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Student student = (Student) target;
		
		if(student.getName() == null || student.getName().isEmpty()) {
			
			errors.rejectValue("name", "required.field");
		}
		
	}
}
