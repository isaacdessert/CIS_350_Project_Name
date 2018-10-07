package com.probizbuddy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/** Add a worker to the database. */
public class AddWorker {
	
	//List<String> workersArray;
	
	/** Constructor to set up a new worker. */
	AddWorker() {
		//StartProgram start = new StartProgram(window);
		//workersArray = start.getWorkers();
		//System.out.println(workersArray.get(0));
		//setWorker("Reese Pankratz", 12.00);
	}
	
	
	/** show the necessary fields to add an employee. */
	public void showForum() {
		System.out.println("Display the GUI form here");
	}
	
	
	/** add the worker to a database.
	 * @param name : name
	 * @param wage : wage
	 * @return the id assigned to the new employee */
	public String setWorker(final String name, final double wage) {
		try (FileWriter fw = new FileWriter("WorkersDB.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw)) {
				GenerateID eID = new GenerateID();
				String eid = eID.getID();
			
			    System.out.println(name + " added");
				out.println(eid + ", " + name + ", " + wage);
				return eid;

			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		return null;
	}
}
