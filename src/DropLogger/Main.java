package DropLogger;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		DatabaseConnector connector = new DatabaseConnector();
		try {
			connector.makeTable("Data_File");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
