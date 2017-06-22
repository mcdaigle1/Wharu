package com.wheru.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wheru.Exceptions.ParamException;
import com.wheru.services.MapService;
import com.wheru.servlets.BaseServlet;

/**
 * Servlet implementation class MapCoordinateServlet
 */
@WebServlet(description = "Handle map coordinate requests", urlPatterns = { "/MapCoordinate" })
public class MapCoordinateServlet extends BaseServlet {

	private static final long serialVersionUID = -6920115174099378227L;

       
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
			if(action == null)
				throw new ParamException("Action is missing");
			
			switch (action) {
			case "addCoordinate":
				MapService mapService = new MapService();
				mapService.addCoordinate(request.getParameterMap());
				break;
			default:
				throw new ParamException("Invalid action: " + action);
			}	
		} catch(ParamException pe) {
			String errorStr = "Parameter error in MapCoordinate call: " + pe.getMessage();
			l.error(errorStr, pe);
			response.sendError(400, errorStr);
		} catch(Exception e) {
			String errorStr = "Error in MapCoordinate call";
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
