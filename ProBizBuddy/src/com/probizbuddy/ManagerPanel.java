package com.probizbuddy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/** Main area for managers to control Pro Biz Buddy. */
public class ManagerPanel {
	
	/** Window. */
	private JFrame window;
	
	/** Panel holding manager tools. */
	private JPanel layout, nav, dash;
	
	/** Optional panels to view. */
	private JPanel overviewPanel, payrollPanel, addEmployeePanel, currentWorkersPanel, editWorkersPanel;
	
	/** Navigation buttons. */
	private JButton overview, payroll, currentWorkers, addEmployee, editEmployees, logout;
	
	/** Format of currency. */
	private NumberFormat currency;
	
	/** Totals the payroll data. */
	private Object[] totalRowData;
	
	/** Writes files. */
	private FileWriter workers;
	
	/** Style of all buttons. */
	private Font buttonStyle = new Font("Arial", Font.PLAIN, 16);
	
	/** Data table. */
	private DefaultTableModel tableModel;
	
	/** Table column names. */
	private String[] columnNames = {
			"Name",
			"Date",
            "Clocked In",
            "Clocked Out",
            "Total Hours",
            "Hourly Rate",
            "Wages Earned"
            };
	
	/** Manager object. */
	private Manager user;
	
	/** logged in as manager. 
	 * @throws FileNotFoundException 
	 * @param pWindow : window
	 * @param manager : manager object */
	ManagerPanel(final JFrame pWindow, final Manager manager) throws FileNotFoundException {
		window = pWindow;
		layout = new JPanel();
		nav = new JPanel();
		dash = new JPanel();
		
		user = manager;
		
		System.out.println("Logged in as " + user.getName());
		
		// set up all components
		addEmployee();
		payroll();
		currentWorkers();
		editWorkers();
		
		setDashComponent(overview());
	}
	
	
	/** Show the panel on the GUI. */
	public void showPanel() {
		System.out.println("Logged in, show the manager's panel.");
		
		layout.setLayout(new GridBagLayout());
		
		 displayNav();
		 displayDashboard();

		window.getContentPane().add(layout);
	}
	
	/**  Sets the component to show in the dash.
	 * @param newPanel : panel to replace in */
	public void setDashComponent(final JPanel newPanel) {
		newPanel.setVisible(true);
		newPanel.setEnabled(true);
		newPanel.setBackground(new Color(66, 153, 229));
		dash.add(newPanel);
	}
	
