package com.probizbuddy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class TestTimeLog {
	
	public TestTimeLog() {
		
		//everything is a String except paid
		TimeLog t = new TimeLog(  "pID",   "startDate",   "startTime",   "endTime",   "totalTime",   false /*paid*/)
		
	}

	@Test
	void test() {
		
		try {
			
			t.getID();
				
			t.getStartDate();
				
			t.getStartTime();

			t.getEndTime();

			t.getTotalTime();

			t.getPaid();


			

			t.setID("pID");
			t.setID("");
			t.setID("pID");
			
			
			

			t.setStartDate("StartDate");
			t.setStartDate("");
			t.setStartDate("reallyloooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong");
			t.setStartDate("10");
			t.setStartDate("9999999999999999999999999999999999");
			
			

			t.setStartTime("StartTime");
			t.setStartTime("");
			t.setStartTime("reallyloooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong");
			t.setStartTime("10");
			t.setStartTime("99999999999999999999999999999999999");
			


			t.setEndTime("EndTime");
			t.setEndTime("");
			t.setEndTime("reallyloooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong");
			t.setEndTime("10");
			t.setEndTime("999999999999999999999999999999999999");
			

		    
			t.setTotalTime("TotalTime");
			t.setTotalTime("");
			t.setTotalTime("reallyloooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong");
			t.setTotalTime("10");
			t.setTotalTime("99999999999999999999999999999999999");
			
			

			t.setPaid(false);
			
			t.setPaid(true);
		        
			
			
		}
		
		
catch (Exception e) {
			
			
			Assert.fail("Exception " + e);

			
		}
		
		
		
		
//		fail("Not yet implemented");
	}

}