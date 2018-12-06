package com.probizbuddy;



import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestAddWorker 
{
	AddWorker test;
	
	
	public TestAddWorker() {
	}
	
	
	@Test
	public void test() {
		test = new AddWorker();
		
		try {
			test.setWorker("Name","Password","Wage");
			test.setWorker("","","");
			test.setWorker("","Password","Wage");
			test.setWorker("Name","","Wage");
			test.setWorker("Name","Password","");
			test.setWorker("NameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameNameName","Password","Wage");
			test.setWorker("Name","PasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPasswordPassword","Wage");
			test.setWorker("Name","Password","WageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWageWage");
			test.setWorker("Name","Password","Wage");
			test.setWorker("1","2","3");
			test.setWorker("Name","Password","9999999999999999999999999999");

			
		}
		catch (Exception e) {
			
			
			Assert.fail("Exception " + e);

			
		}
//		fail("Not yet implemented");
	}

}
