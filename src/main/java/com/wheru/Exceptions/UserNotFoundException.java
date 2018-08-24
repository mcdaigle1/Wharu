/*
 * Copyright (c) 2018. Blue Cask Software
 *
 *
 */

package com.wheru.Exceptions;

public class UserNotFoundException extends BaseException {
	private static final long serialVersionUID = -5593482557772890520L;

	public UserNotFoundException(String message) {
        super(message, message);
    }
	
	public UserNotFoundException(String message, String userFriendlyMessage) {
        super(message, userFriendlyMessage);
    }
    
	public UserNotFoundException(BaseException be) {
		super(be);
	}
	
	public UserNotFoundException(Throwable t, String userFriendlyMessage) {
		super(t, userFriendlyMessage);
	}
}
