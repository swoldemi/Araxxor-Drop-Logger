package DropLogger;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Login extends JFrame implements ActionListener{
	private String user_name;
	private ImageIcon splash_image;
	private JLabel image_label;
	private JLabel prompt;
	private JTextField entry;
	private JButton submit;
	public volatile boolean waiting; 
	
	public Login(){
		// Set up the splash image, text entry prompt, text field and submission button
		this.setTitle("Araxxor Drop Logger | Login");
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		this.splash_image = new ImageIcon(getClass().getResource("splash.png"));
		this.image_label = new JLabel(splash_image);
		c.gridx = 1;
		c.gridy = 0;
		this.add(this.image_label , c);
		
		this.prompt = new JLabel("Please enter your username");
		c.gridy = 1;
		this.add(this.prompt, c);
		
		this.entry = new JTextField(1);
		c.gridy = 2;
		this.add(this.entry, c);
		
		this.submit = new JButton("Login");
		c.gridy = 3;
		this.add(this.submit, c);
		this.submit.addActionListener(this);
		
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(500, 500); 
		this.setVisible(true);
		
	}
	@Override
	synchronized public void actionPerformed(ActionEvent ae) {
		String request = ae.getSource().toString().split(",")[25].split("=")[1];
		System.out.println(ae.getSource().toString());
		System.out.println(request);
		if(request.equals("Login")){
			this.setVisible(false);
			this.setUsername();
			this.waiting = false;
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
