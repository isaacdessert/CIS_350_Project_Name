package com.probizbuddy;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

/** Work space for employees. */
public class EmployeePanel {
	
	/** window. */
	private JFrame window;
	
	/** clock in and clock out buttons. */
	private JButton clockIn, clockOut;
	
	/** panel for clocking in and out. */
	private JPanel clock = new JPanel();
    
	/** panel for displaying hours. */
	private JPanel hours = new JPanel();
	
	/** panel for displaying hours. */
	private JPanel organizedPanel = new JPanel();
	
	/** Table that displays the user's hours. */
	private JTable table;
	
	/** Data to be written into the table. */
    private Object[][] tableData;
    
    /** Employee ID they logged in with. */
    private String eID;

	/** 
	 * constructor to set up the window.
	 * @param employeeID : the employee that is logged in
	 * @param pWindow : the gui
	 * */
	public EmployeePanel(final JFrame pWindow, final String employeeID) {
		System.out.println("Logged in as " + employeeID);
		eID = employeeID;
		
		tableData = new Object[15][15]; // default amount to grab
		window = pWindow;
		
		// show their current hours if any exist
	    try {
			createHoursTable();
		    fillTable();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
	    
	}
	
	
	/** Display the panel to the GUI. */
	public void showPanel() {
		System.out.println("Display employee panel");
		
		/** set layout and bg */
		clock.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		
		Color blue = new Color(66, 153, 229);
		clock.setBackground(blue);

		// clock in button
		clockIn = new JButton("Clock In");
		clockIn.setFont(new Font("Arial Black", Font.BOLD, 20));
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 50;
		c.ipady = 25;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.PAGE_START; 
		
		clock.add(clockIn, c);
		clockIn.setEnabled(true);
		clockIn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            		clockIn();
            }
        });

		// clock out button
		clockOut = new JButton("Clock Out");
		clockOut.setFont(new Font("Arial Black", Font.BOLD, 20));
		
		clockOut.setEnabled(false);
		clockOut.setVisible(false);
		clock.add(clockOut, c);
		clockOut.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            		clockOut();
            }
        });
		
		// reset
		c.fill = GridBagConstraints.NONE;
		c.ipady = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 0;
		c.anchor = GridBagConstraints.NONE; 
		c.insets = new Insets(0, 0, 0, 0);
		
		
		// hours table header
	    hours.setLayout(new GridBagLayout());
	    
	    c.fill = GridBagConstraints.NONE;
	    c.anchor = GridBagConstraints.CENTER; 
		c.insets = new Insets(0, 0, 0, 0);
		//c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridx = 2;
		c.gridy = 1;
		hours.add(table.getTableHeader(), c);
		
		// hours table

		c.gridx = 2;
		c.gridy = 2;

		hours.add(table, c);
		
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
		organizedPanel.add(clock);
		organizedPanel.add(hours);
		
		organizedPanel.setBackground(blue);
		
		window.getContentPane().add(organizedPanel);
	}
	
	
	/** Check whether they are clocked in.
	 *  @return true if they have previously clocked in without clocking out */
	public boolean isClockedIn() {
		// check the database
		Object[][] tempData = null;
		
		try {
			tempData = fillTable();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < tempData.length; i++) {
	        for (int j = 0; j < tempData[i].length; j++) {
	        	System.out.println(tempData[i][j]);
	        		if (tempData[0][j] != null) {
		            if (tempData[0][j].equals(eID)) {
		            		if (tempData[3][j].equals("null")) {
		            			// show clock out button
		            			clockOut.setEnabled(true);
		            			clockOut.setVisible(true);
		            			
		            			clockIn.setEnabled(false);
		            			clockIn.setVisible(false);
		            			return true;
		            		}
		           }
	        		}
	        }
	    }
		
		// show clock in button
		clockOut.setEnabled(false);
		clockOut.setVisible(false);
		
		clockIn.setEnabled(true);
		clockIn.setVisible(true);
		
		return false;
	}
	
	
	/** User still clocks in.
	 *  Makes sure the user has previously clocked out. */
	public void clockIn() {
		clockOut.setEnabled(true);
		clockOut.setVisible(true);
		
		clockIn.setEnabled(false);
		clockIn.setVisible(false);
		
		// add the time to the database
	    SimpleDateFormat formattedDate = new SimpleDateFormat("MM-dd-yyyy");
	    String simpleDate = formattedDate.format(new Date());
	    
	    SimpleDateFormat formattedTime = new SimpleDateFormat("HH:mm:ss a");
	    String simpleTime = formattedTime.format(new Date());
	    
	    try (FileWriter fw = new FileWriter("TimeLogDB.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
				
		    PrintWriter out = new PrintWriter(bw)) {
				
				// 00000, 10-4-18, 11:35, 19:23, 8 hours 12 minutes
				// id, date, in, null, null
				out.println(eID + ", " + simpleDate + ", " + simpleTime + ", null, null");

			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		
	}
	
	
	/** User clocks out. 
	 *  Makes sure the user has previous clocked in.*/
	public void clockOut() {
		isClockedIn();
		
		SimpleDateFormat formattedTime = new SimpleDateFormat("HH:mm:ss a");
	    String simpleTime2 = formattedTime.format(new Date());
		
		// fill in the current time in the null clock out position
		
		
	    // get time difference in seconds based on first time in db
		 
	}
	
	/** Fill the table with data from the time log database.
	 * @return a mutlidimensional array of table data
	 * @throws FileNotFoundException if there is no db */
	public Object[][] fillTable() throws FileNotFoundException {
		// only add data from this user
		File doc = new File("TimeLogDB.txt");
		final Scanner scanner = new Scanner(doc);
		
		int i = 0;

		while (scanner.hasNextLine()) {
			final String lineFromFile = scanner.nextLine();
			List<String> user = Arrays.asList(lineFromFile.split("\\s*,\\s*"));
			// id, name, password
			if (user.get(0).equals(eID)) { 
				// add to the object array
				tableData[i][0] = user.get(1);
				tableData[i][1] = user.get(2);
				tableData[i][2] = user.get(3);
				tableData[i][3] = user.get(4);  
				i++;
			}
		}

		scanner.close();
	
		return tableData;
	}
	
	
	/** Create the hours table.
	 * @throws FileNotFoundException if the DB doesn't exist */
	public void createHoursTable() throws FileNotFoundException {
		
		String[] columnNames = {
				"Date",
                "Clocked In",
                "Clocked Out",
                "Total Hours",
                };
		
		// fill a multidimensional array with a loop from the database
		
		
		table = new JTable(fillTable(), columnNames);
		//table.setFillsViewportHeight(true);
		//hours.setLayout(new BorderLayout());
		//hours.add(table.getTableHeader(), BorderLayout.PAGE_START);
		//hours.add(table, BorderLayout.CENTER);

	}
	

}
