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
	
	
}
