package com.wheru.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "user")
public class User extends PersistentObject {
	@Expose
	private String email;
	@Column(name="display_name")
	@Expose
	private String displayName;
	@Column(name="validation_id")
	private String validationId;
	@Column(name="password_salt")
	private String passwordSalt;
	@Column(name="password_md5")
	private String passwordMd5;
	@OneToMany(fetch = FetchType.LAZY, mappedBy="user")
	private List<UserEvent> userEvents = new ArrayList<UserEvent>();
	
	public String getEmail() {
		return email;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getValidationId() {
		return validationId;
	}
	public void setValidationId(String validationId) {
		this.validationId = validationId;
	}
	public String getPasswordSalt() {
		return passwordSalt;
	}
	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}
	public String getPasswordMd5() {
		return passwordMd5;
	}
	public void setPasswordMd5(String passwordMd5) {
		this.passwordMd5 = passwordMd5;
	}
	public List<UserEvent> getUserEvents() {
		return userEvents;
	}
	public void setUserEvents(List<UserEvent> userEvents) {
		this.userEvents = userEvents;
	}
}
