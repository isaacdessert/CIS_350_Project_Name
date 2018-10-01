package com.probizbuddy;

import java.security.SecureRandom;

/** Random ID generator for managers and employees. */
public class GenerateID {
	
	/** Check to see whether the ID already exists.
	 * @return whether it exists. */
	public boolean checkUsed() {
		return false;
	}
	
	/** Generate a random 5 digit ID.
	 * 	@return String of 5 random numbers */
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
