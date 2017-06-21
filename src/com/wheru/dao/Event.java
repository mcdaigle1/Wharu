package com.wheru.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Session;

import com.google.gson.annotations.Expose;
import com.wheru.Exceptions.DAOException;
import com.wheru.services.DBService;

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
	@Expose
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
	
	public static Event get(Long id) throws DAOException {
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
			throw new DAOException("Error getting event with ID " + id + ": " + e.getMessage());
		} finally {
			session.close();
		}
	   	return event;
	}
}
