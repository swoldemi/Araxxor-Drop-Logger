package DropLogger;

import java.sql.SQLException;

public class Main{
	public static void main(String[] args) {
		// Create a Login instance and check for spaces in the username for SQL table creation
		Login login = new Login();
		
		// Wait without threading but using a synchronized Login.actionPerformed method
		login.waiting = true;
		while(login.waiting){
			login.waiting = login.listen();
		}
		
		String user_name = login.sanitizeUsername(login.getUsername());
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

