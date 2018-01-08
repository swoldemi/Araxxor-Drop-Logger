package DropLogger;
import java.sql.*;

public class DatabaseConnector {
	String url = DatabaseCredentials.url;
	String password = DatabaseCredentials.password;
	String username = DatabaseCredentials.username;
	Connection myConn;
	Statement myState;
	
	DatabaseConnector(){
    	try {
			this.myConn = DriverManager.getConnection(this.url, this.username, this.password);
			this.myState = this.myConn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}    	
	}
	
	public void makeTable(String user_name) throws SQLException{
		String make_table = "CREATE TABLE IF NOT EXISTS " + user_name + "("
						+ "kill_number int, "
						+ "arrows_pheromone varchar(255), "
						+ "charms varchar(255), "
						+ "rocktails_sarabrews_overloads varchar(255), "
						+ "main_loot varchar(255), "
						+ "unique_drops varchar(255)"
						+ "UNIQUE KEY(kill_number)) ";
		System.out.println(make_table);
		myState.executeUpdate(make_table);
	}
	
	
}
