package com.probizbuddy;

/** Defines a manager. */
public class Worker implements User {
	
	/** Every user must have these. */
	private String id, name, password, wage;

	
	/** Constructor for Manager.
	 *  @param pID : id
	 *  @param pName : name
	 *  @param pPassword : pass
	 *  @param pWage : wage */
	public Worker(final String pID, final String pName, final String pPassword, final String pWage) {
		setID(pID);
		setName(pName);
		setPassword(pPassword);
		setWage(pWage);
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

	
	/** Setter method.
	 * @param pID */
    public void setID(final String pID) {
        this.id = pID;
    }
    
	
	/** Setter method.
	 * @param name */
    public void setName(final String name) {
        this.name = name;
    }


	/** Setter method.
	 * @param password */
    public void setPassword(final String password) {
        this.password = password;
    }
    
    
	/** Setter method.
	 * @param wage */
	public void setWage(final String wage) {
		this.wage = wage;
	}

}
