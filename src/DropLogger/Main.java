package DropLogger;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		String user_name = "Data_File";
		DatabaseConnector connector = new DatabaseConnector();
		
		// Make an instance of the interface and attempt to make a table using the username provided on login
		try {
			LoggerInterface user = new LoggerInterface(user_name, connector);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
