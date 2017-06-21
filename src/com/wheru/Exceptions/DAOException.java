package com.wheru.Exceptions;

public class DAOException extends Exception {
	private static final long serialVersionUID = 2976815756049133708L;
	
    public DAOException(String message) {
        super(message);
    }

	public DAOException(String message, Throwable t) {
		super(message, t);
	}
}
