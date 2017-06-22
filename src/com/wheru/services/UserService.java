package com.wheru.services;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;

import com.wheru.Exceptions.DAOException;
import com.wheru.Exceptions.InvalidFieldException;
import com.wheru.Exceptions.InvalidParamException;
import com.wheru.Exceptions.ParamNotFoundException;
import com.wheru.dao.User;
import com.wheru.utilities.FieldValidateUtil;

public class UserService extends BaseService {

	public UserService() {
		super();
	}

    public void addUser(Map<String, String[]> paramMap)
    		throws ParamNotFoundException, InvalidParamException, DAOException {
    	
    	String email = null;
    	if(paramMap.get("email") == null)
    		throw new ParamNotFoundException("Could not find required parameter 'email' in call to addUser.");
    	try {
    		email = paramMap.get("email")[0];
    		FieldValidateUtil.validateEmail(email);
    	} catch (InvalidFieldException ife) {
    		throw new InvalidParamException("Invalid email parameter '" + email + "' in call to addUser: " + ife.getMessage(), ife);
    	}
    	
    	String displayName = null;
    	if(paramMap.get("displayName") == null)
    		throw new ParamNotFoundException("Could not find required parameter 'displayName' in call to addUser.");
    	displayName = paramMap.get("displayName")[0];
    	
    	String validationId = null;
    	
    	if(paramMap.get("validationId") == null) {
    		validationId = generateValidationId();
    	} else {
    		validationId = paramMap.get("validationId")[0];
    	}
    	
    	User user = new User();
    	user.setStatus(0);
    	user.setEmail(email);
    	user.setDisplayName(displayName);
    	user.setValidationId(validationId);
    	
    	user.save();
    }
    
    public String generateValidationId() {
    	SecureRandom random = new SecureRandom();
    	return new BigInteger(130, random).toString(32);
    }
}
