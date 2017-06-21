package com.wheru.dao;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.wheru.Exceptions.InvalidCoordinateException;

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
	
	public static void validateCoordinates(Double latitude, Double longitude) throws InvalidCoordinateException {
		StringBuilder errorMessage = null;
		if(latitude > 90 || latitude < -90) {
			if(errorMessage == null)
				errorMessage = new StringBuilder("");
			errorMessage.append("Latitude " + latitude + " is out of bounds. ");
		}
		if(longitude > 180 || longitude < -180) {
			if(errorMessage == null)
				errorMessage = new StringBuilder("");
			errorMessage.append("Longitude " + longitude + " is out of bounds. ");
		}
		
		if(errorMessage != null) 
			throw new InvalidCoordinateException(errorMessage.toString());
 			
	}
	
//	public static MapCoordinate get(Integer id) {
//		return (MapCoordinate)get(MapCoordinate.class, id);
//	}
}
