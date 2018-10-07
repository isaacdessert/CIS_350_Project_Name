package com.probizbuddy;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Main area for managers to control Pro Biz Buddy. */
public class ManagerPanel {
	
	/** Window. */
	private JFrame window;
	
	/** Panel holding manager tools. */
	private JPanel managerTools;
	
	/** Strings storing the manager's info. */
	private String mID, name;
	
	/** Manager list from db. */
	private List<String> manager;
	
	/** logged in as manager. 
	 * @throws FileNotFoundException 
	 * @param pWindow : window
	 * @param pName : manager's name */
	ManagerPanel(final JFrame pWindow, final String pName) throws FileNotFoundException {
		window = pWindow;
		managerTools = new JPanel();
		name = pName;
		
		ValidateAccess v = new ValidateAccess(window);
		manager = v.getUserData("ManagersDB.txt", name);
		mID = manager.get(0);
	}
	
	
	/** Show the panel on the GUI. */
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
