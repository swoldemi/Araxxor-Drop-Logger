package araxxorDropLogger;

import java.sql.*;

public class DatabaseConnector {
	private String url = DatabaseCredentials.url;
	private String password = DatabaseCredentials.password;
	private String username = DatabaseCredentials.username;
	public Connection myConn;
	public Statement myState;
	public ResultSet myResult;
	
	private boolean DEBUG = false;
	
	DatabaseConnector(){
    	try {
			this.myConn = DriverManager.getConnection(this.url, this.username, this.password);
			this.myState = this.myConn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}    	
	}
	
	/*
	 * Make tables for the user on login
	 */
	public void makeTable(String user_name) throws SQLException{
		String make_main_table = "CREATE TABLE IF NOT EXISTS " + user_name + "("
						+ "kill_number int DEFAULT -1, "
						+ "arrows_pheromone int DEFAULT -1, "
						+ "charms varchar(255) DEFAULT 'null', "
						+ "rocktails_sarabrews_overloads varchar(255) DEFAULT 'null', "
						+ "main_loot varchar(255) DEFAULT 'null', "
						+ "unique_drops varchar(255) DEFAULT 'null',"
						+ "UNIQUE KEY(kill_number)) ";
		
		String make_pet_table = "CREATE TABLE IF NOT EXISTS " + user_name + "_pets("
						+ "araxyte_pet bool DEFAULT 0, "
						+ "barry bool DEFAULT 0, "
						+ "mallory bool DEFAULT 0);";
		
		if(DEBUG){
			System.out.println(make_main_table);
			System.out.println(make_pet_table);
		}
		
		// Make the tables
		this.myState.executeUpdate(make_main_table);
		this.myState.executeUpdate(make_pet_table);
		
		// Check if the tables are empty
		// If they are, insert default values
		// When we insert for the first time into these tables, we need to delete these entries
		this.myResult = this.myState.executeQuery("SELECT * FROM " + user_name + " LIMIT 1");
		this.myResult.next(); 

		if(!this.myResult.isAfterLast()){
			if(DEBUG)
				System.out.println(this.myResult.getString("kill_number") + " AAAAAAAAAA");
			this.myState.executeUpdate("INSERT INTO " + user_name + " VALUES()"); // Insert default values
		}
		this.myResult.close();
		
		// Do the same for the pets table
		this.myResult = this.myState.executeQuery("SELECT * FROM " + user_name + "_pets LIMIT 1");
		this.myResult.next();
		if(!this.myResult.isAfterLast()){
			if(DEBUG)
				System.out.println(this.myResult.getString("barry") + " AAAAAAAAAA");
			this.myState.executeUpdate("INSERT INTO " + user_name + "_pets VALUES()"); // Insert default values
		}
		this.myResult.close();
	}
	
	/* 
	 * Get the current state of a drop table
	 */
	public void getTable(String table_name) throws SQLException{
		String select_all = "SELECT * FROM " + table_name;
		this.myResult = this.myState.executeQuery(select_all); 
	}
	
	/*
	 * Insert a row into the main drop table
	 */
	public void insertRow(String table_name) throws SQLException{
		String insertion_query = "INSERT INTO " + table_name + " VALUES("
				+ Drops.kill_number + ", "
				+ Drops.arrow_pheromone_drop + ", "
				+ "'" + Drops.charms_drop + "', "
				+ "'" + Drops.food_potions_drop + "', "
				+ "'" + Drops.main_loot_drop + "', "
				+ "'" + Drops.unique_drops_drop + "')";
		this.myState.executeUpdate(insertion_query);
		
		// Check the table for pets
		// This can only happen once per logging - multi-pet drops are impossible
		if(DEBUG)
			System.out.println(Drops.pet_drop);
		
		for(int x = 1; x <= 3; x++){
			if(Drops.pet_drop.equals(Drops.pets[x]))
				this.updatePetsTable(table_name);
		}
	}
	
	/*
	 * Get the current number of rows in the main table
	 */
	public int getLength(String table_name) throws SQLException{
		int length = 0;
		String selection_query = "SELECT kill_number FROM " + table_name;
		this.myResult = this.myState.executeQuery(selection_query);
		while(this.myResult.next()){
			length++;
		}
		this.myResult.close();
		return length;
	}
	
	/*
	 * Update the pets table since a new pet was dropped
	 */
	public void updatePetsTable(String table_name) throws SQLException{
		// Get the current state of the table
		this.getTable(table_name + "_pets");
		
		// Check if the user has gotten any pets previously
		this.myResult.next();
		String[] hold_pets = new String[3];
		hold_pets[0] = this.myResult.getString("araxyte_pet");
		hold_pets[1] = this.myResult.getString("barry");
		hold_pets[2] = this.myResult.getString("mallory");
		this.myResult.close();
		
		if(DEBUG){
			System.out.println(hold_pets[1]);
		}
		
		// Delete the old row
		this.myState.executeUpdate("DELETE FROM " + table_name + "_pets LIMIT 1");
		
		// Check previous pets and the pet that was just received
		if(hold_pets[0].equals("0")){
			if(Drops.pet_drop.equals(Drops.pets[1]))
					hold_pets[0] = "1"; // An Araxyte pet was received
		}
		if(hold_pets[1].equals("0")){
			if(Drops.pet_drop.equals(Drops.pets[2]))
					hold_pets[1] = "1"; // Barry was received
		}
		if(hold_pets[2].equals("0")){
			if(Drops.pet_drop.equals(Drops.pets[3]))
					hold_pets[2] = "1"; // Mallory pet was received
		}
		
		// Form the new row
		String update_pets = "INSERT INTO " + table_name + "_pets VALUES(" + 
					hold_pets[0] + ", " + hold_pets[1] + ", " + hold_pets[2] + ")";
		this.myState.executeUpdate(update_pets);
	}
}
