package DropLogger;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Main {
	public static String sanitizeUsername(String user_name){
		if(user_name.contains(" ")){
			user_name.replace(" ", "_"); // replace spaces with underscores
		}
		return user_name;
	}
	public static void main(String[] args) {
		DatabaseConnector connector = new DatabaseConnector();
		LoggerInterface user = null;
		
		// Request user name for login
		String user_name = JOptionPane.showInputDialog("Please enter your username to view your drop log.");
		
		// check for spaces in the username for SQL table creation
		sanitizeUsername(user_name);
		// Make an instance of the interface and attempt to make a table using the username provided on login
		try {
			user = new LoggerInterface(user_name, connector);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Begin the logger
		try {
			user.Logger();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
