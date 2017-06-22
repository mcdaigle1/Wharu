package com.wheru.servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wheru.Exceptions.ParamException;
import com.wheru.services.UserService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/User")
public class UserServlet extends BaseServlet implements Servlet {
	private static final long serialVersionUID = -6854581740498291431L;

	/**
     * @see BaseServlet#BaseServlet()
     */
    public UserServlet() {
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
			case "addUser":
				UserService userService = new UserService();
				userService.addUser(request.getParameterMap());
				break;
			default:
				throw new ParamException("Invalid action: " + action);
			}	
		} catch(ParamException pe) {
			String errorStr = "Parameter error in User call: " + pe.getMessage();
			l.error(errorStr, pe);
			response.sendError(400, errorStr);
		} catch(Exception e) {
			String errorStr = "Error in User call";
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
