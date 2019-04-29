package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Player;

import javax.swing.JCheckBox;

public class Block223SignUp extends JFrame {


	private JTextField username;
	private JTextField userPassword;
	private JButton signUp;
	private JLabel logo;
	private JLabel error;
	private String errorMessage = "";
	private JTextField adminPassword;
	private JLabel lblAdminPassword;
	
	public Block223SignUp() {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Exception e) {}
		initComponents();
		refreshData();
	}
	
	private void initComponents() {
		
		
		setTitle("Block223");
		setResizable(false);
		setSize(639,652);
		getContentPane().setLayout(null);
		
		username = new JTextField();
		username.setBackground(new Color(255, 255, 255));
		username.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		username.setForeground(Color.BLACK);
		username.setBounds(160, 251, 382, 60);
		getContentPane().add(username);
		username.setColumns(1);
		
		signUp = new JButton("SIGN UP");
		signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signUpActionPerformed();
			}
		});
		signUp.setBounds(328, 535, 192, 62);
		getContentPane().add(signUp);
		
		userPassword = new JTextField();
		userPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		userPassword.setForeground(Color.BLACK);
		userPassword.setBounds(160, 348, 382, 60);
		getContentPane().add(userPassword);
		userPassword.setColumns(10);
		
		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setBounds(49, 230, 453, 16);
		getContentPane().add(error);
		
		logo = new JLabel("");
//		logo.setHorizontalAlignment(SwingConstants.CENTER);
//		logo.setVerticalAlignment(SwingConstants.BOTTOM);
		logo.setIcon(new ImageIcon(new ImageIcon(Block223Login.class.getResource("/ca/mcgill/ecse223/block/view/logo.png")).getImage().getScaledInstance(493, 168, Image.SCALE_DEFAULT)));
		logo.setBounds(49, 60, 493, 168);
		getContentPane().add(logo);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		lblUsername.setBounds(49, 262, 99, 36);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		lblPassword.setBounds(49, 360, 99, 36);
		getContentPane().add(lblPassword);
		
		adminPassword = new JTextField();
		adminPassword.setForeground(Color.BLACK);
		adminPassword.setFont(new Font("Dialog", Font.PLAIN, 19));
		adminPassword.setColumns(10);
		adminPassword.setBounds(286, 439, 256, 60);
		getContentPane().add(adminPassword);
		
		lblAdminPassword = new JLabel("Admin Password:");
		lblAdminPassword.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblAdminPassword.setBounds(49, 458, 206, 36);
		getContentPane().add(lblAdminPassword);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				backActionPerformed();
			}
		});
		btnBack.setBounds(82, 535, 192, 62);
		getContentPane().add(btnBack);
		
		

	}
	private void refreshData() {
		error.setText(errorMessage);
	}
	
	private void backActionPerformed()  {
		this.dispose();

		Block223Login page = new Block223Login();
		page.setVisible(true);
	}
	
	private void signUpActionPerformed()  {
		
		errorMessage = "";
		
		try {
			Block223Controller.register(username.getText(),userPassword.getText(),adminPassword.getText());
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		refreshData();
		if(errorMessage.length() == 0) {
			this.dispose();
			Block223Login page = new Block223Login();
			page.setVisible(true);
		}
		
		
	}
}
