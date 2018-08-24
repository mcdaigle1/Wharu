package com.wheru.controllers;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wheru.Exceptions.DaoException;
import com.wheru.Exceptions.InvalidParamException;
import com.wheru.dao.Event;
import com.wheru.utilities.ApiResponseUtil;

/*
 * API for event based requests
 */
@RestController
public class EventController extends BaseController {      
    public EventController() {
        super();      
    }

    /*
     * Get a single event based on the event ID
     */
    @RequestMapping("/api/event")
    @GET
    @Produces("text/plain")
	public Response getEvents(@RequestParam(value = "event_id", required = false) Long eventId) {
    	Event event = null;
    	try {	
    		valid.mustExist("eventId", eventId);
    		event = Event.get(eventId);
		} catch(DaoException daoe) {
			return ApiResponseUtil.returnError(daoe);
		} catch(InvalidParamException ipe) {
			return ApiResponseUtil.returnError(ipe);
		}
		return ApiResponseUtil.returnSuccess(event);
	}

    /*
     * Get all events by user ID.
     */
    @RequestMapping("/api/events_by_user")
    @GET
    @Produces("text/plain")
	public Response getEventsByUser(@RequestParam(value = "user_id", required = false) Long userId) {
    	ArrayList<Event> events = new ArrayList<Event>();
    	try {	
    		valid.mustExist("userId", userId);
    		events = Event.getByUser(userId);
		} catch(DaoException daoe) {
			return ApiResponseUtil.returnError(daoe);
		} catch(InvalidParamException ipe) {
			return ApiResponseUtil.returnError(ipe);
		}
		return ApiResponseUtil.returnSuccess(events);
	}
}
