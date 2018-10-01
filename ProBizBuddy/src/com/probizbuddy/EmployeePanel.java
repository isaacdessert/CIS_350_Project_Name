package com.probizbuddy;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/** Work space for employees. */
public class EmployeePanel {
	
	/** clock in and clock out buttons. */
	private JButton clockIn, clockOut;
	
	/** panel for clocking in and out. */
	private JPanel clock = new JPanel();

	/** 
	 * constructor to set up the window.
	 * @param employeeID : the employee that is logged in
	 * @param window : the gui
	 * */
	public EmployeePanel(final JFrame window, final String employeeID) {
		System.out.println("Logged in as " + employeeID);
		
		/** clock in/out buttons */
		
		clock.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		Color blue = new Color(66, 153, 229);
		clock.setBackground(blue);


		clockIn = new JButton("Clock In");

		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.ipady = 20;
		c.weighty = 1.0;   //request any extra vertical space
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER; 
		c.insets = new Insets(10, 0, 0, 0); // top padding
		
		clock.add(clockIn, c);
		clockIn.setEnabled(true);
		clockIn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            		System.out.println("Clocking in...");
            		clockIn();
            }
        });

		
		clockOut = new JButton("Clock Out");
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.ipady = 20;
		c.weighty = 1.0;   //request any extra vertical space
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER; 
		c.insets = new Insets(10, 0, 0, 0);  //top padding
		clock.add(clockOut, c);
		clockOut.setEnabled(false);
		clockOut.setVisible(false);
		clockOut.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            		System.out.println("Clocking out...");
            		clockOut();
            }
        });
		
		window.getContentPane().add(clock);
	}
	
	
	/** Display the panel to the GUI. */
	public void showPanel() {
		System.out.println("Display employee panel");
	}
	
	
	/** User still clocks in. */
	public void clockIn() {
		clockIn.setEnabled(false);
		clockIn.setVisible(false);
		
		clockOut.setEnabled(true);
		clockOut.setVisible(true);
		
		// add the time to the database
		
	}
	
	
	/** User clocks out. */
	public void clockOut() {
		clockOut.setEnabled(false);
		clockOut.setVisible(false);
		
		clockIn.setEnabled(true);
		clockIn.setVisible(true);
	}
	

}
