/*
 * Copyright (c) 2018. Blue Cask Software
 *
 *
 */

package com.wheru.Exceptions;

public class DaoException extends BaseException {
	private static final long serialVersionUID = 2976815756049133708L;
	
    public DaoException(String message) {
        super(message, message);
    }
    
	public DaoException(String message, String userFriendlyMessage) {
        super(message, userFriendlyMessage);
    }
    
	public DaoException(BaseException be) {
		super(be);
	}
    
	public DaoException(Throwable t, String userFriendlyMessage) {
		super(t, userFriendlyMessage);
	}
}