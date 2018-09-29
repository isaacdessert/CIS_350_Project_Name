package com.probizbuddy;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ManagerPanel {
	
	
	JFrame window;
	
	JPanel managerTools;
	
	String mID, name;
	
	List<String> manager;
	
	/** logged in as manager. 
	 * @throws FileNotFoundException */
	ManagerPanel(final JFrame pWindow, final String id) throws FileNotFoundException {
		window = pWindow;
		managerTools = new JPanel();
		mID = id;
		
		ValidateAccess v = new ValidateAccess(window);
		manager = v.getUserData("ManagersDB.txt", mID);
		name = manager.get(1);
	}
	
	

	
	public void showPanel() {
		System.out.println("Logged in, show the manager's panel.");
		
		managerTools.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel welcome = new JLabel();
		welcome.setText("Welcome, " + name);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		welcome.setFont(new Font("Arial", Font.PLAIN | Font.BOLD, 14));
		managerTools.add(welcome, c);

		window.getContentPane().add(managerTools);
	}
	
}
