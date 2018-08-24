/*
 * Copyright (c) 2018. Blue Cask Software
 *
 * Holds a single event DB object.  Note that the event lazy fetches and does not expose
 * user events. This is to avoid circular dependencies with the user event object. Typically,
 * The event object would be retrieved via the user event.
 *
 * I use DAO objects to wrap the hibernate calls. This is not typical, but I want to insulate the
 * business logic from the raw data calls.
 */

package com.wheru.dao;

import com.google.gson.annotations.Expose;
import com.wheru.Exceptions.DaoException;
import com.wheru.services.DBService;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/*
 * MCD TODO - add a default location (lat/long?) so we have a place to start the map if there
 * are not yet any coordinates
 */
@Entity
@Table(name = "event")
public class Event extends PersistentObject {
	@Column(name="owner_id")
	private Long ownerId;
	@Expose
	private String name;
	@Expose
	private String description;
	@OneToMany(fetch = FetchType.LAZY, mappedBy="event")
	private List<UserEvent> userEvents = new ArrayList<UserEvent>();
	@Column(name="start_time")
	@Expose
	private Timestamp startTime;
	@Column(name="end_time")
	@Expose
	private Timestamp endTime;
	
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public List<UserEvent> getUserEvents() {
		return userEvents;
	}
	public void setUserEvents(List<UserEvent> userEvents) {
		this.userEvents = userEvents;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * Custom getter (as opposed to calling the PersistentObject getter) since we need
	 * to initialize the user events as well.  MCD TODO, look into removing lazy initialization
	 * on the user events.
	 * @param id - Long id of event to retrieve
	 * @return Event object that was retrieved
	 * @throws DaoException
	 */
	public static Event get(Long id) throws DaoException {
    	Session session = DBService.instance().getSession();
    	Event event = null;
	   	try {
			session.beginTransaction();
			event = (Event)session.get(Event.class, id);
			for(UserEvent userEvent : event.getUserEvents()) {
				userEvent.getUser();
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw new DaoException("Error getting event with ID " + id + ": " + e.getMessage());
		} finally {
			session.close();
		}
	   	return event;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Event> getByUser(Long userId) throws DaoException {
    	Session session = null;
    	List<Event> events = new ArrayList<Event>();
	   	try {
	   		session = DBService.instance().getSession();
			session.beginTransaction();
			String hql = "FROM event WHERE user_id = :userId";

			Query query = session.createQuery(hql);
			query.setParameter("userId", userId);
			events = query.list();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw new DaoException("Error getting events for user  " + userId + " : " + e.getMessage());
		} finally {
			session.close();
		}
	   	return (ArrayList<Event>)events;
	}
		
}
