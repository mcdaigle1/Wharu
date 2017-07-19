package com.wheru.Exceptions;

public class BaseException extends Exception {
	private static final long serialVersionUID = 1918705786345244343L;
	
	private String userFriendlyMessage;

	public BaseException(String message, String userFriendlyMessage) {
        super(message);
        setUserFriendlyMessage(userFriendlyMessage);
    }
    
	public BaseException(Throwable t, String userFriendlyMessage) {
		super(t);
		this.userFriendlyMessage = userFriendlyMessage;
	}
	
	public BaseException(BaseException be) {
		super(be);
		this.userFriendlyMessage = be.userFriendlyMessage;
		
	}
	
	public String getUserFriendlyMessage() {
		return userFriendlyMessage;
	}

	public void setUserFriendlyMessage(String userFriendlyMessage) {
		this.userFriendlyMessage = userFriendlyMessage;
	}
}
