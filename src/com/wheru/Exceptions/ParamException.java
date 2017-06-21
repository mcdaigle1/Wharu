package com.wheru.Exceptions;

public class ParamException extends Exception {
	private static final long serialVersionUID = -4741375102115525741L;
	
    public ParamException(String message) {
        super(message);
    }
    
	public ParamException(String message, Throwable t) {
		super(message, t);
	}
}
