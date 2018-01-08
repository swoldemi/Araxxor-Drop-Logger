package DropLogger;
import java.sql.*;

public class DatabaseConnector {
	private String url = DatabaseCredentials.url;
	private String password = DatabaseCredentials.password;
	private String username = DatabaseCredentials.username;
	Connection myConn;
	Statement myState;
	ResultSet myResult;
	
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
						+ "arrows_pheromone int, "
						+ "charms varchar(255), "
						+ "rocktails_sarabrews_overloads varchar(255), "
						+ "main_loot varchar(255), "
						+ "unique_drops varchar(255),"
						+ "UNIQUE KEY(kill_number)) ";
		myState.executeUpdate(make_table);
	}
	
	/* 
	 * Get the current state of the table
	 */
	public ResultSet getTable(String user_name) throws SQLException{
		String select_all = "SELECT * FROM " + user_name;
		this.myResult = myState.executeQuery(select_all); 
		return this.myResult;
	}
}
