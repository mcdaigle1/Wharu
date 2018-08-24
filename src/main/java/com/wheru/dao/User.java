/*
 * Copyright (c) 2018. Blue Cask Software
 *
 *
 */

package com.wheru.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;

import com.google.gson.annotations.Expose;
import com.wheru.Exceptions.DaoException;
import com.wheru.services.DBService;

/*
 * Holds a single user DB object.  Note that the user lazy fetches and does not expose
 * user events. This is to avoid circular dependencies with the user event object. If you
 * want to get the user and all associated events, you should query via the user event object
 */
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
	private byte[] passwordSalt;
	@Column(name="encrypted_password")
	private byte[] encryptedPassword;
	@OneToMany(fetch = FetchType.LAZY, mappedBy="user")
	private List<UserEvent> userEvents = new ArrayList<UserEvent>();
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getValidationId() {
		return validationId;
	}
	public void setValidationId(String validationId) {
		this.validationId = validationId;
	}
	public byte[] getPasswordSalt() {
		return passwordSalt;
	}
	public void setPasswordSalt(byte[] passwordSalt) {
		this.passwordSalt = passwordSalt;
	}
	public byte[] getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(byte[] encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public List<UserEvent> getUserEvents() {
		return userEvents;
	}
	public void setUserEvents(List<UserEvent> userEvents) {
		this.userEvents = userEvents;
	}
	
	public static User get(Long userId) {
		return (User)PersistentObject.get(User.class, userId);
	}
	
	public static User getDeep(Long userId) {
		Session session = DBService.instance().getSession();
		User user = null;
		try {	
			session.beginTransaction();
			user = (User)session.get(User.class, userId);
			if (user != null) {
				user.getUserEvents();
			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Error running query: " + e.getMessage());
		} finally {
			session.close();
		}
		
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public static User getByEmail(String email) throws DaoException {
    	Session session = null;
    	User user = null;
	   	try {	   		
	   		session = DBService.instance().getSession();
			session.beginTransaction();
			String hql = "FROM User WHERE email = :email";

			Query query = session.createQuery(hql);	
			query.setParameter("email", email);
			List<User> users = query.list();
			
			if (users.size() > 0) {
				user = users.get(0);
				user.getUserEvents();
			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw new DaoException("Error getting user with email  " + email + ": " + e.getMessage());
		} finally {
			session.close();
		}
	   	return user;
	}
}
