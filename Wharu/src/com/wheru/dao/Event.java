package com.wheru.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

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
	
//	public static Event get(Integer id) {
//		return (Event)get(Event.class, id);
//	}
}
