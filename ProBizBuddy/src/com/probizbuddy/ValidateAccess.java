package com.probizbuddy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

/** Check to see what this device should access. */
public class ValidateAccess {
	
	/** window. */
	private JFrame window;
	
	/** only allows login of all files are in order. */
	private boolean loginReady = false;
	
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
	
	/** Constructor.
	 * @param pWindow : the window */
	ValidateAccess(final JFrame pWindow) {
		
		window = pWindow;
		
		if (fileExists("ManagersDB.txt")) {
			System.out.println("ManagersDB exists");
			/** if there are no managers, add one */
			if (dbResults("ManagersDB.txt") == null) {
				// need to set up an account
				System.out.println("There are no managers. Must add one.");
				AddManager add = new AddManager(window); // change to add manager
				add.showForum();
				
				loginReady = false;
			} else {
			
				loginReady = true;
			}
			
		} else {
			// need to set up an account
			AddManager add = new AddManager(window);
			add.showForum();
			loginReady = false;
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
		
		System.out.println("Login ready: " + loginReady);
		
		if (loginReady) {
			// if everything is in order, bring them to a login screen.
			Login login = new Login(window);
			login.showPanel();
		}
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
		//String line = null;
		String workersList = "";
		List<String>  workersArray;
		
		System.out.println("Reading file " + file);
		
		try {
			br = new BufferedReader(new FileReader(file));

			if (br.readLine() == null) {
			    System.out.println(file + " is empty.");
			    br.close();
			    return null;
			} else {
				workersList = new String(Files.readAllBytes(Paths.get(file)), StandardCharsets.UTF_8);
				br.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("ArrayList created!");
		System.out.println("List: " + workersList);
		 
		
		workersArray = Arrays.asList(workersList.split("\\s*,\\s*"));
		
		return workersArray;
	}
	
	
	/** Get the line from the database. 
	 * @param file : file
	 * @param name : employee name
	 * @return the list of this users data
	 * @throws FileNotFoundException */
	public  List<String> getUserData(final String file, final String name) throws FileNotFoundException {
		File doc = new File(file);
		final Scanner scanner = new Scanner(doc, "UTF-8");

		while (scanner.hasNextLine()) {
			final String lineFromFile = scanner.nextLine();
			List<String> user = Arrays.asList(lineFromFile.split("\\s*,\\s*"));
			// id, name, password
			if (user.get(1).equals(name)) { 
				scanner.close();
				return user;
			}
			
			// id based search
			if (user.get(0).equals(name)) { 
				scanner.close();
				return user;
			}
		}
		scanner.close();
		
		System.out.println("No user matched this Name: " + name);
		return null;
	}
	
	
	/** Creates a manager object.
	 *  @param name : user's name
	 *  @throws FileNotFoundException 
	 *  @return manager object */
	public Manager createManager(final String name) throws FileNotFoundException {
		if (getUserData("ManagersDB.txt", name) != null) {
			List<String> userData = getUserData("ManagersDB.txt", name);
			
			Manager manager = new Manager(userData.get(0), userData.get(1), userData.get(2));
			return manager;
		}
		
		return null;
	}
	
	
	/** Creates a worker object.
	 *  @param name : user's name
	 *  @throws FileNotFoundException 
	 *  @return worker object */
	public Worker createWorker(final String name) throws FileNotFoundException {
		if (getUserData("WorkersDB.txt", name) != null) {
			List<String> userData = getUserData("WorkersDB.txt", name);
			
			Worker worker = new Worker(userData.get(0), userData.get(1), userData.get(2), userData.get(3));
			return worker;
		}
		
		return null;
	}
}
