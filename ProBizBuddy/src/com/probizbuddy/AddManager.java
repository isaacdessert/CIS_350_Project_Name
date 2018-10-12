package com.probizbuddy;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/** Add a manager to the database. */
public class AddManager extends JPanel {
	
	/** window. */
	private JFrame window;
	
	/** fields to add a manager. */
	private JTextField nameInput, passInput;
	
	/** add manager button. */
	private JButton login;
	
	/** holds fields for adding a manager. */
	private JPanel newManager = new JPanel();
	
	/** Constructor for adding to the window.
	 * @param pWindow : window */
	AddManager(final JFrame pWindow) {
		window = pWindow;
	}
	
	
	/** show the form to add a manager. */
	public void showForum() {
		// show name and password fields
		System.out.println("-- Show a username and password field --");
		
		newManager.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		Color blue = new Color(66, 153, 229);
		newManager.setBackground(blue);
		
		JLabel titleLabel = new JLabel();
		titleLabel.setText("Create a New Manager Account");
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.insets = new Insets(10, 10, 10, 10);
		titleLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		newManager.add(titleLabel, c);
		
		JLabel errorLabel = new JLabel();
		errorLabel.setText("");
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		errorLabel.setFont(new Font("Arial", Font.BOLD, 14));
		
		errorLabel.setHorizontalAlignment(JLabel.CENTER);
		newManager.add(errorLabel, c);
		
		JLabel nameLabel = new JLabel();
		nameLabel.setText("Name: ");
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		nameLabel.setFont(new Font("Arial", Font.PLAIN | Font.BOLD, 14));
		newManager.add(nameLabel, c);
		
		JTextField nameInput = new JTextField(5);
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 2;
		nameInput.setFont(new Font("Arial", Font.PLAIN | Font.BOLD, 14));
		newManager.add(nameInput, c);
		
		JLabel passwordLabel = new JLabel();
		passwordLabel.setText("Password: ");
		c.gridx = 0;
		c.gridy = 3;
		passwordLabel.setFont(new Font("Arial", Font.PLAIN | Font.BOLD, 14));
		newManager.add(passwordLabel, c);
		
		JPasswordField passInput = new JPasswordField(15);
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 2;
		passInput.setFont(new Font("Arial", Font.PLAIN | Font.BOLD, 14));
		newManager.add(passInput, c);
		
		login = new JButton("Create Account");
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 2;
		login.setMargin(new Insets(5, 5, 5, 5));
		newManager.add(login, c);
		
		login.addActionListener(new ActionListener() {
			 
            public void actionPerformed(final ActionEvent e) {
            	
            	String passText = new String(passInput.getPassword());
            	
            		if (!nameInput.getText().equals("")) {
            			if (passInput.getPassword().length > 0) {
            				setManager(nameInput.getText(), passText);
            				System.out.println("Account Created.");
                		} else {
                			errorLabel.setText("Please enter a password.");
                		}
            		} else {
            			errorLabel.setText("Please enter a first and last name.");
            		}
                
            }
        });
		
		window.getContentPane().add(newManager);
		
	}
	
	/** add the manager to the database.
	 * @param name : manager's name
	 * @param password : password */
	public void setManager(final String name, final String password) {
		try (FileWriter fw = new FileWriter("ManagersDB.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
				
		    PrintWriter out = new PrintWriter(bw)) {
				GenerateID mID = new GenerateID();
				String mid = mID.getID();
				
			    System.out.println(name + " added");
				out.println(mid + ", " + name + ", " + password);
				
				// redirect them to their manager's panel
    				newManager.setVisible(false);
				ManagerPanel manager = new ManagerPanel(window, name);
				manager.showPanel();

			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		
		
		
	}
}
