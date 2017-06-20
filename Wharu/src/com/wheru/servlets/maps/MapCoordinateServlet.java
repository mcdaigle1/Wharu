package com.wheru.servlets.maps;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wheru.Exceptions.ParamException;
import com.wheru.maps.MapService;

/**
 * Servlet implementation class MapCoordinateServlet
 */
@WebServlet(description = "Handle map coordinate requests", urlPatterns = { "/MapCoordinate" })
public class MapCoordinateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MapCoordinateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = request.getParameter("action");
			
			switch (action) {
			case "addCoordinate":
				MapService mapService = new MapService();
				mapService.addCoordinate(request.getParameterMap());
				break;
			default:
				System.out.println("ERROR: in MapCoordinateServlet - could not find action: " + action);
				break;
			}	
		} catch(ParamException pe) {
			// MCD TODO replace with logger
			String errorStr = "Error with parameters while setting map coordinates: " + pe.getMessage() + ": " + pe.getClass().getName();
			System.out.println(errorStr);
			for (StackTraceElement element : pe.getStackTrace())
				System.out.println(element.toString());
			response.sendError(400, errorStr);
		} catch(Exception e) {
			// MCD TODO replace with logger
			String errorStr = "Error while setting map coordinates: " + e.getMessage() + ": " + e.getClass().getName();
			System.out.println(errorStr);
			for (StackTraceElement element : e.getStackTrace())
				System.out.println(element.toString());
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
