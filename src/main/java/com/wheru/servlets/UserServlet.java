package com.wheru.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wheru.Exceptions.ParamException;
import com.wheru.Exceptions.PasswordMismatchException;
import com.wheru.Exceptions.UserNotFoundException;
import com.wheru.services.AuthService;
import com.wheru.utilities.ApiResponseUtil;

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
			
			HashMap<String, String> payload = new HashMap<String, String>();
			
			switch (action) {
			case "loginUser":
//				String token = null;
//				try {
//					token = AuthService.instance().authenticateUser(request.getParameterMap());
//				} catch (UserNotFoundException unfe) {
//					ApiResponseUtil.returnError(unfe);
//				} catch (PasswordMismatchException pme) {
//					ApiResponseUtil.returnError(pme);
//				}
//				payload.put("token", token);
//				ApiResponseUtil.returnSuccess(payload);
//				break;
			default:
				throw new ParamException("Invalid action: " + action);
			}	
		} catch(ParamException pe) {
			String errorStr = "Parameter error in User call: " + pe.getMessage();
			l.error(errorStr, pe);
			// MCD TODO use something other than sendError.  We want to be able to set the content to a json info object.
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
