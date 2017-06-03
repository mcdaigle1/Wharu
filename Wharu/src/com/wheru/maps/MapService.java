package com.wheru.maps;

import java.util.ArrayList;

import com.google.gson.Gson;

public class MapService {
	// this is a test array of lat longs
	private ArrayList<ArrayList<Double>> latLongArray;
	private Double leftLong, rightLong, topLat, bottomLat; // bounderies of map
	private String latLongJson;
  
    public MapService() {
    	latLongArray = new ArrayList<ArrayList<Double>>();
    	leftLong = 181.0;
    	rightLong = -181.0;
    	topLat = -91.0;
    	bottomLat = 91.0;
    	
    	// Right now I'm setting these values here for testing.  We want to get
    	// the values from the DB utlimately
    	
    	// james joyce
    	ArrayList<Double> latLongEntry = new ArrayList<Double>();
    	latLongEntry.add(34.417060);
    	latLongEntry.add(-119.696138);
    	latLongArray.add(latLongEntry);
    	
    	// joes
    	latLongEntry = new ArrayList<Double>();
    	latLongEntry.add(34.417764);
    	latLongEntry.add(-119.696577);
    	latLongArray.add(latLongEntry);
    	
    	// Seven
    	latLongEntry = new ArrayList<Double>();
    	latLongEntry.add(34.415215);
    	latLongEntry.add(-119.691784);
    	latLongArray.add(latLongEntry);
    	
    	// set the map bounderies to the outermost lat and long values
    	for (ArrayList<Double> latLongPair : latLongArray) {
    		if (latLongPair.get(0) > topLat) 
    			topLat = latLongPair.get(0);
    		if (latLongPair.get(0) < bottomLat) 
    			bottomLat = latLongPair.get(0);
    		if (latLongPair.get(1) > rightLong) 
    			rightLong = latLongPair.get(1);
    		if (latLongPair.get(1) < leftLong) 
    			leftLong = latLongPair.get(1);
    	}
    	
    	latLongJson = new Gson().toJson(latLongArray);
    }
    
    public ArrayList<ArrayList<Double>> getLatLongArray() {
    	return latLongArray;
    }

	public Double getLeftLong() {
		return leftLong;
	}

	public Double getRightLong() {
		return rightLong;
	}

	public Double getTopLat() {
		return topLat;
	}

	public Double getBottomLat() {
		return bottomLat;
	}

	public String getLatLongJson() {
		return latLongJson;
	}
}
