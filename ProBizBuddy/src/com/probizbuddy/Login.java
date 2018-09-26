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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
	
	JFrame window;
	
	JPanel login = new JPanel();;
	
	JButton signin;
	
	JTextField idInput;
	
	JPasswordField passInput;
	
	String eID, password;
	
	List<String> employeeList, managersList;
	
	boolean isManager;
	
	Login(JFrame pWindow) {
		window = pWindow;
		eID = "";
		password = "";
		
	}
	
	public boolean userFound(String file, String id, String pass) throws FileNotFoundException {
		File doc = new File(file);
		final Scanner scanner = new Scanner(doc);
		System.out.println("Searching DB for user");
		while (scanner.hasNextLine()) {
		   final String lineFromFile = scanner.nextLine();
		   System.out.println("Line: " + lineFromFile);
		   if (lineFromFile.contains(id) && lineFromFile.contains(pass)) { 
		       // a match!
		       System.out.println("I found " + id);
		       return true;
		   }
		}
		
		return false;
	}
	
	
	/** check to see whether the credentials are valid for a user.
	 *  @return whether it is valid */
	public boolean validateCredentials(String pId, String pPassword) 
			throws FileNotFoundException {
		// match what was entered to the database
		System.out.println("Validating Credentials");
		eID = pId;
		password = pPassword;
		
		if (userFound("ManagersDB.txt", eID, password)) {
			System.out.println("Manager Found. Logging in.");
			isManager = true;
			return true;
		} else if (userFound("WorkersDB.txt", eID, password)) {
			System.out.println("Worker Found. Logging in.");
			isManager = false;
			return true;
		} else {
			System.out.println("Cannot find user.");
		}
		
		return false;
	}

	
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
		idLabel.setText("Employee ID: ");
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		idLabel.setFont(new Font("Arial", Font.PLAIN | Font.BOLD, 14));
		login.add(idLabel, c);
		
		idInput = new JTextField(5);
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 2;
		idInput.setFont(new Font("Arial", Font.PLAIN | Font.BOLD, 14));
		login.add(idInput, c);
		
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
			 
            public void actionPerformed(final ActionEvent e) {
            	
            		if (!idInput.getText().equals("")) {
            			if (!(passInput.getPassword().length == 0)) {
            				
            				// see if the entry matches something in the database
            				System.out.println("Comparing info with database.");
            				
            				try {
            					String passText = new String(passInput.getPassword());
            					
            					if (validateCredentials(idInput.getText(), passText)) {
									
									// determine whether they are an employee or manager
									// manager
									if (isManager) {
										ManagerPanel employee = new ManagerPanel(window, idInput.getText());
										login.setVisible(false); //hide the log in panel
										employee.showPanel();
									} else {
										// employee
										EmployeePanel employee = new EmployeePanel(window, idInput.getText());
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
