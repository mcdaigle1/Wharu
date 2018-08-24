/*
 * Copyright (c) 2018. Blue Cask Software
 *
 *
 */

package com.wheru.services;


import org.apache.commons.validator.routines.EmailValidator;

import com.wheru.Exceptions.InvalidParamException;


public class ValidationService extends BaseService {

	private static final ValidationService _instance = new ValidationService();
	private EmailValidator emailValidator = EmailValidator.getInstance();

	private ValidationService() {
		
	} 
	
	public static ValidationService instance() {
		return _instance;
	}

	public <T> ValidationService mustExist(String paramName, T param) throws InvalidParamException {
		if (param == null)
			throw new InvalidParamException("Required parameter " + paramName + " does not exist.");
		return this;
	}
	
	public ValidationService notEmpty(String paramName, String param) throws InvalidParamException {
		if (param.isEmpty())
			throw new InvalidParamException("Required parameter " + paramName + " is emtpy.");
		return this;
	}
	
	public ValidationService properEmailFormat(String email) throws InvalidParamException {
		if (!emailValidator.isValid(email)) 
			throw new InvalidParamException("Email " + email + " is not valid.");
		return this;
	}
   
	/**
	 * Verify the provided string is at least the provided min length
	 * @param paramName - String paramter name.  This is used for display purposes only
	 * @param param - String param whose min length we want to verify
	 * @param minLength - Integer value holding the min length to verify
	 * @return this ValidationService, which allows us to chain these validations
	 * @throws InvalidParamException if invalid
	 */
	public ValidationService minLength(String paramName, String param, Integer minLength) throws InvalidParamException {
		if (param.length() < minLength) 
			throw new InvalidParamException("Parameter " + paramName + " is shorter than the required " + minLength + " characters.");
		return this;
	}
	
	/**
	 * Verify latitude is between -90 and 90
	 * @param latitude - Double latitude value
	 * @return this ValidationService, which allows us to chain these validations
	 * @throws InvalidParamException if invalid
	 */
	public ValidationService isLatititude(Double latitude) throws InvalidParamException {
		if(latitude > 90 || latitude < -90) 
			throw new InvalidParamException("Parameter " + latitude + " is an out of bounds latitude.");
		return this;
	}
	
	/**
	 * Verify longitude is between -180 and 180
	 * @param longitude - Double latitude value
	 * @return this ValidationService, which allows us to chain these validations
	 * @throws InvalidParamException if invalid
	 */
	public ValidationService isLongitude(Double longitude) throws InvalidParamException {
		if(longitude > 180 || longitude < -180) 
			throw new InvalidParamException("Parameter " + longitude + " is an out of bounds longitude.");
		return this;
	}
}
