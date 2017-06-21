package com.wheru.servlets.maps;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wheru.services.MapService;
import com.wheru.servlets.BaseServlet;

/**
 * Servlet implementation class MapTestServlet
 */
@WebServlet("/MapTestServlet")
public class MapTestServlet extends BaseServlet {
/**
	 * 
	 */
	private static final long serialVersionUID = -4701999979662082799L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MapTestServlet() {
        super();      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			MapService mapService = new MapService();
	
			String eventJson = mapService.getEventJson(new Long(1));
	
			request.setAttribute("eventJson", URLEncoder.encode(eventJson, "UTF-8"));
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/maps/showMap.jsp");
			dispatcher.forward(request, response); 
		} catch(Exception e) {
			System.out.println("Error while getting map: " + e.getMessage());
		}
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write("Success");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
