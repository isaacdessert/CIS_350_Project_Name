package com.probizbuddy;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/** Initializes the program and retrieves all necessary info. */
public class StartProgram {
	
	/** Text file databases. */
	private FileWriter workers, timelog, settings;
	
	/** Buttons shown on the screen. */
	private JButton employeeAccess, employerAccess;
	
	/** Stores unique IDs. */
	private String employeeID, employerID;
	
	/** JFrame. */
	private JFrame window;
	
	/** Constructor to set up the program.
	@param pWindow : the JFrame to add content to
	 */
	StartProgram(final JFrame pWindow) {
		
		/** Set up screen */
		window = pWindow;
		
		window.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();


		employeeAccess = new JButton("Employee Access");

		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.ipady = 20;
		c.weighty = 1.0;   //request any extra vertical space
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		
		c.anchor = GridBagConstraints.PAGE_START; 
		c.insets = new Insets(10, 0, 0, 0);  //top padding
		window.add(employeeAccess, c);
		employeeAccess.setEnabled(true);
		employeeAccess.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                employeeAccess();
            }
        });

		
		employerAccess = new JButton("Employer Access");
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.ipady = 20;
		c.weighty = 1.0;   //request any extra vertical space
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.PAGE_START; 
		c.insets = new Insets(10, 0, 0, 0);  //top padding
		window.add(employerAccess, c);
		employerAccess.setEnabled(true);
		
		
		/** Check if the employee database exists */
		File workersDB = new File("WorkersDB.txt");
		if (!workersDB.exists()) { 
			// if the employee database does not exist
			try {
				workers = new FileWriter("WorkersDB.txt", true);
				workers.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		/** Check if the time log database exists */
		File timeLogDB = new File("TimeLogDB.txt");
		if (!timeLogDB.exists()) { 
			// if the employee database does not exist
			try {
				timelog = new FileWriter("TimeLogDB.txt", true);
				timelog.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		/** Check if the settings file exists */
		File settingsFile = new File("Settings.txt");
		if (!settingsFile.exists()) { 
			// if the employee database does not exist
			try {
				settings = new FileWriter("Settings.txt", false);
				//settings.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		/** if there are no workers, add one */
		if (getWorkers() == null) {
			System.out.println("There are no employees. Adding one.");
			AddWorker add = new AddWorker();
			add.showForum();
		}
		
	}
	
	
	/** log into the employee panel. */
	public void employeeAccess() {
		employeeID = (String) JOptionPane.showInputDialog(null, 
			"Enter your 5 digit Employee ID", "Employee Access", 
			JOptionPane.QUESTION_MESSAGE);
		
		// test id against every account in the database
		File file = new File("WorkersDB.txt");

		try {
		    Scanner scanner = new Scanner(file);

		    //now read the file line by line...
		    int lineNum = 0;
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        lineNum++;
		        if (line.substring(0, 5).equals(employeeID)) { 
		            System.out.println("User found on line " + lineNum);
		            new EmployeePanel(window, line);
		        } else {
		        		System.out.println("No, starts with " + line);
		        }
		    }
		    
		    scanner.close();
		    
		} catch (FileNotFoundException e) { 
		    //handle this
		}
		
		
	}


	/** 
	Get a list of the workers.
	@return a List of workers
	 */
	public List<String> getWorkers() {
		BufferedReader br = null;
		String line = null;
		String workersList = "";
		List<String> workersArray;
		
		try {
			br = new BufferedReader(new FileReader("WorkersDB.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		try {
			if (br.readLine() == null) {
			    System.out.println("WorkerDB is empty.");
			    return null;
			} else {
				while ((line = br.readLine()) != null) {
					workersList += line;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		workersArray = new ArrayList<String>(Arrays.asList(workersList.split(", ")));
		
		return workersArray;
	}
	
	/** 
	Get all the clock in and clock out times from the file.
	@return a List times worked by employees
	 */
	public List<String> getTimeLog() {
		BufferedReader br = null;
		String line = null;
		String timesList = "";
		List<String> timesArray;
		
		try {
			br = new BufferedReader(new FileReader("TimeLogDB.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		try {
			if (br.readLine() == null) {
			    System.out.println("TimeLogDB is empty.");
			    return null;
			} else {
				while ((line = br.readLine()) != null) {
					timesList += line;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		timesArray = new ArrayList<String>(Arrays.asList(timesList.split(", ")));
		
		return timesArray;
	}
	
	
	/** 
		Get settings from Settings.txt.
		@return a List of settings
	*/
	public List<String> getSettings() {
		BufferedReader br = null;
		String line = null;
		String settingsList = "";
		List<String> settingsArray;
		
		try {
			br = new BufferedReader(new FileReader("Settings.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		try {
			if (br.readLine() == null) {
			    System.out.println("Settings is empty.");
			    return null;
			} else {
				while ((line = br.readLine()) != null) {
					settingsList += line;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		settingsArray = new ArrayList<String>(Arrays.asList(settingsList.split(", ")));
		
		return settingsArray;
	}
	
	/** check to see if they push the log in button.
	 * @param e : click event.
	 */
	public void actionPerformed(final ActionEvent e) {
		if (employeeAccess.getModel().isPressed()) {
			System.out.println("employee access");
		}
    }
	
}
