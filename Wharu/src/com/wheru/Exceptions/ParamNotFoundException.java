package com.wheru.Exceptions;

public class ParamNotFoundException extends ParamException {
	private static final long serialVersionUID = -4741375102115525741L;
	
    public ParamNotFoundException(String message) {
        super(message);
    }
    
	public ParamNotFoundException(String message, Throwable t) {
		super(message, t);
	}
}
