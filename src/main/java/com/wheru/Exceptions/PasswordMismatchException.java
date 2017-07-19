package com.wheru.Exceptions;

public class PasswordMismatchException extends BaseException {
	private static final long serialVersionUID = 1925361571399392770L;

	public PasswordMismatchException(String message) {
        super(message, message);
    }
	
	public PasswordMismatchException(String message, String userFriendlyMessage) {
        super(message, userFriendlyMessage);
    }
    
	public PasswordMismatchException(BaseException be) {
		super(be);
	}
	
	public PasswordMismatchException(Throwable t, String userFriendlyMessage) {
		super(t, userFriendlyMessage);
	}
}
