package ca.mcgill.ecse223.block.view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Properties;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;

public class Block223SelectGame extends JFrame{
	private JScrollPane scrollpane;
	private JLabel gameDisplayLabel;
	private JPanel gameDisplayPanel;
	
	//buttons
	private JButton deleteButton;
	private JButton updateButton;
	private JButton createButton;
	
	//error
	private String errorMessage;
	private JLabel errorLabel;
	
	private String selectedGameName;
	
	//constructor
	public Block223SelectGame() {
		initComponents();
		refreshData();
		
	}
	public void initComponents(){
		//select game panel
//		gameDisplayLabel = new JLabel("Please select a game:");
//		gameDisplayPanel = new JPanel();
//		scrollpane = new JScrollPane(gameDisplayPanel);
//		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		//deleteButton
		deleteButton = new JButton();
		deleteButton.setText("Delete");
		deleteButton.setBounds(500, 100, 50, 40);
		
		//modifyButton
		updateButton = new JButton();
		updateButton.setText("Update");
		
		//createButton
		createButton = new JButton();
		createButton.setText("New Game");
		
		//error message
		errorMessage = new String();
		errorLabel = new JLabel(errorMessage);
		
		
		this.setVisible(true);
		this.setSize(600,600);
		this.setVisible(true);
		this.setLocation(400,200);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Game");
		
		
		deleteButton.setBounds(51, 516, 82, 29);

		getContentPane().setLayout(null);
		getContentPane().add(deleteButton);
		
		
		
		updateButton.setBounds(234, 516, 117, 29);
		getContentPane().add(updateButton);
		
		
		createButton.setBounds(430, 516, 117, 29);
		getContentPane().add(createButton);
		
//		gameDisplayPanel = new JPanel();
//		ScrollPane scrollPane = new ScrollPane(gameDisplayPanel);
//		gameDisplayLabel = new JLabel("Please select a game:");
//		gameDisplayLabel.setBounds(0, 0, 200, 20);
		gameDisplayPanel = new JPanel();
		scrollpane = new JScrollPane(gameDisplayPanel);
		scrollpane.setBounds(35, 44, 468, 427);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollpane);
		
		JLabel lblPleaseSelectA = new JLabel("Please select a game:");
		lblPleaseSelectA.setBounds(38, 16, 61, 16);
		getContentPane().add(lblPleaseSelectA);
		
		
		//deleteButton listener
		deleteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteButtonActionPerformed(evt);
			}
		});
		
		//updateButton listener
		updateButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateButtonActionPerformed(evt);
			}
		});
		//createButton listener
		createButton.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			createButtonActionPerformed(evt);
		}
		});
		//hsg.addComponent(deleteButton);
		//hsg.addComponent(updateButton);
		//hsg.addComponent(createButton);
		//hpg.addComponent(errorLabel);
		//hpg.addGap(50);
		//hpg.addComponent(gameDisplayLabel);
		//hpg.addGap(50);
		//hpg.addComponent(scrollpane);
		//hpg.addGap(50);
		//hpg.addGroup(hsg);
		//vpg.addComponent(deleteButton);
		//vpg.addComponent(updateButton);
		//vpg.addComponent(createButton);
		//vsg.addComponent(errorLabel);
		//vsg.addGap(50);
		//vsg.addComponent(gameDisplayLabel);
		//vsg.addGap(50);
		//vsg.addComponent(scrollpane);
		//vsg.addGroup(vpg);
			
	}

	public void refreshData(){
		
		ArrayList<TOGame> gamesFromController = new ArrayList<TOGame>();
		try {
			gamesFromController = (ArrayList<TOGame>) Block223Controller.getDesignableGames();
		}catch(InvalidInputException e) {
			errorMessage = e.getMessage();		
		}
		
		
		
		gameDisplayPanel.removeAll();
		
		GridLayout gridLayout = new GridLayout (gamesFromController.size()/1,1);
		gameDisplayPanel.setLayout(gridLayout);
		
		for (TOGame game: gamesFromController) {
			JPanel jp = new JPanel();
			jp.setPreferredSize(new Dimension(468,5));
			jp.add(new JLabel(""+game.getName()));
			jp.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					jp.setBackground(Color.yellow);
					selectedGameName = ((JLabel)jp.getComponent(0)).getText();
				
				}
			});
			
			gameDisplayPanel.add(jp);
		}
		
	}
	
	private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			Block223Controller.deleteGame(selectedGameName);	
		}catch (InvalidInputException e) {
			errorMessage = e.getMessage();
			errorLabel.setText(errorMessage);
		}
		refreshData();
	}
	private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			Block223Controller.selectGame(selectedGameName);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			errorMessage = e.getMessage();
		}
		new Block223UpdateGameSetting().setVisible(true);	
		dispose();
	}
	private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new CreateGamePage().setVisible(true);	
		dispose();
	}
}
