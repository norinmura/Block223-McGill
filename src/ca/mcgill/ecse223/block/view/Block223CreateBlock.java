package ca.mcgill.ecse223.block.view;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
//import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
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

import ca.mcgill.ecse223.block.*;
import ca.mcgill.ecse223.block.controller.*;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;


public class Block223CreateBlock extends JFrame{
	private JLabel errorMessage;
	private JTextField redTextField;
	private JLabel redLabel;
	private JTextField greenTextField;
	private JLabel greenLabel;
	private JTextField blueTextField;
	private JLabel blueLabel;
	private JTextField pointTextField;
	private JLabel pointLabel;
	private JButton saveButton;
	private JButton cancelButton;
	private JButton nextButton;
	private JButton colorButton;
	private String error = null;
	private JPanel jp;
	private JLabel colorOverview;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JLabel lblColoroverview;
	private JButton btnSaveCurrentSetting;
	public Block223CreateBlock() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initComponents();
		refreshData();
	}
	
	private void initComponents() {
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		errorMessage.setText("");
		saveButton = new JButton();
		saveButton.setText("Save");
		cancelButton = new JButton();
		cancelButton.setText("Cancel");
		nextButton = new JButton();
		nextButton.setText("Preview");
		
		redTextField = new JTextField();
		redLabel = new JLabel();
		redLabel.setText("Red:");
		
		greenTextField = new JTextField();
		greenLabel = new JLabel();
		greenLabel.setText("Green:");
		
		blueTextField = new JTextField();
		blueLabel = new JLabel();
		blueLabel.setText("Blue:");
		
		pointTextField = new JTextField();
		pointLabel = new JLabel();
		pointLabel.setText("Point:");
		
		//colorButton.setPreferredSize(new Dimension(50,50));
		colorButton=new JButton();
		colorButton.setText("Color");
		colorButton.setPreferredSize(new Dimension(50,50));
		colorButton.setBackground(Color.RED);
		//colorButton.setForeground(Color.RED);
		jp = new JPanel();
		jp.setBackground(Color.WHITE);
//		Rectangle2D rect=new Rectangle2D.Double(400,200,50,50);
		JComponent rect=new Block223ColorOverview();
		rect.setBackground(new Color(255,255,255));
		//jp.add(rect);
		//setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Adding a block to game");
		
		saveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveButtonActionPerformed(evt);
			}
		});

		// listeners for bus
		nextButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nextButtonActionPerformed(evt);
			}
		});
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});
		
		this.setVisible(true);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setLocation(400, 200);
        

        redTextField.setColumns(10);

        greenTextField.setColumns(10);
        

        blueTextField.setColumns(10);
        

        pointTextField.setColumns(10);
        

        
        lblColoroverview = new JLabel("Color Overview");
        
        btnSaveCurrentSetting = new JButton("Save Game");
        btnSaveCurrentSetting.addActionListener(new ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent e) {
        		saveSettingActionPerformed(e);
        	}
        });
        
       // JLabel lblNewLabel_4 = new JLabel("New label");
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(41)
        			.addComponent(nextButton, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
        			.addGap(39)
        			.addComponent(saveButton, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
        			.addGap(32)
        			.addComponent(cancelButton, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
        			.addGap(37))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(51)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(redLabel)
        				.addComponent(greenLabel)
        				.addComponent(blueLabel)
        				.addComponent(pointLabel))
        			.addGap(33)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(pointTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(225))
        				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        					.addGroup(groupLayout.createSequentialGroup()
        						.addComponent(redTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addContainerGap(346, Short.MAX_VALUE))
        					.addGroup(groupLayout.createSequentialGroup()
        						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        							.addComponent(blueTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addComponent(greenTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        						.addPreferredGap(ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
        						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        							.addComponent(lblColoroverview, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
        							.addComponent(jp, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
        						.addGap(56)))))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(16)
        			.addComponent(errorMessage, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(263, Short.MAX_VALUE))
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addContainerGap(457, Short.MAX_VALUE)
        			.addComponent(btnSaveCurrentSetting)
        			.addGap(26))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(errorMessage)
        					.addGap(94)
        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(redLabel)
        						.addComponent(redTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addGap(37)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(greenLabel)
        						.addComponent(greenTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addGap(27)
        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(blueLabel)
        						.addComponent(blueTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(19)
        					.addComponent(btnSaveCurrentSetting)
        					.addGap(103)
        					.addComponent(jp, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(lblColoroverview)
        			.addGap(14)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(pointLabel)
        				.addComponent(pointTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(nextButton)
        				.addComponent(cancelButton)
        				.addComponent(saveButton))
        			.addGap(71))
        );
        getContentPane().setLayout(groupLayout);
        
//        textField = new JTextField();
//        textField.setColumns(10);
//        
//        textField_1 = new JTextField();
//        textField_1.setColumns(10);
//        
//        JLabel lblRed = new JLabel("Red:");
//        
//        JLabel lblGreen = new JLabel("Green:");
//        
//        JLabel lblBlue = new JLabel("Blue:");
//        
//        JLabel lblPoints = new JLabel("Points:");
//        
//        JLabel lblNewLabel = new JLabel("New label");
//        
//        JButton btnPreview = new JButton("Preview");
//        
//        JButton btnSave = new JButton("Save");
//        
//        JButton btnCancel = new JButton("Cancel");
//        
//        textField_3 = new JTextField();
//        textField_3.setColumns(10);
//        
//        textField_2 = new JTextField();
//        textField_2.setColumns(10);
//        
//        JPanel panel = new JPanel();
//        panel.setBackground(Color.BLUE);
//        GroupLayout groupLayout = new GroupLayout(getContentPane());
//        groupLayout.setHorizontalGroup(
//        	groupLayout.createParallelGroup(Alignment.LEADING)
//        		.addGroup(groupLayout.createSequentialGroup()
//        			.addGap(0, 0, Short.MAX_VALUE)
//        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
//        				.addGroup(groupLayout.createSequentialGroup()
//        					.addGap(63)
//        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
//        						.addGroup(groupLayout.createSequentialGroup()
//        							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
//        								.addGroup(groupLayout.createSequentialGroup()
//        									.addComponent(btnPreview)
//        									.addPreferredGap(ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
//        									.addComponent(btnSave)
//        									.addGap(54)
//        									.addComponent(btnCancel))
//        								.addGroup(groupLayout.createSequentialGroup()
//        									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
//        										.addGroup(groupLayout.createSequentialGroup()
//        											.addComponent(lblBlue, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
//        											.addPreferredGap(ComponentPlacement.UNRELATED)
//        											.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//        											.addPreferredGap(ComponentPlacement.RELATED))
//        										.addGroup(groupLayout.createSequentialGroup()
//        											.addComponent(lblRed, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
//        											.addPreferredGap(ComponentPlacement.RELATED)
//        											.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//        										.addGroup(groupLayout.createSequentialGroup()
//        											.addComponent(lblGreen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//        											.addPreferredGap(ComponentPlacement.UNRELATED)
//        											.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
//        									.addGap(82)
//        									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
//        									.addGap(9)))
//        							.addPreferredGap(ComponentPlacement.RELATED))
//        						.addGroup(groupLayout.createSequentialGroup()
//        							.addComponent(lblPoints)
//        							.addPreferredGap(ComponentPlacement.UNRELATED)
//        							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//        							.addGap(98)))
//        					.addGap(451))
//        				.addGroup(groupLayout.createSequentialGroup()
//        					.addPreferredGap(ComponentPlacement.RELATED)
//        					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)))
//        			.addContainerGap())
//        );
//        groupLayout.setVerticalGroup(
//        	groupLayout.createParallelGroup(Alignment.LEADING)
//        		.addGroup(groupLayout.createSequentialGroup()
//        			.addContainerGap()
//        			.addComponent(lblNewLabel)
//        			.addGap(66)
//        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
//        				.addGroup(groupLayout.createSequentialGroup()
//        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
//        						.addComponent(lblRed)
//        						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//        					.addGap(31)
//        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
//        						.addComponent(lblGreen)
//        						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//        					.addGap(35)
//        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
//        						.addComponent(lblBlue)
//        						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
//        				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
//        			.addGap(46)
//        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
//        				.addComponent(lblPoints)
//        				.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//        			.addGap(90)
//        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
//        				.addComponent(btnPreview)
//        				.addComponent(btnCancel)
//        				.addComponent(btnSave))
//        			.addGap(55))
//        );
//        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
//        getContentPane().setLayout(groupLayout);
       
        
//        /**
//         * set absolute bounds
//         */
//        //JLabel lblRed = new JLabel("Red:");
//        redLabel.setBounds(53, 91, 61, 16);
//        getContentPane().add(redLabel);
//        
//        //textField = new JTextField();
//        redTextField.setBounds(105, 86, 130, 26);
//        getContentPane().add(redTextField);
//        redTextField.setColumns(10);
//        
//        //JLabel lblGreen = new JLabel("Green:");
//        greenLabel.setBounds(53, 137, 61, 16);
//        getContentPane().add(greenLabel);
//        
//        //textField = new JTextField();
//        greenTextField.setBounds(105, 132, 130, 26);
//        getContentPane().add(greenTextField);
//        greenTextField.setColumns(10);
//        
//        //JLabel lblBlue = new JLabel("blue:");
//        blueLabel.setBounds(53, 183, 61, 16);
//        getContentPane().add(blueLabel);
//        
//        //textField_2 = new JTextField();
//        blueTextField.setBounds(105, 178, 130, 26);
//        getContentPane().add(blueTextField);
//        blueTextField.setColumns(10);
//        
//        //JLabel lblPoints = new JLabel("points:");
//        pointLabel.setBounds(53, 229, 61, 16);
//        getContentPane().add(pointLabel);
//        
//        //point = new JTextField();
//        pointTextField.setBounds(105, 224, 130, 26);
//        getContentPane().add(pointTextField);
//        pointTextField.setColumns(10);
//        
//        //JButton btnPreview = new JButton("Preview");
//        nextButton.setBounds(48, 379, 117, 29);
//        getContentPane().add(nextButton);
//        
//        //JButton btnSave = new JButton("Save");
//        saveButton.setBounds(194, 379, 117, 29);
//        getContentPane().add(saveButton);
//        
//        //JButton btnCancel = new JButton("Cancel");
//        cancelButton.setBounds(336, 379, 117, 29);
//        getContentPane().add(cancelButton);
//        
//        //JPanel panel = new JPanel();
//        jp.setBounds(321, 117, 93, 93);
//        getContentPane().add(jp);
//        
//        colorOverview = new JLabel("Color Overview");
//        colorOverview.setBounds(318, 214, 96, 16);
//        getContentPane().add(colorOverview);
//        
//        //JLabel lblErrormessage = new JLabel("errorMessage");
//        errorMessage.setBounds(6, 6, 1000, 16);
//        getContentPane().add(errorMessage);
        

        
        
        /**
         * layout1 by using layout
         */
//		GroupLayout layout = new GroupLayout(getContentPane());
//		this.getContentPane().setLayout(layout);
//		//layout.setAutoCreateGaps(true);
//		//layout.setAutoCreateContainerGaps(true);
//		GroupLayout.ParallelGroup hGroup = layout.createParallelGroup();
//		GroupLayout.ParallelGroup label = layout.createParallelGroup();
//		GroupLayout.ParallelGroup text = layout.createParallelGroup();
//		label.addComponent(redLabel).addComponent(greenLabel).addComponent(blueLabel).addComponent(pointLabel);
//		text.addComponent(redTextField,50,50,100).addComponent(greenTextField,50,50,100).addComponent(blueTextField,50,50,100).addComponent(pointTextField,50,50,100);
//		GroupLayout.SequentialGroup color = layout.createSequentialGroup();
//		color.addGap(50).addGroup(label).addGroup(text).addGap(200).addComponent(jp).addGap(70);
//		GroupLayout.SequentialGroup button = layout.createSequentialGroup();
//		button.addGap(140).addComponent(saveButton).addComponent(cancelButton).addComponent(nextButton);
//		hGroup.addComponent(errorMessage).addGroup(color).addGroup(button);
//		//hGroup.addComponent(errorMessage);
//		
////		hGroup.addGroup(layout.createParallelGroup().addComponent(redLabel).addComponent(greenLabel).addComponent(blueLabel));
////		hGroup.addGroup(layout.createParallelGroup().addComponent(redTextField).addComponent(greenTextField).addComponent(blueTextField));
////		hGroup.addGroup(layout.createParallelGroup().addComponent(saveButton));
////		hGroup.addGroup(layout.createParallelGroup().addComponent(cancelButton));
////		hGroup.addGroup(layout.createParallelGroup().addComponent(nextButton));
//		layout.setHorizontalGroup(hGroup);
//		//GroupLayout.ParallelGroup vertical = layout.createParallelGroup();
//		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
//		
//		vGroup.addComponent(errorMessage);
//		vGroup.addGap(50);
//		vGroup.addGroup(layout.createParallelGroup().addComponent(redLabel).addComponent(redTextField));
//		vGroup.addGap(50);
//		vGroup.addGroup(layout.createParallelGroup().addComponent(greenLabel).addComponent(greenTextField).addComponent(jp));
//		vGroup.addGap(50);
//		vGroup.addGroup(layout.createParallelGroup().addComponent(blueLabel).addComponent(blueTextField));
//		vGroup.addGap(50);
//		vGroup.addGroup(layout.createParallelGroup().addComponent(pointLabel).addComponent(pointTextField));
//		vGroup.addGap(50);
//		vGroup.addGroup(layout.createParallelGroup().addComponent(saveButton).addComponent(cancelButton).addComponent(nextButton));
//		//vertical.addGroup(vGroup).addComponent(colorButton);
//		layout.setVerticalGroup(vGroup);
        
//		getContentPane().add(errorMessage);
//        getContentPane().add(redLabel);
//        getContentPane().add(redTextField);
//        getContentPane().add(greenLabel);
//        getContentPane().add(greenTextField);
//        getContentPane().add(blueLabel);
//        getContentPane().add(blueTextField, BorderLayout.NORTH);
//        getContentPane().add(nextButton, BorderLayout.EAST);
//        getContentPane().add(saveButton, BorderLayout.WEST);
//        getContentPane().add(cancelButton, BorderLayout.SOUTH);
        
		
//		RectCanvas canvas= new RectCanvas();
//		private RectCanvas canvas;
		
		
		
	}

	private void saveButtonActionPerformed(ActionEvent evt) {
		error="";
		int red=-1;
		int green = -1;
		int blue=-1;
		int points=-1;
		try {
			red = Integer.parseInt(redTextField.getText());
			green = Integer.parseInt(greenTextField.getText());
			blue = Integer.parseInt(blueTextField.getText());
			points = Integer.parseInt(pointTextField.getText());
		}
		catch (NumberFormatException e) {
			error = "Red/green/blue number needs to be a numerical value!";
		}
		if (error.length() == 0) {
			// call the controller
			try {
				Block223Controller.addBlock(red, green, blue, points);
				//Block223Controller.saveGame();
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			
		
		}
		refreshData();
		jp.setBackground(new Color(red,green,blue));
	}

	private void nextButtonActionPerformed(ActionEvent evt){
		// TODO Auto-generated method stub
		error="";
		int red=-1;
		int green = -1;
		int blue=-1;
		int points=-1;
		
		try {
			red = Integer.parseInt(redTextField.getText());
			green = Integer.parseInt(greenTextField.getText());
			blue = Integer.parseInt(blueTextField.getText());
			points = Integer.parseInt(pointTextField.getText());
		}
		catch (NumberFormatException e) {
			error = "Red/green/blue/points number needs to be a numerical value!";
		}
		if(red>255||red<0) {
			error="Red must be between 0 and 255.";
			
		}
		if(green>255||red<0) {
			error+="Green must be between 0 and 255.";
			
		}
		if(blue>255||blue<0) {
			error+="Blue must be between 0 and 255.";
			
		}
		if(points>1000||points<1) {
			error+="Points must be between 1 and 1000. ";
		}
		if(error.length()==0) {
			jp.setBackground(new Color(red,green,blue));
			errorMessage.setText(error);
		}
		else {
			errorMessage.setText(error);
		}
		
	}

	private void cancelButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		//setDefaultCloseOperation(this.DISPOSE_ON_CLOSE)�
		//this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		//this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING) );
		//new Block223GameList().setVisible(true);
	  this.setVisible(false);
	  new Block223BlockInGame().setVisible(true);
	}
	
	private void saveSettingActionPerformed(ActionEvent evt) {
		try {
			Block223Controller.saveGame();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void refreshData() {
		errorMessage.setText(error);
		
		if(error==null||error.length()==0) {
			//jp.setBackground(new Color(Integer.parseInt(redTextField.getText()),Integer.parseInt(greenTextField.getText()), Integer.parseInt(blueTextField.getText())));
			redTextField.setText("");
			greenTextField.setText("");
			blueTextField.setText("");
			pointTextField.setText("");
			
			
		}
		
	}
	
	public Color colorActionPerformed(ActionEvent e){
		Color c = new Color(0,0,0);
		if(e.getSource().getClass().getSimpleName().equals("JButton")){
		int r=Integer.parseInt(redTextField.getText());
		int g=Integer.parseInt(greenTextField.getText());
		int b=Integer.parseInt(blueTextField.getText());
		if(r>=0 && r<=255 && g>=0 && g<=255 && b>=0 && b<=255){
		c=new Color(r,g,b);
		}
		
	}
		return c;
}
}

