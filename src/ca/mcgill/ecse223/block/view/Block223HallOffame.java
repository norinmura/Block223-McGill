package ca.mcgill.ecse223.block.view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOGridCell;
import ca.mcgill.ecse223.block.controller.TOHallOfFame;
import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Level;
import ca.mcgill.ecse223.block.model.Player;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javax.swing.JList;


public class Block223HallOffame extends JFrame{

	// list
	JList list;

	//button
	private JButton previousButton;
	private JButton nextButton;

	//error
	private String errorMessage;
	private int page = 0;
	
	String[] HOFList;


	public Block223HallOffame() {
		initComponents();
		initHOF();
		refreshData();	
	}

	public void initComponents() {

		//previous button
		previousButton = new JButton();
		previousButton.setBounds(131, 482, 107, 40);
		previousButton.setText("Previous");

		//next button
		nextButton = new JButton();
		nextButton.setBounds(352, 482, 107, 40);
		nextButton.setText("Next");

		//error message
		errorMessage = new String();

		this.setVisible(true);
		this.setSize(602, 561);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Hall Of Fame");
		getContentPane().setLayout(null);
		getContentPane().add(nextButton);
		getContentPane().add(previousButton);


		JLabel lblNewLabel = new JLabel(errorMessage);
		lblNewLabel.setBounds(41, 10, 263, 16);
		getContentPane().add(lblNewLabel);


		list = new JList();
		list.setBounds(82, 29, 426, 439);
		getContentPane().add(list);

		previousButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				previousButtonActionPerformed(evt);
			}
		});

		nextButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nextButtonActionPerformed(evt);
			}
		});
	}
	
	private void initHOF() {
		String gameName = Block223Controller.getCurrentGameName();
		int start = 0;
		int end = Block223Controller.getHOFLength(gameName);
		HOFList = new String[end];
		for (int i=start; i<end; i++) {
			int rank;
			try {
				rank = Block223Controller.getHallOfFame(gameName,start, end).getEntry(i).getPosition();
				String name =  Block223Controller.getHallOfFame(gameName,start, end).getEntry(i).getPlayername();
				int score =  Block223Controller.getHallOfFame(gameName,start, end).getEntry(i).getScore();
				System.out.println();
				HOFList[i] = name +" "+ score + " pts";
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	public void refreshData() {
	
//		int start = page*10;
//		int end = page*10+9;
//		int maxSize = 0;
//		maxSize = Block223Controller.getHOFLength(gameName);
//		System.out.println(start + ","+ end + "," + maxSize);
//
//		if (!(maxSize == 0)) {
//			if (maxSize < 10) {
//				end = maxSize;
//			}
//			System.out.println(start + ","+ end + "," + maxSize);
//
//			String[] HOFList = new String[10];
//
//			for (int i=start; i<end; i++) {
//				int rank;
//				try {
//					rank = Block223Controller.getHallOfFame(gameName, start, end).getEntry(i).getPosition();
//					String name =  Block223Controller.getHallOfFame(gameName, start, end).getEntry(i).getPlayername();
//					int score =  Block223Controller.getHallOfFame(gameName, start, end).getEntry(i).getScore();
//					System.out.println();
//					HOFList[i] = name +": "+ score + " pts";
//				} catch (InvalidInputException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
		int max = HOFList.length;

		if (page > (max/10)) {
			page = (max / 10);
		}
		else {
			if (page < 0) {
				page = 0;
			}
			int start = page*10;
			int end = page*10+10;

			if (!(max == 0)) {
				if (end > max) {
					end = max;
				}
				if (start > max) {
					start = max - 10;
				}
				System.out.println(start + ","+ end + "," + max);

				String[] _HOFList = new String[10];

				for (int i=start; i<end; i++) {
					_HOFList[i-start] = HOFList[i];
				}

				list.setListData(_HOFList);
			}

		}





	}

	private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {
		page -= 1;
		refreshData();
	}

	private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {
		page += 1;
		refreshData();
	}
}
