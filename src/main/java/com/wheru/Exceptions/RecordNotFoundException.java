package com.wheru.Exceptions;

public class RecordNotFoundException extends BaseException {
	private static final long serialVersionUID = -4741375102115525741L;
	
	public RecordNotFoundException(String message) {
        super(message, message);
    }
	
	public RecordNotFoundException(String message, String userFriendlyMessage) {
        super(message, userFriendlyMessage);
    }
    
	public RecordNotFoundException(BaseException be) {
		super(be);
	}
	
	public RecordNotFoundException(Throwable t, String userFriendlyMessage) {
		super(t, userFriendlyMessage);
	}
}
