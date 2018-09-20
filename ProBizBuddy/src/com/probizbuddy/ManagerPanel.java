package com.probizbuddy;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ManagerPanel {
	
	
	JFrame window;
	
	JPanel managerTools;
	
	String mID, name;
	
	List<String> manager;
	
	ManagerPanel(JFrame pWindow){
		window = pWindow;
		managerTools = new JPanel();
		manager = getManager();
		
		System.out.println(manager);
		//mID = manager[0];
		//name = manager.get(1);
	}
	
	
	/** Get the array of info from the database about this manager. 
	 * @return array from database. */
	public List<String> getManager() {
		ValidateAccess v = new ValidateAccess(window);
		if (v.fileExists("ManagersDB.txt")) {
			return v.dbResults("ManagersDB.txt");
			
		} else {
			// abort and log out
			managerTools.setVisible(false);
			
			AddManager manager = new AddManager(window);
			manager.showForum();
		}
		
		return null;
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
