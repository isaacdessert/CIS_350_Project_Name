package com.probizbuddy;

/** Class for Time Logs from the Database. */
public class TimeLog {
	/** Every user must have these. */
	private String id, startDate, startTime, endTime, totalTime;
	private boolean paid;

	
	/** Constructor for TimeLog.
	 *  @param pID : id
	 *  @param startDate : start date
	 *  @param startTime : start time
	 *  @param endTime : end time
	 *  @param totalTime : total time
	 *  @param paid : paid */
	public TimeLog(final String pID, final String startDate, final String startTime, final String endTime, final String totalTime, final boolean paid) {
		setID(pID);
		setStartDate(startDate);
		setStartTime(startTime);
		setEndTime(endTime);
		setTotalTime(totalTime);
		setPaid(paid);
	}

	
	/** Getter method.
	 * @return user id */
	public String getID() {
		return id;
	}
	
	
	/** Getter method.
	 * @return start date */
	public String getStartDate() {
		return startDate;
	}
	
	
	/** Getter method.
	 * @return start time */
	public String getStartTime() {
		return startTime;
	}
	
	
	/** Getter method.
	 * @return end time */
	public String getEndTime() {
		return endTime;
	}
	
	
	/** Getter method.
	 * @return start time */
	public String getTotalTime() {
		return totalTime;
	}
	
	
	/** Getter method.
	 * @return start time */
	public boolean getPaid() {
		return paid;
	}

	
	/** Setter method.
	 * @param pID */
    public void setID(final String pID) {
        this.id = pID;
    }
    
	
	/** Setter method.
	 * @param sD start date */
    public void setStartDate(final String sD) {
        this.startDate = sD;
    }

	/** Setter method.
	 * @param sT start time */
    public void setStartTime(final String sT) {
        this.startTime = sT;
    }

	/** Setter method.
	 * @param eT end time */
    public void setEndTime(final String eT) {
        this.endTime = eT;
    }
    
    
    /** Setter method.
	 * @param tT total time */
    public void setTotalTime(final String tT) {
        this.totalTime = tT;
    }
    
    
    /** Setter method.
	 * @param p paid */
    public void setPaid(final boolean p) {
        this.paid = p;
    }
}
