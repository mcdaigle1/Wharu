package com.wheru.utilities;

import org.apache.commons.validator.routines.EmailValidator;

import com.wheru.Exceptions.InvalidFieldException;

public final class FieldValidateUtil  {
	public static void validateEmail(String email) throws InvalidFieldException {
		if(!EmailValidator.getInstance().isValid(email)) {
			throw new InvalidFieldException("Invalid email format for 'email'");
		}
	}
}
