package com.probizbuddy;

import javax.swing.JFrame;

public class Login {
	
	JFrame window;
	
	Login(JFrame pWindow) {
		window = pWindow;
	}
	
	public boolean validateCredentials() {
		return false;
	}

	public void showPanel() {
		System.out.println("Show the log in screen");
		
	}
}
