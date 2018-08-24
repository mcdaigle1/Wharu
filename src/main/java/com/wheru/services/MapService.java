/*
 * Copyright (c) 2018. Blue Cask Software
 *
 *
 */

package com.wheru.services;

import java.sql.Timestamp;
import java.util.Map;

import com.wheru.Exceptions.DaoException;
import com.wheru.Exceptions.InvalidParamException;
import com.wheru.Exceptions.ParamNotFoundException;
import com.wheru.Exceptions.RecordNotFoundException;
import com.wheru.dao.MapCoordinate;
import com.wheru.dao.UserEvent;

public class MapService {
	private static final MapService _instance = new MapService();
	
	private MapService() {
		super();
	}
	
	public static MapService instance() {
		return _instance;
	}
	
    public void addCoordinate(Map<String, String[]> paramMap) 
    		throws ParamNotFoundException, InvalidParamException, DaoException, RecordNotFoundException {

    	
    	Long eventId, userId;
    	Double latitude, longitude;
    	
    	MapCoordinate mapCoordinate = new MapCoordinate();
    	
    	String eventIdStr = null;
    	if(paramMap.get("eventId") == null)
    		throw new ParamNotFoundException("Could not find required parameter 'eventId' in call to addCoordinate.");
    	try {
    		eventIdStr = paramMap.get("eventId")[0];
    		eventId = Long.parseLong(eventIdStr);
    	} catch (NumberFormatException nfe) {
    		throw new InvalidParamException(nfe, "Invalid eventId parameter '" + eventIdStr + "' in call to addCoordinate.");
    	}

    	String userIdStr = null;
    	if(paramMap.get("userId") == null)
    		throw new ParamNotFoundException("Could not find required parameter 'userId' in call to addCoordinate.");
    	try {
    		userIdStr = paramMap.get("userId")[0];
    		userId = Long.parseLong(userIdStr);
    	} catch (NumberFormatException nfe) {
    		throw new InvalidParamException(nfe, "Invalid userId parameter '" + userIdStr + "' in call to addCoordinate.");
    	}
    	
    	String latitudeStr = null;
    	if(paramMap.get("latitude") == null)
    		throw new ParamNotFoundException("Could not find required parameter 'latitude' in call to addCoordinate.");
    	try {
    		latitudeStr = paramMap.get("latitude")[0];
    		latitude = Double.parseDouble(latitudeStr);
    	} catch (NumberFormatException nfe) {
    		throw new InvalidParamException(nfe, "Parameter latitude value " + latitudeStr + " in call to addCoordinate is not a double.");
    	}
    
    	String longitudeStr = null;
    	if(paramMap.get("longitude") == null)
    		// MCD TODO replace with validationService methods
    		throw new ParamNotFoundException("Could not find required parameter 'longitude' in call to addCoordinate.");
    	try {
    		longitudeStr = paramMap.get("longitude")[0];
    		longitude = Double.parseDouble(longitudeStr);
    	} catch (NumberFormatException nfe) {
    		throw new InvalidParamException(nfe, "Parameter longitude value " + longitudeStr + " in call to addCoordinate is not a double.");
    	}
//    	try {
//    		// MCD TODO replace with validationService methods
//    		// MapCoordinate.validateCoordinates(latitude, longitude);
//    	} catch(InvalidCoordinateException ive) {
//    		throw new InvalidParamException(ive, "Parameter latitude " + latitudeStr + " or longitude " + longitudeStr + " in call to addCoordinate is invalid.");
//    	}
    	
    	UserEvent userEvent = UserEvent.getByUserAndEvent(userId, eventId);
    	if(userEvent == null) 
    		throw new RecordNotFoundException("Could not find user event record for user " + userId + ", event " + eventId);
    	
    	mapCoordinate.setUserEvent(userEvent);
    	mapCoordinate.setName("");
    	mapCoordinate.setDescription("");
    	mapCoordinate.setStatus(0);
    	Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    	mapCoordinate.setArrivalTime(currentTime);
    	mapCoordinate.setLatestTime(currentTime);
    	mapCoordinate.setLatitude(latitude);
    	mapCoordinate.setLongitude(longitude);
    	
    	mapCoordinate.save();
    }
}
