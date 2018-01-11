package DropLogger;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoggerInterface implements ActionListener{
	private String user_name;
	private DatabaseConnector connector;
	private ImageIcon window_icon;
	public boolean exit;
	public String[] last_kill;
	public String pets;
	public String selection;
	public String title;
	public Dimension window_dimension;
	
	private boolean DEBUG = false;
	
	LoggerInterface(String name, DatabaseConnector connector) throws SQLException{
		this.user_name = name;
		this.connector = connector;
		connector.makeTable(this.user_name);
		
		this.exit = false;
		this.last_kill = null;
		this.pets = null;
		this.selection = null;
		this.title = "Runescape Araxxor Drop Logger ";
		this.window_dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.window_icon = new ImageIcon(getClass().getResource("Varrock_census_records_detail.png"));
	}
	
	public void Logger() throws SQLException{
		// First, get the current state of the main table and get the last row
		connector.getTable(this.user_name);
		connector.myResult.last();
		last_kill = new String[]{connector.myResult.getString("kill_number"),
				connector.myResult.getString("arrows_pheromone"),
				connector.myResult.getString("charms"), connector.myResult.getString("rocktails_sarabrews_overloads"), 
				connector.myResult.getString("main_loot"), connector.myResult.getString("unique_drops")};
		
		if(DEBUG){
			for(String item: last_kill){
				System.out.print(item + " ");
			}
		}
		connector.myResult.close();
		
		// Now, get the current state of the pets table
		connector.getTable(this.user_name + "_pets");
		connector.myResult.last();
		pets = connector.myResult.getString("araxyte_pet")
					+ "|" + connector.myResult.getString("barry")
					+ "|" + connector.myResult.getString("mallory");
		if(DEBUG)
			System.out.println(pets);
		connector.myResult.close();
		
		// Make a JFrame to host the menu
		JFrame main_logger_interface = new JFrame(this.title + "| Please Select an Option");
		main_logger_interface.setPreferredSize(new Dimension(560, 70));
		main_logger_interface.setLayout(new GridLayout(1,3));
		main_logger_interface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_logger_interface.setVisible(true);
		
		// Make a panel for the actual menu
		JPanel logger_panel = new JPanel();
		main_logger_interface.add(logger_panel);
		
		// Put all of the buttons on the panel
		JButton log_drop_button = new JButton("Log Drop");
		logger_panel.add(log_drop_button);
		log_drop_button.addActionListener(this);
		
		JButton view_log_button = new JButton("View Log");
		logger_panel.add(view_log_button);
		view_log_button.addActionListener(this);
		
		JButton exit_button = new JButton("Exit");
		logger_panel.add(exit_button);
		exit_button.addActionListener(this);
		
		JButton view_pets_button = new JButton("View Pets");
		logger_panel.add(view_pets_button);
		exit_button.addActionListener(this);
		
		// Pack the buttons on the panel within the frame and center the frame
		main_logger_interface.pack();
		main_logger_interface.setLocationRelativeTo(null);
		
		// Set the icon image for the window
		main_logger_interface.setIconImage(this.window_icon.getImage());
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// Get the items and their quantities and store them in instance variables
		this.selection = ae.getSource().toString().split(",")[25].split("=")[1];
		
		// Begin interface logic - No need for an event loop!
		if(selection.equals("Log Drop")){
			this.setDrops();
			try {
				connector.insertRow(this.user_name);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(selection.equals("View Log")){
			try {
				this.makeAndShowTable(this.getDrops());
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(selection.equals("Exit")){
			System.exit(0);
		}
	}
	
	public void setDrops(){
		// Add drops categories
		JComboBox<String> charms_choices = new JComboBox<String>(Drops.charms);
		JComboBox<String> food_potions_choices = new JComboBox<String>(Drops.food_potions);
		JComboBox<String> main_loot_choices = new JComboBox<String>(Drops.main_loot);
		JComboBox<String> unique_drops_choices = new JComboBox<String>(Drops.unique_drops);
		JComboBox<String> pets_choices = new JComboBox<String>(Drops.pets);
		
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
		logger_panel.setLayout(new GridLayout(10,2));
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
		logger_panel.add(new JLabel("Pets:"));
		logger_panel.add(pets_choices);
		
		// Show panel
		int logger_panel_selection = JOptionPane.showConfirmDialog(null, logger_panel, 
				this.title + "| Log Drop", JOptionPane.OK_CANCEL_OPTION);
		
		// Get the static text fields (kill number and arrow/pheromone count) and quantities
		if(logger_panel_selection == 0){
			Drops.kill_number = Integer.parseInt(kill_number_field.getText());
			Drops.arrow_pheromone_drop = Integer.parseInt(arrows_pheromone_field.getText());
			Drops.charms_quantity = charms_choices_count.getText();
			Drops.food_potions_quantity = food_potions_count.getText();
			Drops.main_loot_quantity = main_loot_count.getText();
		}
		
		// Define the instance variables that are going to be passed as parameters for the row insertion query
		Drops.charms_drop = Drops.charms_quantity + " " + charms_choices.getSelectedItem().toString();
		Drops.food_potions_drop = Drops.food_potions_quantity + " " + food_potions_choices.getSelectedItem().toString();
		Drops.main_loot_drop = Drops.main_loot_quantity + " " + main_loot_choices.getSelectedItem().toString();
		Drops.unique_drops_drop = unique_drops_choices.getSelectedItem().toString();
		if(Drops.unique_drops_drop.equals("none")){
			Drops.unique_drops_drop = null;
		}
		Drops.pet_drop = pets_choices.getSelectedItem().toString();
	}

	public Object[][] getDrops() throws SQLException{
		// Get the current number of rows in the table
		int table_length = connector.getLength(this.user_name);
		
		// Create an object to hold all of the cell information for each row
		Object[][] cell_information = new Object[table_length][6];
		
		// Get the current log of drops
		connector.getTable(this.user_name); 
		
		// Add each of the rows, using the cursor, to the 2D array to be used for the JFrame
		int row = 0;
		while(connector.myResult.next()){
			Object[] drop_table_row = new Object[]{Integer.parseInt(connector.myResult.getString("kill_number")), Integer.parseInt(connector.myResult.getString("arrows_pheromone")),
					connector.myResult.getString("charms"), connector.myResult.getString("rocktails_sarabrews_overloads"),
					connector.myResult.getString("main_loot"), connector.myResult.getString("unique_drops")};
			for(int x = 0; x < 6; x++){
				cell_information[row][x] = drop_table_row[x];
			}
			row++;
		}
		connector.myResult.close();
		return cell_information;
	}
	
    public void makeAndShowTable(Object[][] cell_information) {
    	 JFrame frame = new JFrame(this.title + "| View Log");
         frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
         LoggerTable table = new LoggerTable(cell_information);
         table.setOpaque(true);
         frame.setContentPane(table);
         frame.pack();
         frame.setIconImage(this.window_icon.getImage());
         frame.setVisible(true);
    }
}