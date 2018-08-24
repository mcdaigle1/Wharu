/*
 * Copyright (c) 2018. Blue Cask Software
 *
 *
 */

package com.wheru.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/*
 * MCD TODO determine if this is the way we want to handle exceptions.  
 */
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView handleException (NoHandlerFoundException ex) {
		System.out.println("++++++++++ caught 404");
		ModelAndView mav = new ModelAndView("index");
		//mav.addObject("exception", e);
		// mav.addObject("errorcode", "404");
		return mav;
	}
}