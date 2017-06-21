package com.wheru.Exceptions;

public class InvalidParamException extends ParamException {
	private static final long serialVersionUID = -8357004090627359418L;

    public InvalidParamException(String message) {
        super(message);
    }

	public InvalidParamException(String message, Throwable t) {
		super(message, t);
	}
}
