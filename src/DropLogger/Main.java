package DropLogger;

import java.sql.SQLException;

public class Main{
	public static void main(String[] args) {
		// Create a Login instance and check for spaces in the username for SQL table creation
		Login login_interface = new Login();
		
		// Wait without threading but using a synchronized Login.actionPerformed method
		login_interface.waiting = true; // Highly volatile boolean
		while(login_interface.waiting){
			login_interface.waiting = login_interface.listen();
		}
		
		String user_name = login_interface.sanitizeUsername(login_interface.getUsername());
		DatabaseConnector connector = new DatabaseConnector();
		
		// Define a reference for the LoggerInterface
    	LoggerInterface user = null;
    	
    	// Make an instance of the interface and attempt to make a table using the username provided on login
		try {
			user = new LoggerInterface(user_name, connector);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Start the logger
		try {
			user.Logger();
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
}

