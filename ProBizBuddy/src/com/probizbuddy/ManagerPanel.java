package com.probizbuddy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Main area for managers to control Pro Biz Buddy. */
public class ManagerPanel {
	
	/** Window. */
	private JFrame window;
	
	/** Panel holding manager tools. */
	private JPanel layout, nav, dash;
	
	/** Navigation buttons. */
	private JButton overview, payWages, currentWorkers, addEmployee, logout;
	
	/** Strings storing the manager's info. */
	private String mID, name;
	
	/** Manager list from db. */
	private List<String> manager;
	
	/** Style of all buttons. */
	private Font buttonStyle = new Font("Arial", Font.PLAIN, 16);
	
	/** logged in as manager. 
	 * @throws FileNotFoundException 
	 * @param pWindow : window
	 * @param pName : manager's name */
	ManagerPanel(final JFrame pWindow, final String pName) throws FileNotFoundException {
		window = pWindow;
		layout = new JPanel();
		nav = new JPanel();
		dash = new JPanel();
		
		name = pName;
		
		ValidateAccess v = new ValidateAccess(window);
		manager = v.getUserData("ManagersDB.txt", name);
		//mID = manager.get(0);
	}
	
	
	/** Show the panel on the GUI. */
	public void showPanel() {
		System.out.println("Logged in, show the manager's panel.");
		
		layout.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
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
		
		overview.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
        		overview.setEnabled(false);
            	payWages.setEnabled(true);
        		currentWorkers.setEnabled(true);
        		addEmployee.setEnabled(true);
            	
            }
        });
		
		
		payWages = new JButton("Pay Wages");
		payWages.setFont(buttonStyle);
		
		c.gridy++;
		nav.add(payWages, c);
		
		payWages.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
       			overview.setEnabled(true);
        		payWages.setEnabled(false);
        		currentWorkers.setEnabled(true);
        		addEmployee.setEnabled(true);
        		
            }
        });
		
		
		currentWorkers = new JButton("View Current Workers");
		currentWorkers.setFont(buttonStyle);
		
		c.gridy++;
		nav.add(currentWorkers, c);

		currentWorkers.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            	overview.setEnabled(true);
            	payWages.setEnabled(true);
        		currentWorkers.setEnabled(false);
        		addEmployee.setEnabled(true);
            }
        });
		
		
		addEmployee = new JButton("Add Employee");
		addEmployee.setFont(buttonStyle);
		
		c.gridy++;
		nav.add(addEmployee, c);

		addEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            	overview.setEnabled(true);
            	payWages.setEnabled(true);
        		currentWorkers.setEnabled(true);
        		addEmployee.setEnabled(false);
        		
            }
        });
		
		
		logout = new JButton("Log Out");
		logout.setFont(buttonStyle);
		
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
	
	
	
	
}
