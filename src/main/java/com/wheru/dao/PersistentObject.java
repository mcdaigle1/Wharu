package com.wheru.dao;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.Session;

import com.google.gson.annotations.Expose;
import com.wheru.services.DBService;

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