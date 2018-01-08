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
		String make_main_table = "CREATE TABLE IF NOT EXISTS " + user_name + "("
						+ "kill_number int, "
						+ "arrows_pheromone int, "
						+ "charms varchar(255), "
						+ "rocktails_sarabrews_overloads varchar(255), "
						+ "main_loot varchar(255), "
						+ "unique_drops varchar(255),"
						+ "UNIQUE KEY(kill_number)) ";
		
		String make_pet_table = "CREATE TABLE IF NOT EXISTS " + user_name + "_pets("
						+ "araxyte_pet bool, "
						+ "barry bool, "
						+ "mallory bool)";
		myState.executeUpdate(make_main_table);
		myState.executeUpdate(make_pet_table);
	}
	
	/* 
	 * Get the current state of the table
	 */
	public ResultSet getTable(String user_name) throws SQLException{
		String select_all = "SELECT * FROM " + user_name;
		this.myResult = myState.executeQuery(select_all); 
		return this.myResult;
	}
	
	public void insertRow(String table_name) throws SQLException{
		//INSERT INTO data_file VALUES(1, 74, "12 Blue Charm", "12 Saradomin brew flask (6)", "220 Onyx bolts", null)
		String insertion_query = "INSERT INTO " + table_name + " VALUES("
				+ Drops.kill_number + ", "
				+ Drops.arrow_pheromone_drop + ", "
				+ "'" + Drops.charms_drop + "', "
				+ "'" + Drops.food_potions_drop + "', "
				+ "'" + Drops.main_loot_drop + "', "
				+ "'" + Drops.unique_drops_drop + "')";
		this.myState.executeUpdate(insertion_query);
	}
	
	public int getLength(String table_name) throws SQLException{
		int length = 0;
		String insertion_query = "SELECT kill_number FROM " + table_name;
		this.myResult = this.myState.executeQuery(insertion_query);
		while(this.myResult.next()){
			length++;
		}
		return length;
	}
}
