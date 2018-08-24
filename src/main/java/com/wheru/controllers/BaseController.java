package com.wheru.controllers;

import com.wheru.services.LogService;
import com.wheru.services.ValidationService;

/*
 * Base controller class.  This provides some core utility functions to 
 * controllers, like logging and validation services
 */
public class BaseController  {
	
	protected LogService l = null;
	protected ValidationService valid = ValidationService.instance();

    public BaseController() {
        l = LogService.instance();
    }
}
