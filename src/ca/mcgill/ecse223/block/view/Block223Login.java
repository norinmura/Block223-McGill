package ca.mcgill.ecse223.block.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Block223Login extends JFrame {
	
	

	private JTextField username;
	private JTextField password;
	private JButton signUp;
	private JButton login;
	private JLabel logo;
	private JLabel error;
	private String errorMessage = "";
	
	public Block223Login() {
		initComponents();
		refreshData();
	}
	
	private void initComponents() {
		
		
		setTitle("Block223");
		setResizable(false);
		setSize(600,600);
		getContentPane().setLayout(null);
		
		login = new JButton("LOGIN");
		login.setBackground(Color.ORANGE);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed();
			}
		});
		login.setBounds(71, 459, 185, 62);
		getContentPane().add(login);
		
		username = new JTextField();
		username.setBackground(new Color(255, 255, 255));
		username.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		username.setForeground(Color.BLACK);
		username.setBounds(160, 276, 382, 60);
		getContentPane().add(username);
		username.setColumns(1);
		
		signUp = new JButton("SIGN UP");
		signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signUpActionPerformed();
			}
		});
		signUp.setBounds(323, 459, 185, 62);
		getContentPane().add(signUp);
		
		password = new JTextField();
		password.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		password.setForeground(Color.BLACK);
		password.setBounds(160, 348, 382, 60);
		getContentPane().add(password);
		password.setColumns(10);
		
		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setBounds(49, 248, 493, 16);
		getContentPane().add(error);
		
		logo = new JLabel("");
//		logo.setHorizontalAlignment(SwingConstants.CENTER);
//		logo.setVerticalAlignment(SwingConstants.BOTTOM);
		logo.setIcon(new ImageIcon(new ImageIcon(Block223Login.class.getResource("/ca/mcgill/ecse223/block/view/logo.png")).getImage().getScaledInstance(493, 168, Image.SCALE_DEFAULT)));
		logo.setBounds(49, 60, 493, 168);
		getContentPane().add(logo);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		lblUsername.setBounds(49, 287, 99, 36);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		lblPassword.setBounds(49, 360, 99, 36);
		getContentPane().add(lblPassword);
		
		

	}
	private void refreshData() {
		error.setText(errorMessage);
	}
	
	private void signUpActionPerformed() {
		this.dispose();
		Block223SignUp signUpPage = new Block223SignUp();
		signUpPage.setVisible(true);
	}
	
	private void loginActionPerformed() {
		errorMessage = "";
		try {
			Block223Controller.login(username.getText(),password.getText());
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
		
		if(errorMessage.length() == 0) {
			this.dispose();
			if (Block223Controller.isCurrentUserAdmin()) {
			  CreateNewGamePage page = new CreateNewGamePage();
              page.setVisible(true);
			} else {
			  Block223SelectGamePlayer page = new Block223SelectGamePlayer();
              page.setVisible(true);
			}
	         
		}
		refreshData();
	}
}
