package com.probizbuddy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.probizbuddy.AnalyzeData;
import com.probizbuddy.TimeLog;
import com.probizbuddy.Worker;

class TestAnalyzeData {
	
	public TestAnalyzeData() {
		
	}
	
	

	@Test
	void test() {
		
		try {
			
			int x = 4;
			List<TimeLog> timeLogs = new ArrayList<TimeLog>();
			AnalyzeData analyze = new AnalyzeData();

			
			
			
			
			Worker w1 = new Worker("Id","Name","Password","Wage");
			Worker w2 = new Worker("","","","");
			Worker w3 = new Worker("Id","","Password","Wage");
			Worker w4 = new Worker("Id","Name","","Wage");
			Worker w5 = new Worker("Id","Name","Password","");
			Worker w6 = new Worker("Id","NameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameName","Password","Wage");
			Worker w7 = new Worker("Id","Name","PasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPassword","Wage");
			Worker w8 = new Worker("Id","Name","Password","WageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWage");
			Worker w9 = new Worker("Id","Name","Password","Wage");
			Worker w10 = new Worker("0","1","2","3");
			Worker w11 = new Worker("Id","Name","Password","9999999999999999999999999999");
			
			
			
			
			if (analyze.getAllWorkers() != null && analyze.nameList(analyze.getAllWorkers()) != null) {
			
			}

			if (analyze.getAllWorkers() != null)
			
			if (analyze.nameList(analyze.getAllWorkers()) != null)
				
			
	
			if (analyze.getCurrentWorkers() != null)
			
			
			if (analyze.getAllTimeLogs().equals(""))
			
			if (analyze.getCertainTimeLogs(timeLogs,w1).equals(""))
			
			if (analyze.getCertainTimeLogs(timeLogs,w2).equals(""))
			
			if (analyze.getCertainTimeLogs(timeLogs,w3).equals(""))
			
			if (analyze.getCertainTimeLogs(timeLogs,w4).equals(""))
			
			if (analyze.getCertainTimeLogs(timeLogs,w5).equals(""))
			
			if (analyze.getCertainTimeLogs(timeLogs,w6).equals(""))
			
			if (analyze.getCertainTimeLogs(timeLogs,w7).equals(""))
			
			if (analyze.getCertainTimeLogs(timeLogs,w8).equals(""))
			
			if (analyze.getCertainTimeLogs(timeLogs,w9).equals(""))
			
			if (analyze.getCertainTimeLogs(timeLogs,w10).equals(""))
			
			if (analyze.getCertainTimeLogs(timeLogs,w11).equals(""))
			
			
			if (analyze.getUnpaidTimeLogs(analyze.getAllTimeLogs()).equals(""))
			
			
			if (analyze.getPaidTimeLogs(analyze.getAllTimeLogs()).equals(""))


			if (analyze.logTotalsList(analyze.getAllTimeLogs()).equals(""))
			
		
			if (analyze.sumLogTotalsList(analyze.getAllTimeLogs()).equals(""))
			
			
			
			
//			if (analyze.calculateWages(analyze.getAllTimeLogs(), w1).equals(""))
				
			if (analyze.calculateWages(analyze.getAllTimeLogs(), w2).equals(""))
				
			if (analyze.calculateWages(analyze.getAllTimeLogs(), w3).equals(""))
				
			if (analyze.calculateWages(analyze.getAllTimeLogs(), w4).equals(""))
				
			if (analyze.calculateWages(analyze.getAllTimeLogs(), w5).equals(""))
				
			if (analyze.calculateWages(analyze.getAllTimeLogs(), w6).equals(""))
				
			if (analyze.calculateWages(analyze.getAllTimeLogs(), w7).equals(""))
			
			if (analyze.calculateWages(analyze.getAllTimeLogs(), w8).equals(""))
				
			if (analyze.calculateWages(analyze.getAllTimeLogs(), w9).equals(""))
			
			if (analyze.calculateWages(analyze.getAllTimeLogs(), w10).equals(""))
				
			if (analyze.calculateWages(analyze.getAllTimeLogs(), w11).equals(""))
			
			
			if (analyze.formatTimeLog(1,60).equals(""))
			
			if (analyze.formatTimeLog(0,0).equals(""))
			
			if (analyze.formatTimeLog(999999999,999999999).equals(""))
				x = 2;
		}
		
		
		catch (Exception e) {
			
			
			Assert.fail("Exception " + e);

			
		}
		
		
		
		
//		fail("Not yet implemented");
	}

}
