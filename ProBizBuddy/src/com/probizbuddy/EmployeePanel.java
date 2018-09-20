package com.probizbuddy;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/** Work space for employees. */
public class EmployeePanel {
	
	/** clock in and clock out buttons. */
	private JButton clockIn, clockOut;

	/** 
	 * constructor to set up the window.
	 * @param employeeID : the employee that is logged in
	 * @param window : the gui
	 * */
	public EmployeePanel(final JFrame window, final String employeeID) {
		System.out.println("Logged in as " + employeeID);
		
		/** clock in/out buttons */
		
		GridBagConstraints c = new GridBagConstraints();


		clockIn = new JButton("Clock In");

		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.ipady = 20;
		c.weighty = 1.0;   //request any extra vertical space
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER; 
		c.insets = new Insets(10, 0, 0, 0); // top padding
		
		window.add(clockIn, c);
		clockIn.setEnabled(true);
		clockIn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            	//clockIn();
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
		window.add(clockOut, c);
		clockOut.setEnabled(true);
		
		window.repaint();
		
	}

}
