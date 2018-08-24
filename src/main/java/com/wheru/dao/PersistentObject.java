/*
 * Copyright (c) 2018. Blue Cask Software
 *
 * Base class for DAO objects.  Provides the fields common to all DB objects, as well
 * as some basic DB access functions.  I use DAO objects to wrap the hibernate calls.
 * This is not typical, but I want to insulate the business logic from the raw data calls.
 */

package com.wheru.dao;

import com.google.gson.annotations.Expose;
import com.wheru.services.DBService;
import org.hibernate.Session;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class PersistentObject {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Expose
	private Long id;
	@Column(name="create_time")
	private Timestamp createTime;
	@Column(name="mod_time")
	private Timestamp modTime;
	@Expose
	private Integer status;
	
	public Long getId() {
		return id;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public Timestamp getModTime() {
		return modTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/*
	 * Simple save function allows us to save a DAO object without having to 
	 * wrap the object in a session every time.
	 */
	public void save() {
		Session session = DBService.instance().getSession();
		try {
			session.beginTransaction();
			session.save(this);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}
	
	/*
	 * Simple update function allows us to update a DAO object without having to 
	 * wrap the object in a session every time.
	 */
	public void update() {
		Session session = DBService.instance().getSession();
		try {
			session.beginTransaction();
			session.update(this);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	/*
	 * Generic static getter, allows us to get an object by ID.  If some lazy fetching
	 * needs to happen, you cannot use this method, as the session is closed after the
	 * core object is retrieved. 
	 */
	protected static <T> T get(Class<T> objectClass, Long id) {
		Session session = DBService.instance().getSession();
		T persistantObject = null;
		
		try {	
			session.beginTransaction();
			System.out.println("Getting " + objectClass.getName() + " with id " + id);
			persistantObject = (T)session.get(objectClass, id);
			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Error running query: " + e.getMessage());
		} finally {
			session.close();
		}
		return persistantObject;
	}
}
