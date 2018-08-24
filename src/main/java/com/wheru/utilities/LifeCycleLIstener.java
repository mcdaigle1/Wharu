/*
 * Copyright (c) 2018. Blue Cask Software
 *
 *
 */

package com.wheru.utilities;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.wheru.services.DBService;

	
@WebListener
public class LifeCycleLIstener implements ServletContextListener {

      public void contextInitialized(ServletContextEvent event) {
          //TODO ON START
      }

      public void contextDestroyed(ServletContextEvent event) {
    	  System.out.println("shutting down DB service");
          DBService.instance().shutdown();
      }
}

