package com.probizbuddy;

import java.security.SecureRandom;

public class GenerateID {
	
	public boolean checkUsed() {
		return false;
	}
	
	public String getID() {
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(100000);
		String formattedID = String.format("%05d", num); 
		if (!checkUsed()) {
			return formattedID;
		} else {
			getID();
		}
		
		return null;
	}
	
}
