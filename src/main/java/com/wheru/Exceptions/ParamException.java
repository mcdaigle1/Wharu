package com.wheru.Exceptions;

public class ParamException extends BaseException {
	private static final long serialVersionUID = -4741375102115525741L;
	
    public ParamException(String message) {
        super(message, message);
    }
    
	public ParamException(String message, String userFriendlyMessage) {
        super(message, userFriendlyMessage);
    }
    
	public ParamException(BaseException be) {
		super(be);
	}
    
	public ParamException(Throwable t, String userFriendlyMessage) {
		super(t, userFriendlyMessage);
	}
}
