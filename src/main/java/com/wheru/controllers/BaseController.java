package com.wheru.controllers;

import com.wheru.services.LogService;
import com.wheru.services.ValidationService;

public class BaseController  {
	
	protected LogService l = null;
	protected ValidationService valid = ValidationService.instance();

    public BaseController() {
        l = LogService.instance();
    }
}
