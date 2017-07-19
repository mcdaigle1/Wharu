package com.wheru.Exceptions;

public class AuthException extends BaseException {
	private static final long serialVersionUID = 6575815188920655503L;

    public AuthException(String message) {
        super(message, message);
    }
    
	public AuthException(String message, String userFriendlyMessage) {
        super(message, userFriendlyMessage);
    }
    
	public AuthException(BaseException be) {
		super(be);
	}
    
	public AuthException(Throwable t, String userFriendlyMessage) {
		super(t, userFriendlyMessage);
	}
}
