package com.wheru.Exceptions;

public class InvalidCoordinateException extends Exception {
	private static final long serialVersionUID = -4741375102115525741L;
	
    public InvalidCoordinateException(String message) {
        super(message);
    }
    
	public InvalidCoordinateException(String message, Throwable t) {
		super(message, t);
	}
}
