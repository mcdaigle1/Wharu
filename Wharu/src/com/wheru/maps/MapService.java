package com.wheru.maps;

import java.sql.Timestamp;
import java.util.Map;

import org.hibernate.Session;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wheru.database.DBService;
import com.wheru.dao.Event;
import com.wheru.dao.MapCoordinate;
import com.wheru.dao.UserEvent;

public class MapService {
    public void addCoordinate(Map<String, String[]> paramMap) {
    	
    	MapCoordinate mapCoordinate = new MapCoordinate();
    	
    	String eventIdStr = paramMap.get("eventId")[0];

    	String userIdStr = paramMap.get("userId")[0];
    	//mapCoordinate.setUserId(Long.parseLong(userIdStr));
    	mapCoordinate.setName("");
    	mapCoordinate.setDescription("");
    	mapCoordinate.setStatus(1);
    	Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    	mapCoordinate.setArrivalTime(currentTime);
    	mapCoordinate.setLatestTime(currentTime);
    	String latitudeStr = paramMap.get("latitude")[0];
    	mapCoordinate.setLatitude(Double.parseDouble(latitudeStr));
    	String longitudeStr = paramMap.get("latitude")[0];
    	mapCoordinate.setLongitude(Double.parseDouble(longitudeStr));
    	
    	Session session = DBService.instance().getSession();
		try {
			session.beginTransaction();
			Event event = (Event)session.get(Event.class, 1);
			//event.getMapCoordinates().size();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}	
    }
		
    		
    		
    
    	
//    	MapCoordinateDAO mapCoordinateDAO = MapCoordinateDAO.getSingleton();
//    	mapCoordinateDAO.put(mapCoordinate);

	
//	public ArrayList<MapCoordinate> getMapCoordinatesForEvent(Integer eventId) {
//    	MapCoordinateDAO mapCoordinateDAO = MapCoordinateDAO.getSingleton();
//    	ArrayList<MapCoordinate> mapCoordinateArray = null;
//    	try {
//    		mapCoordinateArray = mapCoordinateDAO.getForEvent(eventId);
//    	} catch (Exception e) {
//    		System.out.println("Error getting map coordinates for event: " + e.getMessage());
//    	}
//    	return mapCoordinateArray;
//    }

	public String getEventJson(Long eventId) {
    	Session session = DBService.instance().getSession();
    	Event event = null;
    	try {
			session.beginTransaction();
			event = (Event)session.get(Event.class, eventId);
			for(UserEvent userEvent : event.getUserEvents()) {
				userEvent.getUser();
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Error getting session and map coordinates: " + e.getMessage());
		} finally {
			session.close();
		}
    	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    	return gson.toJson(event);
	}
}
