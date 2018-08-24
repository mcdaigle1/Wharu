package com.wheru.controllers;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wheru.Exceptions.GeneralException;
import com.wheru.Exceptions.ParamException;
import com.wheru.Exceptions.PasswordMismatchException;
import com.wheru.Exceptions.UserNotFoundException;
import com.wheru.dao.User;
import com.wheru.services.AuthService;
import com.wheru.utilities.ApiResponseUtil;

/*
 * API for user based requests
 */
@RestController
public class UserController extends BaseController {
       
    public UserController() {
        super();      
    }

    /*
     * Log a user in.  This uses local authorization and may be deprecated.  We may only use facebook 
     * as the auth service.  
     */
    @RequestMapping("/api/user_login")
    @GET
    @Produces("application/json")
	public Response loginUser(@RequestParam(value = "userEmail", required = false) String userEmail,
						      @RequestParam(value = "password", required = false) String password) {
    	HashMap<String, String> payload = new HashMap<String, String>();
    	
    	try {	
    		valid.mustExist("userEmail", userEmail).properEmailFormat(userEmail);
    		valid.mustExist("password", password).minLength("password", password, 8);
    		
			String token = null;
			try {
				token = AuthService.instance().authenticateUser(userEmail, password);
			} catch (UserNotFoundException unfe) {
				return ApiResponseUtil.returnError(unfe);
			} catch (PasswordMismatchException pme) {
				return ApiResponseUtil.returnError(pme);
			}
			payload.put("token", token);

		} catch(ParamException pe) {
			return ApiResponseUtil.returnError(pe);
		} catch(Exception e) {
			return ApiResponseUtil.returnError(new GeneralException(e, "General error in user login call"));
		}
		return ApiResponseUtil.returnSuccess(payload);
	}
    
    /*
     * Get a user record by email.  This is used after login to get the user info needed to get further
     * data from the DB.
     */
    @RequestMapping("/api/user_by_email")
    @GET
    @Produces("application/json")
	public Response userByEmail(@RequestParam(value = "user_email", required = false) String userEmail) {
    	User user = null;
    	
    	try {	
    		valid.mustExist("user_email", userEmail).properEmailFormat(userEmail);
    		user = User.getByEmail(userEmail);

		} catch(ParamException pe) {
			return ApiResponseUtil.returnError(pe);
		} catch(Exception e) {
			return ApiResponseUtil.returnError(new GeneralException(e, "General error in user login call"));
		}
		return ApiResponseUtil.returnSuccess(user);
	}
    
    /* 
     * Create a user record if it does not exist, otherwise update the existing user.  The reason we have 
     * both create or update is so a user can log in with Facebook, but we still end up with a record we can
     * link to events in our DB
     * MCD TODO right now the update doesn't make a ton of sense because there are only two params,  name and email,
     * however, ulitmately, we might have tokens, etc ...
     */
    @RequestMapping("/api/register_user")
    @GET
    @Produces("application/json")
	public Response registerUser(@RequestParam(value = "user_email", required = false) String userEmail,
								 @RequestParam(value = "user_name", required = false) String userName) {
    	User user = null;
    	User user2 = null;
    	
    	try {	
    		valid.mustExist("user_email", userEmail).properEmailFormat(userEmail);
    		user = User.getByEmail(userEmail);
    		
    		if (user == null) {
    			user = new User();
    			user.setEmail(userEmail);
    			user.setDisplayName(userName);
    			user.setStatus(0);
    			user.save();
    		}
    		user2 = User.getDeep(user.getId());
		} catch(ParamException pe) {
			return ApiResponseUtil.returnError(pe);
		} catch(Exception e) {
			return ApiResponseUtil.returnError(new GeneralException(e, "General error in user login call"));
		}
		return ApiResponseUtil.returnSuccess(user2);
	}
}
