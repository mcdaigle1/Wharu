/*
 * Copyright (c) 2018. Blue Cask Software
 *
 * Holds a single map coordinate DB object.  Note that the map coordinate lazy fetches and
 * does not expose user events. This is to avoid circular dependencies with the user event object.
 * Typically, you would retrieve map coordinates via the user event object.
 *
 * I use DAO objects to wrap the hibernate calls. This is not typical, but I want to insulate the
 * business logic from the raw data calls.
 */

package com.wheru.dao;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "map_coordinate")
public class MapCoordinate extends PersistentObject {	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_event_id", nullable = false)
	private UserEvent userEvent;
	@Expose
	private String name;
	@Expose
	private String description;
	@Column(name="arrival_time")
	@Expose
	private Timestamp arrivalTime;
	@Column(name="latest_time")
	@Expose
	private Timestamp latestTime;
	@Expose
	private Double latitude;
	@Expose
	private Double longitude;
	
	public UserEvent getUserEvent() {
		return userEvent;
	}
	public void setUserEvent(UserEvent userEvent) {
		this.userEvent = userEvent;
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
	public Timestamp getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Timestamp arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Timestamp getLatestTime() {
		return latestTime;
	}
	public void setLatestTime(Timestamp latestTime) {
		this.latestTime = latestTime;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
//	public static MapCoordinate get(Integer id) {
//		return (MapCoordinate)get(MapCoordinate.class, id);
//	}
}
