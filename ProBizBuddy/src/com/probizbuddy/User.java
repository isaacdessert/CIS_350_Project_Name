package com.probizbuddy;

/** Defines a user. */
public class User {
	
	/** Every user must have these. */
	private String id, name, password, wage;
	
	/** Constructor for Manager.
	 *  @param pId : id
	 *  @param pName : name
	 *  @param pPassword : pass */
	public User(final String pId, final String pName, final String pPassword) {
		this.id = pId;
		this.name = pName;
		this.password = pPassword;
	}
	
	/** Constructor for Employee.
	 *  @param pId : id
	 *  @param pName : name
	 *  @param pPassword : pass
	 *  @param pWage : wage */
	public User(final String pId, final String pName, final String pPassword, final String pWage) {
		this.id = pId;
		this.name = pName;
		this.password = pPassword;
		this.wage = pWage;
	}
	
	/** Getter method.
	 * @return user id */
	public String getID() {
		return id;
	}
	
	/** Getter method.
	 * @return user name */
	public String getName() {
		return name;
	}
	
	/** Getter method.
	 * @return user password */
	public String getPassword() {
		return password;
	}
	
	/** Getter method.
	 * @return employee wage */
	public String getWage() {
		return wage;
	}
	
	
}
