package DropLogger;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class LoggerTable extends JPanel  {
	private static final long serialVersionUID = -4482263346660694338L;
	
	LoggerTable(Object[][] table_contents){	
		// Parent grid construction
		super(new GridLayout(1,0));
		
		// Define column_names
		String[] column_names = {"Kill #", "Arrows", "Charms", "Food/Potions", "Loot", "Unique Loot"};
		
		// Create the JTable
		JTable table = new JTable(table_contents, column_names);
		table.setPreferredScrollableViewportSize(new Dimension(700, 70));
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true); // This is really cool
		
		//Create the scroll pane and add the table to it
		JScrollPane scroll_pane = new JScrollPane(table);

		//Add the scroll pane to this panel
    	this.add(scroll_pane);
    	
    	// Resize each column
    	TableColumn column = null;
    	// Size each column
        for(int x = 0; x < 6; x++){
        	column = table.getColumnModel().getColumn(x);
        	if(x == 0 || x == 1)
        		column.setPreferredWidth(30);
        	if(x >= 3)
        		column.setPreferredWidth(150);
        }
	}		
}
