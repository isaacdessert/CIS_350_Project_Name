package com.probizbuddy;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/** Login for employees and employers. */
public class Login {
	
	/** window. */
	private JFrame window;
	
	/** Panel that holds log in fields. */
	private JPanel login = new JPanel();
	
	/** sign in button. */
	private JButton signin;
	
	/** text field for user or employee id. */
	private JTextField nameInput;
	
	/** password field. */
	private JPasswordField passInput;
	
	/** string to store id and password. */
	private String eName, password;
	
	/** whether the person logging in is a manager or employee. */
	private boolean isManager;
	
	
	/** Constructor for viewing the log in screen.
	 * @param pWindow : the window */
	Login(final JFrame pWindow) {
		window = pWindow;
		eName = "";
		password = "";
	}
	
	
	/** Function to find the user in the database. 
	 * @param file : file name
	 * @param name : user's name
	 * @param pass : user's password
	 * @return whether the user was found
	 * @throws FileNotFoundException : whether the database file exists */
	public boolean userFound(final String file, final String name, final String pass) 
			throws FileNotFoundException {
		File doc = new File(file);
		final Scanner scanner = new Scanner(doc);
		System.out.println("Searching DB for user");
		while (scanner.hasNextLine()) {
		   final String lineFromFile = scanner.nextLine();
		   System.out.println("Line: " + lineFromFile);
		   
		   //convert line to an array and pull elements
		   //this way you can see if the whole thing matches
		   List<String> user = Arrays.asList(lineFromFile.split("\\s*,\\s*"));
		   // id, name, password
		   if (user.get(1).equals(name) && user.get(2).equals(pass)) { 
		       // a match!
			   scanner.close();
		       System.out.println("I found " + name);
		       return true;
		   }
		}
		
		scanner.close();
		return false;
	}
	
	
	/** check to see whether the credentials are valid for a user.
	 * @param pName : person's name
	 * @param pPassword : person's password
	 * @return whether it is valid
	 * @throws FileNotFoundException : whether the database file exists */
	public boolean validateCredentials(final String pName, final String pPassword) 
			throws FileNotFoundException {
		// match what was entered to the database
		System.out.println("Validating Credentials");
		eName = pName;
		password = pPassword;
		
		if (userFound("ManagersDB.txt", eName, password)) {
			System.out.println("Manager Found. Logging in.");
			isManager = true;
			return true;
		} else if (userFound("WorkersDB.txt", eName, password)) {
			System.out.println("Worker Found. Logging in.");
			isManager = false;
			return true;
		} else {
			System.out.println("Cannot find user.");
		}
		
		return false;
	}

	
	/** show the log in panel on the jframe. */
	public void showPanel() {
		System.out.println("-- Show the log in screen --");
		
		login.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		Color blue = new Color(66, 153, 229);
		login.setBackground(blue);
		
		JLabel titleLabel = new JLabel();
		titleLabel.setText("Enter your credentials");
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.insets = new Insets(10, 10, 10, 10);
		titleLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		login.add(titleLabel, c);
		
		JLabel errorLabel = new JLabel();
		errorLabel.setText("");
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		errorLabel.setFont(new Font("Arial", Font.BOLD, 14));
		
		errorLabel.setHorizontalAlignment(JLabel.CENTER);
		login.add(errorLabel, c);
		
		JLabel idLabel = new JLabel();
		idLabel.setText("Name: ");
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		idLabel.setFont(new Font("Arial", Font.PLAIN | Font.BOLD, 14));
		login.add(idLabel, c);
		
		nameInput = new JTextField(5);
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 2;
		nameInput.setFont(new Font("Arial", Font.PLAIN | Font.BOLD, 14));
		login.add(nameInput, c);
		
		JLabel passwordLabel = new JLabel();
		passwordLabel.setText("Password: ");
		c.gridx = 0;
		c.gridy = 3;
		passwordLabel.setFont(new Font("Arial", Font.PLAIN | Font.BOLD, 14));
		login.add(passwordLabel, c);
		
		passInput = new JPasswordField(15);
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 2;
		passInput.setFont(new Font("Arial", Font.PLAIN | Font.BOLD, 14));
		login.add(passInput, c);
		
		signin = new JButton("Sign In");
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 2;
		signin.setMargin(new Insets(5, 5, 5, 5));
		login.add(signin, c);
		
		signin.addActionListener(new ActionListener() {
			
			// if the sign in button was pushed
            public void actionPerformed(final ActionEvent e) {
            	
            		if (!nameInput.getText().equals("")) {
            			if (!(passInput.getPassword().length == 0)) {
            				
            				// see if the entry matches something in the database
            				System.out.println("Comparing info with database.");
            				
            				try {
            					String passText = new String(passInput.getPassword());
            					
            					if (validateCredentials(nameInput.getText(), passText)) {
									
									// determine whether they are an employee or manager
									// manager
									if (isManager) {
										ManagerPanel employee = new ManagerPanel(window, nameInput.getText());
										login.setVisible(false); //hide the log in panel
										employee.showPanel();
									} else {
										// employee
										EmployeePanel employee = new EmployeePanel(window, nameInput.getText());
										login.setVisible(false); //hide the log in panel
										employee.showPanel();
									}
								} else {
									errorLabel.setText("Incorrect credentials. Try again.");
								}
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
            				
                		} else {
                			errorLabel.setText("Please enter your password.");
                		}
            		} else {
            			errorLabel.setText("Please enter your assigned Employee ID.");
            		}
                
            }
        });
		
		window.getContentPane().add(login);
	}
}
