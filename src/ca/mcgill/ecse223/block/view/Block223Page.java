package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


public class Block223Page extends JFrame{
	private JLabel errorMessage;
	private JTextField redTextField;
	private JLabel redLabel;
	private JTextField greenTextField;
	private JLabel greenLabel;
	private JTextField blueTextField;
	private JLabel blueLabel;
	private JButton saveButton;
	private JButton cancelButton;
	private JButton nextButton;
	
	public Block223Page() {
		initComponents();
		
	}
	
	private void initComponents() {
//		errorMessage = new JLabel();
//		errorMessage.setForeground(Color.RED);
		
		saveButton = new JButton();
		saveButton.setText("Save");
		cancelButton = new JButton();
		cancelButton.setText("Cancel");
		nextButton = new JButton();
		nextButton.setText("Next");
		
		redTextField = new JTextField();
		redLabel = new JLabel();
		redLabel.setText("Red:");
		
		greenTextField = new JTextField();
		greenLabel = new JLabel();
		greenLabel.setText("Green:");
		
		blueTextField = new JTextField();
		blueLabel = new JLabel();
		blueLabel.setText("Blue:");
		
//		saveButton.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				saveButtonActionPerformed(evt);
//			}
//		});
//
//		// listeners for bus
//		nextButton.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				nextButtonActionPerformed(evt);
//			}
//		});
//		cancelButton.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				cancelButtonActionPerformed(evt);
//			}
//		});
		this.setVisible(true);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setLocation(400, 200);
		GroupLayout layout = new GroupLayout(getContentPane());
		this.getContentPane().setLayout(layout);
		//layout.setAutoCreateGaps(true);
		//layout.setAutoCreateContainerGaps(true);
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(redLabel).addComponent(greenLabel).addComponent(blueLabel));
		hGroup.addGroup(layout.createParallelGroup().addComponent(redTextField).addComponent(greenTextField).addComponent(blueTextField));
		hGroup.addGroup(layout.createParallelGroup().addComponent(saveButton));
		hGroup.addGroup(layout.createParallelGroup().addComponent(cancelButton));
		hGroup.addGroup(layout.createParallelGroup().addComponent(nextButton));
		layout.setHorizontalGroup(hGroup);
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup().addComponent(redLabel).addComponent(redTextField));
		vGroup.addGroup(layout.createParallelGroup().addComponent(greenLabel).addComponent(greenTextField));
		vGroup.addGroup(layout.createParallelGroup().addComponent(blueLabel).addComponent(blueTextField));
		vGroup.addGroup(layout.createParallelGroup().addComponent(saveButton).addComponent(cancelButton).addComponent(nextButton));
		layout.setVerticalGroup(vGroup);
		
		
		
	}

	private void saveButtonActionPerformed(ActionEvent evt) {
		
		
		
	}

	private void nextButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}

	private void cancelButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	private void refreshData() {
		
	}
	
}
