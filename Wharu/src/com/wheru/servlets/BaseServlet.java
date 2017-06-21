package com.wheru.servlets;

import javax.servlet.http.HttpServlet;

import com.wheru.services.LogService;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = -1489018749104248453L; 
	
	public LogService l = null;

    public BaseServlet() {
        super();  
        l = LogService.instance();
    }
}
