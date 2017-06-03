package com.wheru;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wheru.maps.MapService;

/**
 * Servlet implementation class MapTestServlet
 */
@WebServlet("/MapTestServlet")
public class MapTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MapTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MapService mapService = new MapService(); 
		
		request.setAttribute("latLongArray", mapService.getLatLongArray());
		request.setAttribute("topLat", mapService.getTopLat());
		request.setAttribute("bottomLat", mapService.getBottomLat());
		request.setAttribute("leftLong", mapService.getLeftLong());
		request.setAttribute("rightLong", mapService.getRightLong());
		
		System.out.printf("top %f bottom %f left %f right %f",  mapService.getTopLat(), mapService.getBottomLat(), mapService.getLeftLong(), mapService.getRightLong());
		
		request.setAttribute("latLongJson", mapService.getLatLongJson());
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/maps/showMap.jsp");
        dispatcher.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
