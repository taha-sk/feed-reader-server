package com.thcode.feedreader.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.thcode.feedreader.model.Widget;

public class WidgetValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Widget.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "widgetTitle", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "widgetValue", "required");		
	}

}
