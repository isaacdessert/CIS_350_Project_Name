package com.probizbuddy;

/** Defines a manager. */
public class Manager implements User {
	
	/** Every user must have these. */
	private String id, name, password;

	
	/** Constructor for Manager.
	 *  @param pID : id
	 *  @param pName : name
	 *  @param pPassword : pass */
	public Manager(final String pID, final String pName, final String pPassword) {
		setID(pID);
		setName(pName);
		setPassword(pPassword);
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

}
