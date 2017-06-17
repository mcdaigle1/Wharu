package com.wheru.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DBService {
	private static final DBService _instance = new DBService();
	
	private StandardServiceRegistry registry;
	private SessionFactory sessionFactory = null;
	
	private DBService() {
	}
	
	public static DBService instance() {
		return _instance;
	}
    
	public void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
	public Session getSession() {
		return getSessionFactory().openSession();
	}
	
	private SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				// Create registry
				registry = new StandardServiceRegistryBuilder()
						.configure("com/wheru/config/hibernate.cfg.xml").build();

				// Create MetadataSources
				MetadataSources sources = new MetadataSources(registry);

				// Create Metadata
				Metadata metadata = sources.getMetadataBuilder().build();

				// Create SessionFactory
				sessionFactory = metadata.getSessionFactoryBuilder().build();

			} catch (Exception e) {
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
				System.out.println("Error getting session factory: " + e.getMessage());
			}
		}
		return sessionFactory;
	}
}