	/** Shows dashboard components on the GUI. 
	 * The content inside here will be replaced with 
	 * whatever is navigated to. */
	public void displayDashboard() {
		dash.setBackground(new Color(66, 153, 229)); // blue
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = .7;
		constraints.weighty = 1.0;
		dash.setPreferredSize(new Dimension(window.getWidth() - 325, window.getHeight()));
		layout.add(dash, constraints);
	}
	
	
	/** Shows navigation components on the GUI. */
	public void displayNav() {
		nav.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weighty = 1;
		
		JLabel navigationLabel = new JLabel();
		navigationLabel.setText("Navigation");
		navigationLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		
		c.gridx = 0;
		c.gridy = 0;
		nav.add(navigationLabel, c);
		
		// default view for the dashboard
		overview = new JButton("Overview");
		overview.setFont(buttonStyle);
		overview.setEnabled(false);
		
		c.gridx = 0;
		c.gridy++;
		c.ipadx = 10;
		c.ipady = 5;
		nav.add(overview, c);

		payroll = new JButton("Pay Wages");
		payroll.setFont(buttonStyle);
		c.gridy++;
		nav.add(payroll, c);
		
		currentWorkers = new JButton("Currently Working");
		currentWorkers.setFont(buttonStyle);
		c.gridy++;
		nav.add(currentWorkers, c);
		
		addEmployee = new JButton("Add Employee");
		addEmployee.setFont(buttonStyle);
		c.gridy++;
		nav.add(addEmployee, c);
		
		editEmployees = new JButton("Edit Employees");
		editEmployees.setFont(buttonStyle);
		c.insets = new Insets(0, 0, 125, 0);
		c.gridy++;
		nav.add(editEmployees, c);
		
		overview.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
        		setDashComponent(overview());
            	
        		overview.setEnabled(false); overviewPanel.setVisible(true);
            	payroll.setEnabled(true); payrollPanel.setVisible(false); payrollPanel.removeAll();
        		currentWorkers.setEnabled(true); currentWorkersPanel.setVisible(false); currentWorkersPanel.removeAll();
        		addEmployee.setEnabled(true); addEmployeePanel.setVisible(false); addEmployeePanel.removeAll();
        		editEmployees.setEnabled(true); editWorkersPanel.setVisible(false); editWorkersPanel.removeAll();
            }
        });
		
		payroll.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
        		try {
					setDashComponent(payroll());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
            	
       			overview.setEnabled(true); overviewPanel.setVisible(false); overviewPanel.removeAll();
        		payroll.setEnabled(false); payrollPanel.setVisible(true);
        		currentWorkers.setEnabled(true); currentWorkersPanel.setVisible(false); currentWorkersPanel.removeAll();
        		addEmployee.setEnabled(true); addEmployeePanel.setVisible(false); addEmployeePanel.removeAll();
        		editEmployees.setEnabled(true); editWorkersPanel.setVisible(false); editWorkersPanel.removeAll();
            }
        });
		
		currentWorkers.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            	try {
					setDashComponent(currentWorkers());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
            	
            	overview.setEnabled(true); overviewPanel.setVisible(false); overviewPanel.removeAll();
            	payroll.setEnabled(true); payrollPanel.setVisible(false); payrollPanel.removeAll();
        		currentWorkers.setEnabled(false); currentWorkersPanel.setVisible(true);
        		addEmployee.setEnabled(true); addEmployeePanel.setVisible(false); addEmployeePanel.removeAll();
        		editEmployees.setEnabled(true); editWorkersPanel.setVisible(false); editWorkersPanel.removeAll();
            }
        });
		
		addEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            	setDashComponent(addEmployee());
            	
            	overview.setEnabled(true); overviewPanel.setVisible(false); overviewPanel.removeAll();
            	payroll.setEnabled(true); payrollPanel.setVisible(false); payrollPanel.removeAll();
        		currentWorkers.setEnabled(true); currentWorkersPanel.setVisible(false); currentWorkersPanel.removeAll();
        		addEmployee.setEnabled(false); addEmployeePanel.setVisible(true);
        		editEmployees.setEnabled(true); editWorkersPanel.setVisible(false); editWorkersPanel.removeAll();
            }
        });
		
		editEmployees.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            	try {
					setDashComponent(editWorkers());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
            	
            	overview.setEnabled(true); overviewPanel.setVisible(false); overviewPanel.removeAll();
            	payroll.setEnabled(true); payrollPanel.setVisible(false); payrollPanel.removeAll();
        		currentWorkers.setEnabled(true); currentWorkersPanel.setVisible(false); currentWorkersPanel.removeAll();
        		addEmployee.setEnabled(true); addEmployeePanel.setVisible(false); addEmployeePanel.removeAll();
        		editEmployees.setEnabled(false); editWorkersPanel.setVisible(true); 
            }
        });
		
		
		logout = new JButton("Log Out");
		logout.setFont(buttonStyle);
		
		c.insets = new Insets(70, 0, 25, 0);
		c.gridy++;
		nav.add(logout, c);
		
		logout.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
        		System.out.println("Logging out.");
        		Login l = new Login(window);
        		layout.setVisible(false);
        		layout.setEnabled(false);
        		l.showPanel();
            }
        });
		

		nav.setPreferredSize(new Dimension(325, window.getHeight()));
		nav.setBackground(new Color(49, 128, 159));
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.VERTICAL;

		layout.add(nav, constraints);
	}
	
	/** Overview (default) panel.
	 * @return the JPanel to display. */
	private JPanel overview() {
		overviewPanel = new JPanel();
		overviewPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel userLabel = new JLabel();
		userLabel.setText("Welcome, " + user.getName());
		userLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		overviewPanel.add(userLabel, c);
		
		return overviewPanel;
	}
	
	/** Payroll panel.
	 * @return the JPanel to display
	 * @throws FileNotFoundException */
	private JPanel payroll() throws FileNotFoundException {
		payrollPanel = new JPanel();
		
		// fill a multidimensional array with a loop from the database

		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(tableModel);
		File doc = new File("TimeLogDB.txt");
		final Scanner scanner = new Scanner(doc, "UTF-8");
		
		//wipe the current table
		int rowCount = tableModel.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}

		double totalPayout = 0.0;
		int totalHrs = 0;
		int totalMins = 0;

		while (scanner.hasNextLine()) {
			final String lineFromFile = scanner.nextLine();
			List<String> user = Arrays.asList(lineFromFile.split("\\s*,\\s*"));
			// id, name, password

			// add to an arraylist of their hours
			String data0 = user.get(0);
			String data1 = user.get(1);
		    String data2 = user.get(2);
		    String data3 = user.get(3);
		    String data4 = user.get(4);
		    String data5 = user.get(5);
		    
		    // only show all unpaid, completed time logs
		    if (!data3.equals("null") && data5.equals("false")) {
			    // get that employee's name, wage
			    ValidateAccess v = new ValidateAccess();
			    List<String> thisEmployee = v.getUserData("WorkersDB.txt", data0);
			    String employeeName = thisEmployee.get(1);
			    double employeeRate = Double.parseDouble(thisEmployee.get(3));
			    
			    int hours = 0;
			    int minutes = 0;
			    String[] log = data4.split(" ");
			    
			    if (log.length == 4) {
			    	hours = Integer.parseInt(log[0]);
			    	minutes = Integer.parseInt(log[2]);
			    	System.out.println(log[0] + " " + log[1] + " " + log[2] + " " + log[3]);
			    	
			    	totalHrs += hours;
			    	totalMins += minutes;
			    } else {
			    	minutes = Integer.parseInt(log[0]);
			    	System.out.println(log[0] + " " + log[1]);
			    	totalMins += minutes;
			    }
			    
			    double timeWorked = (double) hours + (minutes / 60.0);
			    
			    currency = NumberFormat.getCurrencyInstance(Locale.US);
		        String pay = currency.format(timeWorked * employeeRate);
		        String hourlyRate = currency.format(employeeRate);
		        
		        totalPayout += (timeWorked * employeeRate);
	
			    Object[] rowData = new Object[] {employeeName, data1, data2, data3, data4, hourlyRate, pay};
			    
				tableModel.addRow(rowData);
		    }
		}

		scanner.close();
		
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
		
		
		if (totalHours != null && totalPayout != 0.00) {
			totalRowData = new Object[] {"TOTALS:", null, null, null, totalHours, null, currency.format(totalPayout)};
			tableModel.addRow(totalRowData);
		}
		
		// hours table header
		GridBagConstraints c = new GridBagConstraints();
	    payrollPanel.setLayout(new GridBagLayout());
	    c.ipady = 5;
	    c.fill = GridBagConstraints.BOTH;
	    c.anchor = GridBagConstraints.CENTER; 
		c.gridx = 0;
		c.gridy = 0;
		payrollPanel.add(table.getTableHeader(), c);
		
		// hours table
		c.gridx = 0;
		c.gridy = 1;
		payrollPanel.add(table, c);
		
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setPreferredSize(new Dimension(window.getWidth() - 325, table.getRowCount() * table.getRowHeight()));
		
		
		JButton setPaid = new JButton("Mark as Paid");
		setPaid.setFont(buttonStyle);
		c.gridy++;
		payrollPanel.add(setPaid, c);
		
		if (totalPayout == 0.00) {
			setPaid.setVisible(false);
			setPaid.setEnabled(false);
			
			JLabel allPaidLabel = new JLabel();
			allPaidLabel.setText("Payroll is up-to-date.");
			allPaidLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
			c.gridy++;
			payrollPanel.add(allPaidLabel, c);
		}
		
		setPaid.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
        		// replace all the false values with true
            	// update the database
    			File doc = new File("TimeLogDB.txt");
    			Scanner scanner = null;
				try {
					scanner = new Scanner(doc, "UTF-8");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
    			
    			String tempData = "";

    			if (scanner != null) {
	    			while (scanner.hasNextLine()) {
	    				final String lineFromFile = scanner.nextLine();
	    				List<String> user = Arrays.asList(lineFromFile.split("\\s*,\\s*"));
	    				// id, name, password
	    				if (user.get(5).equals("false") && !user.get(3).equals("null")) { 
	    				    
	    				    String updatedLine = 
	    				    		user.get(0) + ", " + user.get(1) + ", " + user.get(2) + ", " + user.get(3) + ", " + user.get(4) + ", true";
	    				    
					    	tempData += updatedLine;
					    	tempData += "\n";
	
	    				} else {
	    					tempData += lineFromFile;
	    					tempData += "\n";
	    				}
	    			}
    			
	    			// replace the file data with the value of tempData
	    			System.out.println(tempData);
	    			
					try {
						workers = new FileWriter("TimeLogDB.txt", false);
	
						if (tempData != null) {
							workers.write(tempData);
						}
						
					} catch (IOException e1) {
						e1.printStackTrace();
					} finally {
					    if (workers != null) {
					        try {
					        	workers.flush();                
					        } catch (IOException e1) {
					            e1.printStackTrace();
					        }
					        try {
					        	workers.close();
					        } catch (IOException e1) {
					            e1.printStackTrace();
					        }
					    }
					}
	    			
	
	    			scanner.close();
    			}
           
    			// refresh the panel
    			payrollPanel.removeAll();
    			try {
					setDashComponent(payroll());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
    			payrollPanel.revalidate();
    			payrollPanel.repaint();
            }
        });
		
		return payrollPanel;
	}
	
	
	/** Add an employee to the database.
	 * @return the panel */
	private JPanel addEmployee() {
		addEmployeePanel = new JPanel();
		
		addEmployeePanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel formLabel = new JLabel();
		formLabel.setText("Have the employee fill out this form:");
		formLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		c.gridx = 0;
		c.gridy = 0;
		addEmployeePanel.add(formLabel, c);
		
		JLabel statusLabel = new JLabel();
		statusLabel.setText("\n");
		statusLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		c.gridy++;
		addEmployeePanel.add(statusLabel, c);
		
		JLabel nameLabel = new JLabel();
		nameLabel.setText("Name: ");
		c.gridx = 0;
		c.gridy++;
		nameLabel.setFont(new Font("Arial", Font.PLAIN | Font.BOLD, 14));
		addEmployeePanel.add(nameLabel, c);
		
		JTextField empName = new JTextField(15); 
		empName.setFont(new Font("Arial", Font.PLAIN | Font.BOLD, 14));
		c.gridx = 1;
		addEmployeePanel.add(empName, c);
		
		JLabel passwordLabel = new JLabel();
		passwordLabel.setText("Password: ");
		c.gridx = 0;
		c.gridy++;
		passwordLabel.setFont(new Font("Arial", Font.PLAIN | Font.BOLD, 14));
		addEmployeePanel.add(passwordLabel, c);
		
		JPasswordField empPass = new JPasswordField(15); 
		empPass.setFont(new Font("Arial", Font.PLAIN | Font.BOLD, 14));
		c.gridx = 1;
		addEmployeePanel.add(empPass, c);
		
		JLabel wageLabel = new JLabel();
		wageLabel.setText("Hourly Wage: ");
		c.gridx = 0;
		c.gridy++;
		wageLabel.setFont(new Font("Arial", Font.PLAIN | Font.BOLD, 14));
		addEmployeePanel.add(wageLabel, c);
		
		JTextField empWage = new JTextField(15); 
		empWage.setFont(new Font("Arial", Font.PLAIN | Font.BOLD, 14));
		c.gridx = 1;
		addEmployeePanel.add(empWage, c);
		
		JButton createEmployee = new JButton("Create New Employee");
		createEmployee.setFont(buttonStyle);
		c.gridx = 1;
		c.gridy++;
		createEmployee.setMargin(new Insets(5, 5, 5, 5));
		addEmployeePanel.add(createEmployee, c);
		
		createEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            	String password = String.valueOf(empPass.getPassword());
            	
            	//check to make sure wage is valid
            	String wage = "0.00";
            	boolean validWage = true;
            	
            	if (!empWage.getText().equals("")) {
            		wage = empWage.getText();
            		
            		if (wage.equals("")) {
            			validWage = false;
            		}
            		
            		try {
            	        Integer.parseInt(wage);
            	    } catch (NumberFormatException e1) {
            	    	
            	    	try {
                	        Float.parseFloat(wage);
                	    } catch (NumberFormatException e11) {
                	    	wage = "0.00";
                	    	validWage = false;
                	    }
            	    }
            	    
            	    
            	}	
            	
            	if (validWage && (empName.getText().length()) > 0 && (empPass.getPassword().length) > 0 && (empWage.getText().length()) > 0) {
	        		AddWorker newEmp = new AddWorker();
	        		newEmp.setWorker(empName.getText(), password, wage);
	        		statusLabel.setText(empName.getText() + " successfully added. ");
	            	
	        		// reset the fields
	        		empName.setText("");
	        		empPass.setText("");
	        		empWage.setText("");
            	}  else {
            		statusLabel.setText("Please fill out all fields.");
            	}
            	
            	if (!validWage) {
            		statusLabel.setText("You must enter a valid wage. Try again.");
            		empWage.setText("");
            	}
            }
        });
		
		return addEmployeePanel;
	}
	
	/** Show a list of the employees working right now.
	 * @return panel to show
	 * @throws FileNotFoundException */
	private JPanel currentWorkers() throws FileNotFoundException {
		currentWorkersPanel = new JPanel();
		ValidateAccess v = new ValidateAccess();
		
		currentWorkersPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		String employeesClockedIn = "";
		
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
						String employeeName = thisEmployee.get(1);
						
						employeesClockedIn += employeeName + ", ";
				    }
				}
				
				scanner.close();
				
				if (!employeesClockedIn.equals("")) {
					JLabel clockedInLabel = new JLabel();
					clockedInLabel.setText("Current Employees Clocked In: " + employeesClockedIn.substring(0, employeesClockedIn.length() - 2));
					clockedInLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
					currentWorkersPanel.add(clockedInLabel, c);
				} else {
					JLabel clockedInLabel = new JLabel();
					clockedInLabel.setText("There are no employees clocked in.");
					clockedInLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
					currentWorkersPanel.add(clockedInLabel, c);
				}
				
			} else {
				JLabel clockedInLabel = new JLabel();
				clockedInLabel.setText("There are no employees clocked in.");
				clockedInLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
				currentWorkersPanel.add(clockedInLabel, c);
			}
		 } else {
			 JLabel clockedInLabel = new JLabel();
			 clockedInLabel.setText("There are currently no employees. Please add one.");
			 clockedInLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
			 currentWorkersPanel.add(clockedInLabel, c);
		 }	
		
		return currentWorkersPanel;
	}
	
	
	/** Edit employee info.
	 * @return panel to show
	 * @throws FileNotFoundException */
	private JPanel editWorkers() throws FileNotFoundException {
		editWorkersPanel = new JPanel();
		
		editWorkersPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		

		
		String[] columnNames = {
				"ID", 
				"Name",
				"Password",
                "Wage"
                };
		
		// fill a multidimensional array with a loop from the database

		tableModel = new DefaultTableModel(columnNames, 0);
		
		@SuppressWarnings("serial")
		JTable table = new JTable(tableModel) {
			public boolean isCellEditable(final int row, final int column) {
			    if (column == 0) {
			    	return false;
			    }
			    
			    return true;
			  }
		};
		
		File doc = new File("WorkersDB.txt");
		final Scanner scanner = new Scanner(doc, "UTF-8");
		

		while (scanner.hasNextLine()) {
			final String lineFromFile = scanner.nextLine();
			List<String> user = Arrays.asList(lineFromFile.split("\\s*,\\s*"));
			
			// id, name, password, wage
			String data0 = user.get(0);
			String data1 = user.get(1);
			String data2 = user.get(2);
		    String data3 = user.get(3);

		    Object[] rowData = new Object[] {data0, data1, data2, data3};
		    
			tableModel.addRow(rowData);
		}
		
		for (int col = 0; col < columnNames.length; col++) {
			for (int row = 0; row < tableModel.getRowCount(); row++) {
				tableModel.isCellEditable(row, col);
			}
		}
		
		scanner.close();
		
		JLabel editEmpLabel = new JLabel();
		editEmpLabel.setText("Edit employee info:");
		editEmpLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		c.gridx = 0;
		c.gridy = 0;
		editWorkersPanel.add(editEmpLabel, c);
		
		JLabel statusLabel = new JLabel();
		statusLabel.setText(" ");
		statusLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		c.gridy++;
		editWorkersPanel.add(statusLabel, c);
		
		// hours table header
	    c.ipady = 5;
	    c.fill = GridBagConstraints.BOTH;
	    c.anchor = GridBagConstraints.CENTER; 
	    c.gridy++;
		editWorkersPanel.add(table.getTableHeader(), c);
		
		// hours table
		c.gridx = 0;
		c.gridy++;
		editWorkersPanel.add(table, c);
		
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setPreferredSize(new Dimension(window.getWidth() - 325, table.getRowCount() * table.getRowHeight()));
		
		if (table.getRowCount() > 0) {
			JButton updateWorkersDB = new JButton("Update");
			updateWorkersDB.setFont(buttonStyle);
			c.gridy++;
			editWorkersPanel.add(updateWorkersDB, c);
			
			updateWorkersDB.addActionListener(new ActionListener() {
	            public void actionPerformed(final ActionEvent e) {
						try {
							updateWorkersDB();
							statusLabel.setText("Database updated successfully.");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
	            }

	        });
		}
		
		return editWorkersPanel;
	}
	
	/** Replace the database values with all values in the table. 
	 * @throws IOException */
	private void updateWorkersDB() throws IOException {
		String newWorkersDB = "";
		
		for (int count = 0; count < tableModel.getRowCount(); count++) {
			newWorkersDB += tableModel.getValueAt(count, 0) + ", " + tableModel.getValueAt(count, 1) 
				+ ", " + tableModel.getValueAt(count, 2) + ", " + tableModel.getValueAt(count, 3) + "\n";
		}
		
		System.out.println(newWorkersDB);
		
		FileWriter workers = new FileWriter("WorkersDB.txt", false);
		workers.write(newWorkersDB);
		workers.close();
		
	}
}
