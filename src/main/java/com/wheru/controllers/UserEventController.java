/*
 * Copyright (c) 2018. Blue Cask Software
 *
 *
 */

package com.wheru.controllers;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wheru.Exceptions.GeneralException;
import com.wheru.Exceptions.ParamException;
import com.wheru.dao.UserEvent;
import com.wheru.utilities.ApiResponseUtil;

/*
 * API for user event based requests.  Note that the user event is the central point of reference
 * for both events and users.  The user event DAO object is configured in both Hibernate and GSON 
 * to eager fetch and JSON render its associated user, event and map coordinate objects.
 */
@RestController
public class UserEventController extends BaseController {
       
    public UserEventController() {
        super();      
    }
    
    /*
     * Get all user events by user ID.  
     */
    @RequestMapping("/api/user_events_by_user")
    @GET
    @Produces("application/json")
	public Response userEventsByUser(@RequestParam(value = "user_id", required = false) Long userId) {
    	ArrayList<UserEvent> userEvents = new ArrayList<UserEvent>();
    	
    	try {	
    		valid.mustExist("user_id", userId);
    		userEvents = UserEvent.getByUser(userId);
		} catch(ParamException pe) {
			return ApiResponseUtil.returnError(pe);
		} catch(Exception e) {
			return ApiResponseUtil.returnError(new GeneralException(e, "General error in user events by user call"));
		}
		return ApiResponseUtil.returnSuccess(userEvents);
	}
    
    /*
     * Get all user events by event ID.
     */
    @RequestMapping("/api/user_events_by_event")
    @GET
    @Produces("application/json")
	public Response userEventsByEvent(@RequestParam(value = "event_id", required = false) Long eventId) {
    	ArrayList<UserEvent> userEvents = new ArrayList<UserEvent>();
    	
    	try {	
    		valid.mustExist("event_id", eventId);
    		userEvents = UserEvent.getByEvent(eventId);
		} catch(ParamException pe) {
			return ApiResponseUtil.returnError(pe);
		} catch(Exception e) {
			return ApiResponseUtil.returnError(new GeneralException(e, "General error in user events by event call"));
		}
		return ApiResponseUtil.returnSuccess(userEvents);
	}
}