package me.swoldemi.araxxorDropLogger;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener{
	private static final long serialVersionUID = -3628098483298498297L;
	private String user_name;
	private ImageIcon splash_image;
	private ImageIcon window_icon;
	private JLabel image_label;
	private JLabel prompt;
	private JTextField entry;
	private JButton submit;
	public volatile boolean waiting; // Important that this boolean is volatile
	
	private boolean DEBUG = false;
	
	public Login(){
		// Set up the splash image, text entry prompt, text field and submission button
		this.setTitle("Araxxor Drop Logger | Login");
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		this.splash_image = new ImageIcon(getClass().getResource("splash-high-res-small.png"));
		this.image_label = new JLabel(splash_image);
		c.gridx = 1;
		c.gridy = 0;
		this.add(this.image_label , c);
		
		this.prompt = new JLabel("Please enter your username:");
		c.gridy = 1;
		this.add(this.prompt, c);
		
		this.entry = new JTextField(1);
		c.gridy = 2;
		c.ipadx = 100;
		this.add(this.entry, c);
		
		this.submit = new JButton("Login");
		c.gridy = 3;
		c.ipadx = 0; // Reset padding
		this.add(this.submit, c);
		this.submit.addActionListener(this);
		
		// Define parameters for the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(480, 360);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.window_icon = new ImageIcon(getClass().getResource("Varrock_census_records_detail.png"));
		this.setIconImage(this.window_icon.getImage());
	}
	@Override
	synchronized public void actionPerformed(ActionEvent ae) {
		String request = ae.getSource().toString().split(",")[25].split("=")[1];
		
		if(DEBUG){
			System.out.println(ae.getSource().toString());
			System.out.println(request);
		}
		
		if(request.equals("Login")){
			if(this.entry.getText().equals(""))
				this.prompt.setText("Please actually type your username:");
			else{
				this.setVisible(false);
				this.dispose();
				this.setUsername();
				this.waiting = false;
			}
		}
	}
	 public boolean listen(){
		// Waiting for a username to be input
		while(this.waiting){
			
		}
		return false;
	}
	
	public void setUsername(){
		this.user_name =  this.entry.getText();
	}
	
	public String getUsername(){
		return this.user_name;
	}
	
	public String sanitizeUsername(String user_name){
		if(user_name.contains(" ")){
			user_name.replace(" ", "_"); // replace spaces with underscores
		}
		return user_name;
	}
}