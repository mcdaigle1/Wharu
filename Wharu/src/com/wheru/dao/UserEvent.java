package com.wheru.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "user_event")
public class UserEvent extends PersistentObject {
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	@Expose
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id", nullable = false)
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
}
