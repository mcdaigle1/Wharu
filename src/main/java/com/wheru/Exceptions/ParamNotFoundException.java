/*
 * Copyright (c) 2018. Blue Cask Software
 *
 *
 */

package com.wheru.Exceptions;

public class ParamNotFoundException extends ParamException {
	private static final long serialVersionUID = -4741375102115525741L;
	
	public ParamNotFoundException(String message) {
        super(message, message);
    }
	
	public ParamNotFoundException(String message, String userFriendlyMessage) {
        super(message, userFriendlyMessage);
    }
    
	public ParamNotFoundException(BaseException be) {
		super(be);
	}
	
	public ParamNotFoundException(Throwable t, String userFriendlyMessage) {
		super(t, userFriendlyMessage);
	}
}
