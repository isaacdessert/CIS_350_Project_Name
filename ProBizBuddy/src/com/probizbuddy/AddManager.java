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
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/** Add a manager to the database. */
@SuppressWarnings("serial")
public class AddManager extends JPanel {
	
	/** window. */
	private JFrame window;
	
	/** fields to add a manager. */
	private JTextField nameInput;
	
	/** Input fields. */
	private JPasswordField passInput;
	
	/** Show errors to the user. */
	private JLabel errorLabel;
	
	/** add manager button. */
	private JButton login;
	
	/** holds fields for adding a manager. */
	private JPanel newManager;
	
	/** Constructor for adding to the window.
	 * @param pWindow : window */
	AddManager(final JFrame pWindow) {
		window = pWindow;
		newManager = new JPanel();
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
		
		errorLabel = new JLabel();
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
		
		nameInput = new JTextField(5);
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
		
		passInput = new JPasswordField(15);
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
            	try {
					createManager();
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
            }
        });
		
		// allow pressing enter to attempt sign in
		Action action = new AbstractAction() {
		    @Override
		    public void actionPerformed(final ActionEvent e) {
		    	System.out.println("Attempting account creation..");
		    	try {
					createManager();
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
		    }
		};
		
		passInput.addActionListener(action);
		
		
		if (window != null) {
			window.getContentPane().add(newManager);
		} else {
			System.out.println("window is null");
		}
		
	}
	
	
	/** Create a manager account.
	 * @return whether it was done successfully. 
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException */
	private boolean createManager() throws UnsupportedEncodingException, FileNotFoundException {
		String passText = new String(passInput.getPassword());
    	
		if (!nameInput.getText().equals("")) {
			if (passInput.getPassword().length > 0) {
				setManager(nameInput.getText(), passText);
				System.out.println("Account Created.");
				return true;
    		} else {
    			errorLabel.setText("Please enter a password.");
    		}
		} else {
			errorLabel.setText("Please enter a first and last name.");
		}
		
		return false;
	}
	
	/** add the manager to the database.
	 * @param name : manager's name
	 * @param password : password 
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException */
	public void setManager(final String name, final String password) throws UnsupportedEncodingException, FileNotFoundException {
		File file = new File("ManagersDB.txt");
		Writer w = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
		PrintWriter pw = new PrintWriter(w);
	
		GenerateID mID = new GenerateID();
		String mid = mID.getID();
		
	    System.out.println(name + " added");
	    pw.println(mid + ", " + name + ", " + password);
	    
	    // create manager object to pass
	    ValidateAccess v = new ValidateAccess();
	    Manager manager = v.createManager(name);
		
		// redirect them to their manager's panel
		newManager.setVisible(false);
		ManagerPanel managerPanel = new ManagerPanel(window, manager);
		managerPanel.showPanel();
		
		pw.close();
	}
}
