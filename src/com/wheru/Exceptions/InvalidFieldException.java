package com.wheru.Exceptions;

public class InvalidFieldException extends Exception {
	private static final long serialVersionUID = -7091500498078790304L;

	public InvalidFieldException(String message) {
        super(message);
    }

	public InvalidFieldException(String message, Throwable t) {
		super(message, t);
	}
}
