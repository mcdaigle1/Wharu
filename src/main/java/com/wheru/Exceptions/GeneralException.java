package com.wheru.Exceptions;

public class GeneralException extends BaseException {
	private static final long serialVersionUID = 3065184223635779534L;

	public GeneralException(String message) {
        super(message, message);
    }
	
	public GeneralException(String message, String userFriendlyMessage) {
        super(message, userFriendlyMessage);
    }
    
	public GeneralException(BaseException be) {
		super(be);
	}
	
	public GeneralException(Throwable t, String userFriendlyMessage) {
		super(t, userFriendlyMessage);
	}
}
