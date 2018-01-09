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
		super(new GridLayout(1,0));
		String[] column_names = {"Kill #", "Arrows/Pheromone", "Charms", "Food/Potions", "Loot", "Unique Loot"};
		final JTable table = new JTable(table_contents, column_names);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		//Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

		//Add the scroll pane to this panel.
    	add(scrollPane);
	}		
}
