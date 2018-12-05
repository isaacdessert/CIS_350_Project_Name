package com.probizbuddy;

/** Defines a user. */
interface User {
	
	/** Getter method.
	 * @return user id */
	String getID();
	
	/** Getter method.
	 * @return user name */
	String getName();
	
	/** Getter method.
	 * @return user password */
	String getPassword();
	
	/** Setter method.
	 * @param pID their unique id */
    void setID(String pID);
    
	
	/** Setter method.
	 * @param name their name */
    void setName(String name);


	/** Setter method.
	 * @param password their password */
    void setPassword(String password);
}
