package com.wheru.Exceptions;

public class RecordNotFoundException extends Exception {
	private static final long serialVersionUID = -4741375102115525741L;
	
    public RecordNotFoundException(String message) {
        super(message);
    }
    
	public RecordNotFoundException(String message, Throwable t) {
		super(message, t);
	}
}
