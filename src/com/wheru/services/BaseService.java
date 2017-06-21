package com.wheru.services;

public class BaseService {
	protected LogService l = null;
	
	BaseService() {
		l = LogService.instance();
	}
}
