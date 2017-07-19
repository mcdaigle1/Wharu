package com.wheru.services;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wheru.Exceptions.DaoException;
import com.wheru.Exceptions.InvalidParamException;
import com.wheru.Exceptions.ParamException;
import com.wheru.Exceptions.ParamNotFoundException;
import com.wheru.dao.Event;
import com.wheru.dao.User;

public class EventService extends BaseService {

	public EventService() {
		super();
	}

    public void addEvent(Map<String, String[]> paramMap)
    		throws ParamException, DaoException {
    	// MCD TODO complete the implementation
    	Long eventId = null;
    	String eventIdStr = null;
    	if(paramMap.get("eventId") == null)
    		throw new ParamNotFoundException("Could not find required parameter 'eventId' in call to addEvent.");
    	try {
    		eventIdStr = paramMap.get("eventId")[0];
    		eventId = Long.parseLong(eventIdStr);
    	} catch (NumberFormatException nfe) {
    		throw new InvalidParamException(nfe, "Invalid eventId parameter '" + eventIdStr + "' in call to addEvent");
    	}
    	
    	String displayName = null;
    	if(paramMap.get("displayName") == null)
    		throw new ParamNotFoundException("Could not find required parameter 'displayName' in call to addEvent.");
    	displayName = paramMap.get("displayName")[0];
    	
    	String validationId = null;
    	
    	if(paramMap.get("validationId") == null) {
    		//validationId = generateValidationId();
    	} else {
    		validationId = paramMap.get("validationId")[0];
    	}
    	
    	User user = new User();
    	user.setStatus(0);
    	//user.setEmail(email);
    	user.setDisplayName(displayName);
    	user.setValidationId(validationId);
    	
    	user.save();
    }

	public String getEventJson(Long eventId) throws ParamException, DaoException {	
		Event event = Event.get(eventId);
    	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    	return gson.toJson(event);
	}
}
