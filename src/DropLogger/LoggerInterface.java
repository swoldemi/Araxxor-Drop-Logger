package DropLogger;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoggerInterface implements  ActionListener {
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
				this.setDrops();
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
	
	public void setDrops(){
		Drops[] drops = new Drops[6]; // create an array of drops
		
		// Create a JPanel
		String[] user_drops = new String[6];
		
		JComboBox<String> main_loot = new JComboBox<String>(Drops.main_loot);
		main_loot.addActionListener(this);
		
		JPanel logger_panel = new JPanel();
		logger_panel.setLayout(new GridLayout(1,1));
		logger_panel.add(new JLabel("Select main loot:"));
		logger_panel.add(main_loot);
		int logger_panel_selection = JOptionPane.showConfirmDialog(null, logger_panel, 
		           "Runescape Araxxor Drop Logger | Log", JOptionPane.OK_CANCEL_OPTION);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String hold_main_loot = ae.getSource().toString();
		System.out.println(hold_main_loot);
		
	}
	
}
