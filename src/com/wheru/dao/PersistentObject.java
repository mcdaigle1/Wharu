package com.wheru.dao;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.Session;

import com.google.gson.annotations.Expose;
import com.wheru.Exceptions.DAOException;
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
	
	public void save() throws DAOException {	
		Session session = null;

		try {
			session = DBService.instance().getSession();
			session.beginTransaction();
			session.save(this);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw new DAOException("Could not save object to db: " + e.getMessage(), e);
		} finally {
			session.close();
		}
	}

//	public static <T> T get(Class<T> objectClass, Integer id) {
//		Session session = DBService.instance().getSession();
//		T persistantObject = null;
//		
//		try {	
//			session.beginTransaction();
//			System.out.println("Getting " + objectClass.getName() + " with id " + id);
//			persistantObject = (T)session.get(objectClass, id);
//			session.getTransaction().commit();
//
//		} catch (Exception e) {
//			session.getTransaction().rollback();
//			System.out.println("Error running query: " + e.getMessage());
//		} finally {
//			session.close();
//		}
//		return persistantObject;
//	}
}
