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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
    
    /** Employee ID they logged in with. */
    private String eID, eName;
    
    /** Creates an expandable data table. */
    private DefaultTableModel tableModel;
    
    /** Manager list from db. */
	private List<String> employee;
    

	/** 
	 * constructor to set up the window.
	 * @param employeeName : the employee that is logged in
	 * @param pWindow : the gui
	 * @throws FileNotFoundException 
	 * */
	public EmployeePanel(final JFrame pWindow, final String employeeName) throws FileNotFoundException {
		eName = employeeName;
		System.out.println("Logged in as " + employeeName);
		
		window = pWindow;
		
		ValidateAccess v = new ValidateAccess(window);
		employee = v.getUserData("WorkersDB.txt", eName);
		eID = employee.get(0);
		System.out.println("eID = " + eID);
		
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

		clockIn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            		try {
						clockIn();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
            }
        });

		// clock out button
		clockOut = new JButton("Clock Out");
		clockOut.setFont(new Font("Arial Black", Font.BOLD, 20));
		
		clock.add(clockOut, c);
		clockOut.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
        		try {
					clockOut();
				} catch (ParseException | IOException e1) {
					e1.printStackTrace();
				}
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
		
		// determine which button to show the user
		isClockedIn();
	}
	
	
	/** Show the Clock Out button on the GUI. */
	private void showClockInButton() {
		clockOut.setEnabled(false);
		clockOut.setVisible(false);
		
		clockIn.setEnabled(true);
		clockIn.setVisible(true);
	}
	
	
	/** Show the Clock Out button on the GUI. */
	private void showClockOutButton() {
		clockOut.setEnabled(true);
		clockOut.setVisible(true);
		
		clockIn.setEnabled(false);
		clockIn.setVisible(false);
	}
	
	
	/** Check whether they are clocked in.
	 *  @return true if they have previously clocked in without clocking out */
	public boolean isClockedIn() {
		// check the database
		if (tableModel != null && tableModel.getRowCount() > 0) {
			if (tableModel.getValueAt(tableModel.getRowCount() - 1, 2).toString().equals("null")) {
				// show clock out button
				showClockOutButton();
			} else {
				// show clock in button
				showClockInButton();
			}
		} else {
			showClockInButton();
		}
		
		
		return false;
	}
	
	
	/** User still clocks in.
	 *  Makes sure the user has previously clocked out. 
	 * @throws FileNotFoundException */
	private void clockIn() throws FileNotFoundException {
		
		// add the time to the database
	    SimpleDateFormat formattedDate = new SimpleDateFormat("MM-dd-yyyy");
	    String simpleDate = formattedDate.format(new Date());
	    
	    SimpleDateFormat formattedTime = new SimpleDateFormat("hh:mm a"); // ad :ss for seconds too
	    String simpleTime = formattedTime.format(new Date());
	    
	    try (FileWriter fw = new FileWriter("TimeLogDB.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
				
		    PrintWriter out = new PrintWriter(bw)) {
				
				// 00000, 10-4-18, 11:35, 19:23, 8 hours 12 minutes, false
				// id, date, in, null, null, false
				out.println(eID + ", " + simpleDate + ", " + simpleTime + ", null, null, false");

			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
	    
	    // refresh after the entry is added
		fillTable();
		isClockedIn();
	}
	
	
	/** User clocks out. 
	 *  Makes sure the user has previous clocked in.
	 * @throws ParseException 
	 * @throws IOException */
	private void clockOut() throws ParseException, IOException {
		
		
			// replace that value in the text file
			System.out.println("Replacing null with current time.");
			
			String time1 = tableModel.getValueAt(tableModel.getRowCount() - 1, 1).toString();
			
		    SimpleDateFormat formattedTime = new SimpleDateFormat("hh:mm a"); // ad :ss for seconds too
		    String time2 = formattedTime.format(new Date());
			
			Date date1 = formattedTime.parse(time1);
			Date date2 = formattedTime.parse(time2);
			
			long totalSecs = date2.getTime() - date1.getTime();
			
			totalSecs /= 1000;
			
			long hours = (totalSecs / 3600);
	        long mins = (totalSecs / 60) % 60;

			System.out.println("Hours: " + hours);
			System.out.println("Minutes: " + mins);
			
			String timeDifference = "";
			
			if (hours > 0) {
				if (hours == 1) {
					timeDifference += "1 Hour ";
				} else if (hours > 1) {
					timeDifference += "" + hours + " Hours ";
				}
			}
			
			if (mins > 0) {
				if (mins == 1) {
					timeDifference += "1 Minute";
				} else if (mins > 1) {
					timeDifference += "" + mins + " Minutes";
				}
			}
			

			// update the database
			File doc = new File("TimeLogDB.txt");
			final Scanner scanner = new Scanner(doc);
			
			String tempData = "";

			while (scanner.hasNextLine()) {
				final String lineFromFile = scanner.nextLine();
				List<String> user = Arrays.asList(lineFromFile.split("\\s*,\\s*"));
				// id, name, password
				if (user.get(0).equals(eID) && user.get(3).equals("null")) { 
				    
				    String updatedLine = 
				    		user.get(0) + ", " + user.get(1) + ", " + user.get(2) + ", " + time2 + ", " + timeDifference + ", false";
				    
				    
				    if (!timeDifference.equals("")) {
				    	tempData += updatedLine;
				    	tempData += "\n";
				    }

				} else {
					tempData += lineFromFile;
					tempData += "\n";
				}
			}
			
			// replace the file data with the value of tempData
			System.out.println(tempData);
			
			FileWriter workers = new FileWriter("TimeLogDB.txt", false);
			workers.write(tempData);
			workers.close();
			
			scanner.close();
		
		
		
		// refresh after the entry is updated
		fillTable();
		isClockedIn();
	}
	
	
	/** Return a vector containing available table data.
	 * @throws FileNotFoundException if time log db does not exist
	 */
	public void fillTable() throws FileNotFoundException {
		// only add data from this user
		File doc = new File("TimeLogDB.txt");
		final Scanner scanner = new Scanner(doc);
		
		//wipe the current table
		int rowCount = tableModel.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		
		// 00000, 10-4-18, 11:35, 19:23, 8 hours 12 minutes
		// id, date, in, null, null

		while (scanner.hasNextLine()) {
			final String lineFromFile = scanner.nextLine();
			List<String> user = Arrays.asList(lineFromFile.split("\\s*,\\s*"));
			// id, name, password
			if (user.get(0).equals(eID)) { 
				// add to an arraylist of their hours
				String data1 = user.get(1);
			    String data2 = user.get(2);
			    String data3 = user.get(3);
			    String data4 = user.get(4);

			    Object[] rowData = new Object[] {data1, data2, data3, data4};
			    
				tableModel.addRow(rowData);
			}
		}

		scanner.close();
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

		tableModel = new DefaultTableModel(columnNames, 0);
		
		table = new JTable(tableModel);
		table.setAutoscrolls(true);

	}
	

}
