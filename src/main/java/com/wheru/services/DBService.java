package com.wheru.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.wheru.dao.Event;
import com.wheru.dao.MapCoordinate;
import com.wheru.dao.User;
import com.wheru.dao.UserEvent;

public class DBService extends BaseService {
	private static final DBService _instance = new DBService();
	
	private StandardServiceRegistry standardServiceRegistry;
	private SessionFactory sessionFactory = null;
	
	private DBService() {
		super();
	}
	
	public static DBService instance() {
		return _instance;
	}
    
	public void shutdown() {
		if(standardServiceRegistry != null)
			StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
		if(sessionFactory != null)
			sessionFactory.close();
	}
	
	public Session getSession() {
		return getSessionFactory().openSession();
	}
	
	private SessionFactory getSessionFactory() {
		
		if(sessionFactory == null) {
			try {
				System.out.println("########## Getting configuration");
	            Configuration configuration = new Configuration();
	            System.out.println("########## configuring");
	            configuration.configure("com/wheru/config/hibernate.cfg.xml")
	            									.addAnnotatedClass(User.class)
	            									.addAnnotatedClass(UserEvent.class)
	            									.addAnnotatedClass(Event.class)
	            									.addAnnotatedClass(MapCoordinate.class);
	            
	            StandardServiceRegistryBuilder standardServiceRegistryBuilder = 
	            		new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	            standardServiceRegistry = standardServiceRegistryBuilder.build();
	            sessionFactory = configuration.buildSessionFactory(standardServiceRegistry);

			} catch (Exception e) {
				if (standardServiceRegistry != null) {
					StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
				}
				System.out.println("Error getting session factory: " + e.getMessage());
				throw e;
			}
		}
		return sessionFactory;
	}
}
