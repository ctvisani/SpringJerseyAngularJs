package com.concretepage.controller;

import java.util.Collections;
import java.util.List;

public class ValidationException extends Exception {
	private static final long serialVersionUID = 1L;

	List<ValidationErrorMessage> _errors;

	public ValidationException(List<ValidationErrorMessage> errors) {
		super();
		_errors = errors;
	}
	
	public ValidationException(ValidationErrorMessage singleError) {
		super();
		_errors = Collections.singletonList(singleError);
	}

	public List<ValidationErrorMessage> getViolations() {
		return _errors;
	}
}