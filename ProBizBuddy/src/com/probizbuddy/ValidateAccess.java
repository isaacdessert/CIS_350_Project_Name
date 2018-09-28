package com.probizbuddy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

/** Check to see what this device should access. */
public class ValidateAccess {
	
	JFrame window;
	
	/** Default constructor. */
	ValidateAccess() {
		if (fileExists("ManagersDB.txt")) {
			System.out.println("ManagersDB exists");
			/** if there are no managers, add one */
			if (dbResults("ManagersDB.txt") == null) {
				// need to set up an account
				System.out.println("There are no managers. Must add one.");
				AddManager add = new AddManager(window); // change to add manager
				add.showForum();
			}
			
		} else {
			// need to set up an account
			AddManager add = new AddManager(window);
			add.showForum();
		}
		
		
		if (fileExists("WorkersDB.txt")) {
			System.out.println("WorkersDB exists");
		}
		
		if (fileExists("TimeLogDB.txt")) {
			System.out.println("TimeLogDB exists");
		}
		
		if (fileExists("Settings.txt")) {
			System.out.println("Settings exists");
		}
	}
	
	/** Costructor. */
>>>>>>> 18bcc38b19bd0144f43a04df3d66eef792dff23d
	ValidateAccess(JFrame pWindow) {
		
		window = pWindow;
		
		if (fileExists("ManagersDB.txt")) {
			System.out.println("ManagersDB exists");
			/** if there are no managers, add one */
			if (dbResults("ManagersDB.txt") == null) {
				// need to set up an account
				System.out.println("There are no managers. Must add one.");
				AddManager add = new AddManager(window); // change to add manager
				add.showForum();
			}
			
		} else {
			// need to set up an account
			AddManager add = new AddManager(window);
			add.showForum();
		}
		
		
		if (fileExists("WorkersDB.txt")) {
			System.out.println("WorkersDB exists");
		}
		
		if (fileExists("TimeLogDB.txt")) {
			System.out.println("TimeLogDB exists");
		}
		
		if (fileExists("Settings.txt")) {
			System.out.println("Settings exists");
		}
		
		// if everything is in order, bring them to a login screen.
		Login login = new Login(window);
		login.showPanel();
	}

	/** Check to see of the file exists.
	 * @param fileName : the name of the file to check
	 * 	@return whether the file exists
	 *  */
	public boolean fileExists(final String fileName) {
		/** Check if the employee database exists */
		File file = new File(fileName);
		if (!file.exists()) { 
			
			// create the file because it is needed
			try {
				FileWriter workers = new FileWriter(fileName, true);
				workers.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return false;
		}
		
		return true;
	}
	
	
	/** 
	Get a list of the workers.
	@param file : file to search
	@return a List of workers
	 */
	public List<String> dbResults(final String file) {
		BufferedReader br = null;
		String line = null;
		String workersList = "";
		List<String>  workersArray;
		
		System.out.println("Reading file " + file);
		
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		try {
			if (br.readLine() == null) {
			    System.out.println(file + " is empty.");
			    return null;
			} else {
				System.out.println("Scanning...");
				while ((line = br.readLine()) != null) {
					workersList += line;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("ArrayList created!");
		
		workersArray = Arrays.asList(workersList.split("\\s*,\\s*"));
		
		return workersArray;
	}

}
