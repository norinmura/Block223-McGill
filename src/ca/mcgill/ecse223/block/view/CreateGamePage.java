package ca.mcgill.ecse223.block.view;

import java.awt.Color;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import ca.mcgill.ecse223.block.controller.*;
import ca.mcgill.ecse223.block.view.*;

public class CreateGamePage extends JFrame{
	
	// UI elements
	private JLabel errorMessage;	
	private JLabel gameNameLabel;
	private JTextField gameNameTextField;
	private JButton createButton;
	
	// Data elements
	private String error = null;
	
	public CreateGamePage() {
		initComponents();
	}
	
	private void initComponents() {
		this.setVisible(true);
		this.setLocation(600, 300);
		
		Font newTitleFont = new Font("Label.font",Font.PLAIN,18);
		
		// Elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		// Elements for game name
		gameNameLabel = new JLabel();
		gameNameLabel.setText("New Game Name:");
		gameNameLabel.setFont(newTitleFont);
		gameNameTextField = new JTextField();
		createButton = new JButton();
		createButton.setText("Create");
		
		setTitle("CREATE A NEW GAME");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// listeners for game name
		createButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createButtonActionPerformed(evt);
			}
		});
		
		// layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addComponent(gameNameLabel)
				.addComponent(gameNameTextField,100,200,400)
				.addComponent(createButton,100,200,400));
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addComponent(gameNameLabel)
				.addComponent(gameNameTextField)
				.addComponent(createButton));
		
		pack();
	}
	
	private void refreshData() {
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			gameNameTextField.setText("");
		}
		pack();
	}
	
	private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		String name = gameNameTextField.getText();
		
		if (gameNameTextField.getText().trim().isEmpty()) {
			error = "The name of a game must be specified.";
		}
		
		if (error.length() == 0 || error == null) {
			try {
				Block223Controller.createGame(name);
			}
			catch (InvalidInputException e){
				error = e.getMessage();
			}}
		
		refreshData();
		
		if (error.length() == 0 || error == null) {
		new GameSettingPage().setVisible(true);	
		dispose();
		// this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING) );
		}
	}
}
