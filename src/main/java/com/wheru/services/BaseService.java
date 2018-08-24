package com.wheru.services;

/* 
 * Base class for services
 */
public class BaseService {
	protected LogService l = null;
	
	BaseService() {
		l = LogService.instance();
	}
}
