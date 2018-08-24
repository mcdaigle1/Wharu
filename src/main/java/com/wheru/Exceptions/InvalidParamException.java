/*
 * Copyright (c) 2018. Blue Cask Software
 *
 *
 */

package com.wheru.Exceptions;

public class InvalidParamException extends ParamException {
	private static final long serialVersionUID = -8357004090627359418L;

    public InvalidParamException(String message) {
        super(message, message);
    }

	public InvalidParamException(String message, String userFriendlyMessage) {
        super(message, userFriendlyMessage);
    }
    
	public InvalidParamException(BaseException be) {
		super(be);
	}
    
	public InvalidParamException(Throwable t, String userFriendlyMessage) {
		super(t, userFriendlyMessage);
	}
}
