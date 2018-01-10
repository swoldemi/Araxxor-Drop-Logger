package DropLogger;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LoggerTable extends JPanel{
	
	LoggerTable(Object[][] table_contents){
		// Define the default GridLayout in the parent class
		super(new GridLayout(1,0));
		
		// Define column_names
		String[] column_names = {"Kill #", "Arrows/Pheromone", "Charms", "Food/Potions", "Loot", "Unique Loot"};
		
		// Create the JTable
		final JTable table = new JTable(table_contents, column_names);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		//Create the scroll pane and add the table to it
		JScrollPane scroll_pane = new JScrollPane(table);

		//Add the scroll pane to this panel
    	add(scroll_pane);
	}		
}
