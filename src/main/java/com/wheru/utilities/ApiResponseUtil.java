/*
 * Copyright (c) 2018. Blue Cask Software
 *
 *
 */

package com.wheru.utilities;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wheru.Exceptions.BaseException;

/**
 * ApiResponseUtil manages formatting and returning http responses
 */
public class ApiResponseUtil {
	
	/**
	 * return a success response with a JSON payload
	 * @param payload
	 * @return
	 */
	public static <T> Response returnSuccess(T payload) {
		String jsonPayload = getSuccessJSON(payload);
		
		return Response.ok(jsonPayload, MediaType.APPLICATION_JSON).build();
	}
	
	/**
	 * Return an error response with JSON payload.  Note that this is for successful
	 * processing (200) but some business error like invalid inputs.
	 * @param payload - HashMap holding payload info
	 * @return
	 */
	public static <T> Response returnError(T payload) {
		String jsonPayload = getErrorJSON(payload);
				
        return Response.ok(jsonPayload, MediaType.APPLICATION_JSON).build();
	}
		
	/**
	 * Return an error response with JSON payload.  Note that this is for successful
	 * processing (200) but some business error like invalid inputs.
	 * @param be - BaseException object holding error info
	 * @return
	 */
	public static Response returnError(BaseException be) {
		String jsonPayload = getErrorJSON(be);
		
        return Response.ok(jsonPayload, MediaType.APPLICATION_JSON).build();
	}
	
	/**
	 * Convert payload hashmap to success response JSON 
	 * @param payload - HashMap holding payload info
	 * @return JSON holding error response info
	 */
	public static <T> String getSuccessJSON(T payload) {
		HashMap<String, Object> responseMap = new HashMap<String, Object>();
		
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		
		responseMap.put("status", "success");
		responseMap.put("payload", payload);
		return gson.toJson(responseMap); 
	}
	
	/**
	 * Convert payload object to error response JSON 
	 * @param payload - HashMap holding payload info
	 * @return JSON holding error response info
	 */
	public static <T> String getErrorJSON(T payload) {
		HashMap<String, String> responseMap = new HashMap<String, String>();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		
		responseMap.put("status", "error");
		responseMap.put("payload", gson.toJson(payload));
		return gson.toJson(responseMap); 
	}
	
	/**
	 * Convert BaseException to error response JSON 
	 * @param be - BaseException object holding error info
	 * @return JSON holding error response info
	 */
	public static String getErrorJSON(BaseException be) {
		HashMap<String, String> payload = new HashMap<String, String>();
		payload.put("detailedMessage", be.getMessage());
		payload.put("userFriendlyMessage", be.getUserFriendlyMessage());
		
		return getErrorJSON(payload);	
	}
}
