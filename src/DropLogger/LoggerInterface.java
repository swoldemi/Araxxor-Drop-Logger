package DropLogger;

import java.awt.Dimension;
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
		Drops[] current_kill = new Drops[6]; // create an array of drops
		
		// Begin JPanel Creation
		String[] user_drops = new String[6];
		
		// Add drops categories
		JComboBox<String> charms_choices = new JComboBox<String>(Drops.charms);
		JComboBox<String> food_potions_choices = new JComboBox<String>(Drops.food_potions);
		JComboBox<String> main_loot_choices = new JComboBox<String>(Drops.main_loot);
		JComboBox<String> unique_drops_choices = new JComboBox<String>(Drops.unique_drops);
		charms_choices.addActionListener(this);
		food_potions_choices.addActionListener(this);
		main_loot_choices.addActionListener(this);
		unique_drops_choices.addActionListener(this);
		
		// Add drop category quantity fields - unique drops are dropped alone
		JTextField charms_choices_count = new JTextField(1);
		JTextField food_potions_count = new JTextField(1);
		JTextField main_loot_count = new JTextField(1);
		
		// Get kill number and arrow count - 0 arrows means you got a pheromone
		JTextField kill_number_field = new JTextField(1);
		JTextField arrows_pheromone_field = new JTextField(1);
		
		// Add everything to a JPanel
		JPanel logger_panel = new JPanel();
		logger_panel.setPreferredSize(new Dimension(280, 200));
		logger_panel.setLayout(new GridLayout(9,2));
		logger_panel.add(new JLabel("Kill number:"));
		logger_panel.add(kill_number_field);
		logger_panel.add(new JLabel("Arrows/Pheromone:"));
		logger_panel.add(arrows_pheromone_field);
		logger_panel.add(new JLabel("Charms:"));
		logger_panel.add(charms_choices);
		logger_panel.add(new JLabel("Charms quantity:"));
		logger_panel.add(charms_choices_count);
		logger_panel.add(new JLabel("Food/Potions:"));
		logger_panel.add(food_potions_choices);
		logger_panel.add(new JLabel("Food/Potions quantity:"));
		logger_panel.add(food_potions_count);
		logger_panel.add(new JLabel("Main loot:"));
		logger_panel.add(main_loot_choices);
		logger_panel.add(new JLabel("Main loot quantity:"));
		logger_panel.add(main_loot_count);
		logger_panel.add(new JLabel("Unique drop:"));
		logger_panel.add(unique_drops_choices);
		
		// Show panel
		int logger_panel_selection = JOptionPane.showConfirmDialog(null, logger_panel, 
		           "Runescape Araxxor Drop Logger | Log", JOptionPane.OK_CANCEL_OPTION);
		
		// Get the static text fields (kill number and arrow/pheromone count) and quantities
		if(logger_panel_selection == 0){
			Drops.kill_number = Integer.parseInt(kill_number_field.getText());
			Drops.arrow_pheromone_drop = Integer.parseInt(arrows_pheromone_field.getText());
			Drops.charms_quantity = charms_choices_count.getText();
			Drops.food_potions_quantity = food_potions_count.getText();
			Drops.main_loot_quantity = main_loot_count.getText();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// Get the items and their quantities and store them in instance variables
		String get_selections = ae.getSource().toString().split("selectedItemReminder=")[1].replace("]", "");
		
		// Iterate through each category of items and store it if it falls within a category
		for(String charms: Drops.charms){
			if(get_selections.contains(charms))
				Drops.charms_drop = Drops.charms_quantity + " " + charms;
		}
		
		for(String food_potion: Drops.food_potions){
			if(get_selections.contains(food_potion))
				Drops.food_potions_drop = Drops.food_potions_quantity + " " + food_potion;
		}
		
		for(String main_loot: Drops.main_loot){
			if(get_selections.contains(main_loot))
				Drops.main_loot_drop = Drops.main_loot_quantity + " " + main_loot;
		}
		
		for(String unique_drop: Drops.unique_drops){
			if(get_selections.contains(unique_drop))
				Drops.unique_drops_drop = unique_drop;
		}
	}
	
}
