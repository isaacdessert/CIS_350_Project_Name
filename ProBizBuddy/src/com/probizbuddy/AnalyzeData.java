package com.probizbuddy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/** Class for analyzing database content. */
public class AnalyzeData {
	
	/** Get a list of all employees.
	 * @return array of worker objects
	 * @throws FileNotFoundException not found */
	public List<Worker> getAllWorkers() throws FileNotFoundException {

		ValidateAccess v = new ValidateAccess();
		
		List<Worker> workers = new ArrayList<Worker>();
		
		File workersDB = new File("WorkersDB.txt");
		final Scanner scanner = new Scanner(workersDB, "UTF-8");
		
		if (workersDB.length() > 0) {
			while (scanner.hasNextLine()) {
				final String lineFromFile = scanner.nextLine();
				List<String> user = Arrays.asList(lineFromFile.split("\\s*,\\s*"));
				List<String> thisEmployee = v.getUserData("WorkersDB.txt", user.get(0));
				workers.add(new Worker(thisEmployee.get(0), thisEmployee.get(1), thisEmployee.get(2), thisEmployee.get(3)));
			}
				
			scanner.close();
			return workers;
		}
		
		scanner.close();
		return null;
	}
	
	
	/** Turn a list of objects into a list of names.
	 *  @param workers list
	 *  @return list */
	public String nameList(final List<Worker> workers) {
		String nameList = "";
		
		for (Worker worker : workers) {
			nameList += worker.getName() + ", ";
		}
		
		return nameList;
	}
	
	
	/** Count the number of employees clocked in.
	 * @return the count
	 * @throws FileNotFoundException */
	public int countWorkers() throws FileNotFoundException {
		return getAllWorkers().size();
	}
	
	
	/** Get a list of people currently clocked in.
	 * @return array of worker objects
	 * @throws FileNotFoundException not found */
	public List<Worker> getCurrentWorkers() throws FileNotFoundException {

		ValidateAccess v = new ValidateAccess();
		
		List<Worker> employeesClockedIn = new ArrayList<Worker>();
		
		File workersDB = new File("WorkersDB.txt");
		File timeDB = new File("TimeLogDB.txt");
		final Scanner scanner = new Scanner(timeDB, "UTF-8");
		
		if (workersDB.length() > 0) {
			if (timeDB.length() > 0) {
				while (scanner.hasNextLine()) {
					final String lineFromFile = scanner.nextLine();
					List<String> user = Arrays.asList(lineFromFile.split("\\s*,\\s*"));
				
					// only show all unpaid, completed time logs
					if (user.get(3).equals("null")) {
						List<String> thisEmployee = v.getUserData("WorkersDB.txt", user.get(0));
						employeesClockedIn.add(new Worker(thisEmployee.get(0), thisEmployee.get(1), thisEmployee.get(2), thisEmployee.get(3)));
				    }
				}
				
				scanner.close();
				return employeesClockedIn;
			}
		
		}
		
		scanner.close();
		return null;
	}
	
	/** Count the number of employees clocked in.
	 * @return the count
	 * @throws FileNotFoundException */
	public int countCurrentWorkers() throws FileNotFoundException {
		return getCurrentWorkers().size();
	}
}
