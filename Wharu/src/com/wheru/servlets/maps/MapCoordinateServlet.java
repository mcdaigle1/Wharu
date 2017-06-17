package com.wheru.servlets.maps;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		switch (action) {
		case "addCoordinate: ":
			MapService mapService = new MapService();
			mapService.addCoordinate(request.getParameterMap());
			break;
		default:
			System.out.println("ERROR: in MapCoordinateServlet - could not find action: " + action);
			break;
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
