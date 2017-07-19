package com.wheru.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;

import com.google.gson.annotations.Expose;
import com.wheru.Exceptions.DaoException;
import com.wheru.services.DBService;

@Entity
@Table(name = "user_event")
public class UserEvent extends PersistentObject {
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	@Expose
	private User user;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "event_id", nullable = false)
	@Expose
	private Event event;
	@OneToMany(fetch = FetchType.EAGER, mappedBy="userEvent")
	@Expose
	private List<MapCoordinate> mapCoordinates = new ArrayList<MapCoordinate>();
	@Expose
	private String color;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public List<MapCoordinate> getMapCoordinates() {
		return mapCoordinates;
	}
	public void setMapCoordinates(List<MapCoordinate> mapCoordinates) {
		this.mapCoordinates = mapCoordinates;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<UserEvent> getByUser(Long userId) throws DaoException {
    	Session session = null;
    	List<UserEvent> userEvents = new ArrayList<UserEvent>();
	   	try {
	   		session = DBService.instance().getSession();
			session.beginTransaction();
			String hql = "FROM UserEvent WHERE user_id = :userId";

			//TypedQuery<UserEvent> query = (TypedQuery<UserEvent>) session.createQuery(hql);	
			Query query = session.createQuery(hql);
			query.setParameter("userId", userId);
			//userEvents = (ArrayList<UserEvent>)query.getResultList();
			userEvents = (ArrayList<UserEvent>)query.list();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw new DaoException("Error getting user events for user  " + userId + " : " + e.getMessage());
		} finally {
			session.close();
		}
	   	return (ArrayList<UserEvent>)userEvents;
	}
	
	@SuppressWarnings("unchecked")
	public static UserEvent getByUserAndEvent(Long userId, Long eventId) throws DaoException {
    	Session session = null;
    	UserEvent userEvent = null;
	   	try {
	   		session = DBService.instance().getSession();
			session.beginTransaction();
			String hql = "FROM UserEvent UE WHERE user_id = :userId AND event_id = :eventId";

			//TypedQuery<UserEvent> query = (TypedQuery<UserEvent>) session.createQuery(hql);	
			Query query = session.createQuery(hql);
			query.setParameter("userId", userId);
			query.setParameter("eventId", eventId);
			List<UserEvent> userEvents = query.list();
			
			if (userEvents.size() > 0) 
				userEvent = userEvents.get(0);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw new DaoException("Error getting user event for user  " + userId + " and event " + eventId + ": " + e.getMessage());
		} finally {
			session.close();
		}
	   	return userEvent;
	}
}
