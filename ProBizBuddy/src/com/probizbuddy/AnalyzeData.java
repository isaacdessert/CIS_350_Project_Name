package com.probizbuddy;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
		if (getAllWorkers() != null) {
			return getAllWorkers().size();
		}
		
		return 0;
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
		if (getCurrentWorkers() != null) {
			return getCurrentWorkers().size();
		}
		
		return 0;
	}
	
	
	/** Get time logs as objects.
	 *  @return all time log objects
	 *  @throws FileNotFoundException */
	public List<TimeLog> getAllTimeLogs() throws FileNotFoundException {
		
		List<TimeLog> timeLogs = new ArrayList<TimeLog>();
		
		File doc = new File("TimeLogDB.txt");
		final Scanner scanner = new Scanner(doc, "UTF-8");
		
		while (scanner.hasNextLine()) {
			final String lineFromFile = scanner.nextLine();
			List<String> user = Arrays.asList(lineFromFile.split("\\s*,\\s*"));

			// add to an arraylist of their hours
			String data0 = user.get(0);
			String data1 = user.get(1);
		    String data2 = user.get(2);
		    String data3 = user.get(3); // possible null
		    if (data3.equals("null")) {
		    	data3 = null;
		    }
		    
		    String data4 = user.get(4); // possible null
		    if (data4.equals("null")) {
		    	data4 = null;
		    }
		    
		    boolean data5;
		    if (user.get(5).equals("false")) {
		    	data5 = false;
		    } else {
		    	data5 = true;
		    }
		    
		    timeLogs.add(new TimeLog(data0, data1, data2, data3, data4, data5));
		}

		scanner.close();
		return timeLogs;
	}
	
	
	/** Get time logs as objects.
	 *  @param logList 
	 *  @param worker 
	 *  @return all time log objects
	 *  @throws FileNotFoundException */
	public List<TimeLog> getCertainTimeLogs(final List<TimeLog> logList, final Worker worker) throws FileNotFoundException {
		
		List<TimeLog> timeLogs = new ArrayList<TimeLog>();
		
		for (TimeLog log : logList) {
			 // get the results for this person
		    if (log.getID().equals(worker.getID())) {
		    	timeLogs.add(log);
		    }
		}
		    
		return timeLogs;
	}
	
	
	/** Get a list of unpaid time logs.
	 *  @param logList list of time logs
	 *  @return list of unpaid logs */
	public List<TimeLog> getUnpaidTimeLogs(final List<TimeLog> logList) {
		List<TimeLog> unpaidLogs = new ArrayList<TimeLog>();
		
		for (TimeLog log : logList) {
			if (!log.getPaid() && log.getEndTime() != null) {
				unpaidLogs.add(log);
			}
		}
		
		return unpaidLogs;
	}
	
	
	/** Get a list of paid time logs.
	 *  @param logList list of time logs
	 *  @return list of unpaid logs */
	public List<TimeLog> getPaidTimeLogs(final List<TimeLog> logList) {
		List<TimeLog> paidLogs = new ArrayList<TimeLog>();
		
		for (TimeLog log : logList) {
			if (log.getPaid()) {
				paidLogs.add(log);
			}
		}
		
		return paidLogs;
	}
	
	
	/** Turn a list of objects into a list of names.
	 *  @param logs list
	 *  @return list */
	public String logTotalsList(final List<TimeLog> logs) {
		String logList = "";
		
		for (TimeLog timeLog : logs) {
			logList += timeLog.getTotalTime() + ", ";
		}
		
		return logList;
	}
	
	
	/** Converts time logs into a sum.
	 *  @param logs 
	 *  @return total hours they worked */
	public String sumLogTotalsList(final List<TimeLog> logs) {
		
		int totalHrs = 0;
		int totalMins = 0;
	
		for (TimeLog timeLog : logs) {
			
		    int hours = 0;
		    int minutes = 0;
			String[] log = timeLog.getTotalTime().split(" ");
		    
		    if (log.length == 4) {
		    	hours = Integer.parseInt(log[0]);
		    	minutes = Integer.parseInt(log[2]);
		    	
		    	totalHrs += hours;
		    	totalMins += minutes;
		    } else {
		    	minutes = Integer.parseInt(log[0]);
		    	totalMins += minutes;
		    }
		    
		    
		} 
		
		return formatTimeLog(totalHrs, totalMins);
	}
	
	/** Calculate wages from a list of time logs.
	 *  @param logList 
	 *  @param worker 
	 *  @return wages 
	 *  @throws FileNotFoundException */
	public String calculateWages(final List<TimeLog> logList, final Worker worker) throws FileNotFoundException {
		
		int hours = 0, minutes = 0;
		String formattedTotal = sumLogTotalsList(logList);
		
		if (formattedTotal.length() == 4) {
	    	hours = Integer.parseInt(formattedTotal.substring(0, 1));
	    	minutes = Integer.parseInt(formattedTotal.substring(2, 3));
	    } else {
	    	minutes = Integer.parseInt(formattedTotal.substring(0, 1));
	    }
		
		
		double timeWorked = (double) hours + (minutes / 60.0);
        Double totalPayout = (timeWorked * Double.parseDouble(worker.getWage()));
        
        Currency usd = Currency.getInstance("USD");
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        format.setCurrency(usd);
        
        return format.format(totalPayout);
	}
	
	
	/** Format a time log as xH yM  where x is hours and y is minutes.
	 *  @param totalHrs 
	 *  @param totalMins 
	 *  @return formatted time */
	public String formatTimeLog(final int totalHrs, final int totalMins) {
		
		String totalHours = "";
		if (totalHrs == 1) {
			totalHours += "1 H ";
		} else {
			totalHours += "" + totalHrs + " Hs ";
		}
		
		if (totalMins == 1) {
			totalHours += "1 M";
		} else {
			totalHours += "" + totalMins + " Ms";
		}
		
		return totalHours;
	}

}
