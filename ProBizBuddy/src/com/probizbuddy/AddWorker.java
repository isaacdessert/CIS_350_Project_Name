package com.probizbuddy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFrame;

public class AddWorker {
	
	//List<String> workersArray;
	
	AddWorker() {
		//StartProgram start = new StartProgram(window);
		//workersArray = start.getWorkers();
		//System.out.println(workersArray.get(0));
		setWorker("Reese Pankratz", 12.00);
	}
	
	public void showForum() {
		System.out.println("Display the GUI form here");
	}
	
	public String setWorker(final String name, final double wage) {
		try (FileWriter fw = new FileWriter("WorkersDB.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
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
