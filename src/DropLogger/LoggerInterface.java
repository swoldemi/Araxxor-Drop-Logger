package DropLogger;

import java.sql.*;

import javax.swing.JOptionPane;

public class LoggerInterface {
	private String user_name;
	private DatabaseConnector connector;
	
	LoggerInterface(String name, DatabaseConnector connector) throws SQLException{
		this.user_name = name;
		this.connector = connector;
		connector.makeTable(this.user_name);
	}
	//INSERT INTO data_file VALUES(1, 74, "12 Blue Charm", "12 Saradomin brew flask (6)", "220 Onyx bolts", null)
	public void Logger() throws SQLException{
		String last_kill = null;
		String pets = null;
		
		// First, get the current state of the main table
		ResultSet current_state = connector.getTable(this.user_name);
		while(current_state.next()){
			System.out.println(current_state.getString("kill_number")
					+ "|" + current_state.getString("arrows_pheromone")
					+ "|" + current_state.getString("charms")
					+ "|" + current_state.getString("rocktails_sarabrews_overloads")
					+ "|" + current_state.getString("main_loot")
					+ "|" + current_state.getString("unique_drops") 
					);
			last_kill = current_state.getString("kill_number")
					+ "|" + current_state.getString("arrows_pheromone")
					+ "|" + current_state.getString("charms")
					+ "|" + current_state.getString("rocktails_sarabrews_overloads")
					+ "|" + current_state.getString("main_loot")
					+ "|" + current_state.getString("unique_drops");
		}
		
		// Now, get the current state of the pets table
		ResultSet current_pets_state = connector.getTable(this.user_name + "_pets");
		while(current_pets_state.next()){
			System.out.println(current_state.getString("araxyte_pet")
					+ "|" + current_state.getString("barry")
					+ "|" + current_state.getString("mallory"));
			pets = current_state.getString("araxyte_pet")
					+ "|" + current_state.getString("barry")
					+ "|" + current_state.getString("mallory");
		}
		
		boolean exit = false;
		while(!exit){
			String[] buttons = {"1", "2", "3"};
			int task = JOptionPane.showOptionDialog(null, 
					"Welcome " + this.user_name + "!\nWhat would you like to do?\n"
					+ "1: Log kill\n2: View drop log\n3: Exit",
					"Runescape Araxxor Drop Logger | Please Select an Option:", 
					JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[0]) +1;
			
			if(task == 1){
				LoggerInterface.setDrops();
			}
			else if(task == 2){
				//LoggerInterface.getDrops();
				System.out.println("Not implemented");
			}
			else if(task == 3){
				exit = true;
				JOptionPane.showMessageDialog(null, "Exiting RADL. Click 'OK' to close this window.", "Runescape Araxxor Drop Logger | Good Bye!", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	public static void setDrops(){
		System.out.println("Not implemented");
	}
	
}
