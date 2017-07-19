package com.wheru.servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wheru.Exceptions.ParamException;
//import com.wheru.services.EventService;
//import com.wheru.services.UserService;

/**
 * Servlet implementation class EventServlet
 */
@WebServlet("/Event")
public class EventServlet extends BaseServlet implements Servlet {
	private static final long serialVersionUID = 1662232337293824951L;

	/**
     * @see BaseServlet#BaseServlet()
     */
    public EventServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = request.getParameter("action");
			if(action == null)
				throw new ParamException("Action is missing");
			
			switch (action) {
			case "addEvent":
				// MCD TODO create add event stuff here
				break;
			case "getEventInfo":
//				EventService eventService = new EventService();
//				String eventJson = eventService.getEventJson(request.getParameterMap());
//			    response.setContentType("text/plain"); 
//			    response.setCharacterEncoding("UTF-8");
//			    response.getWriter().write(eventJson); 
//				break;
			default:
				throw new ParamException("Invalid action: " + action);
			}	
		} catch(ParamException pe) {
			String errorStr = "Parameter error in Event call: " + pe.getMessage();
			l.error(errorStr, pe);
			// MCD TODO use something other than sendError.  We want to be able to set the content to a json info object.
			response.sendError(400, errorStr);
		} catch(Exception e) {
			String errorStr = "Error in Event call";
			l.error(errorStr, e);
			response.sendError(500, errorStr);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
