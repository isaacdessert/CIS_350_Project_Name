package com.probizbuddy;

import java.awt.Color;
import javax.swing.JFrame;

/** gui. */
public class GUI {
	
	/** create the GUI. */
	public static void main(String[] args) {
		JFrame window = new JFrame("ProBizBuddy");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//StartProgram start = new StartProgram(window);
		ValidateAccess validate = new ValidateAccess(window);
		
		/*
		if (start.getSettings() != null 
				&& start.getSettings().get(0) != null) {
			window.setTitle((String) start.getSettings().get(0) 
					+ " - ProBizBuddy");
		}
		*/
		
		window.pack();
		Color blue = new Color(66, 153, 229);
		window.getContentPane().setBackground(blue);
		//window.setLocationRelativeTo(null);
		window.setSize(1080, 720);
		
		
		window.setVisible(true);
	}
}
