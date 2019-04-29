package ca.mcgill.ecse223.block.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.GroupLayout;
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

public class Block223GameList extends JFrame {
	private JTable overviewTable;
	private JScrollPane overviewScrollPane;
	private JButton deleteButton;
	private JButton modifyButton;
	private JButton createButton;
	
	public Block223GameList() {
		initComponents();
	}
	public void initComponents() {
		deleteButton = new JButton();
		deleteButton.setText("Delete");
		deleteButton.setBounds(500, 100, 50, 40);
		modifyButton = new JButton();
		modifyButton.setText("Modify");
		createButton = new JButton();
		createButton.setText("New Game");
		this.setVisible(true);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setLocation(400, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Modify or Delete Games");
		Object columnName[]= {"Game List"};
		Object gameList[][]= {new Object[] {"game1"},new Object[] {"game2"}};
		overviewTable = new JTable(gameList,columnName);

		overviewScrollPane = new JScrollPane(overviewTable);
		//overviewScrollPane.add(deleteButton);
		//overviewScrollPane.add(modifyButton);
		//overviewScrollPane.add(createButton);
//		this.add(overviewScrollPane);
//		Dimension d = overviewTable.getPreferredSize();
//		overviewScrollPane.setPreferredSize(new Dimension(d.width, 200));
//		overviewScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		JSeparator horizontalLine = new JSeparator();
//		GroupLayout layout = new GroupLayout(getContentPane());
//		this.getContentPane().setLayout(layout);
//		layout.setAutoCreateGaps(true);
//		layout.setAutoCreateContainerGaps(true);
//		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
//		//hGroup.addComponent(overviewScrollPane).addComponent(horizontalLine).addComponent(deleteButton).addComponent(modifyButton).addComponent(createButton);
//		hGroup.addGroup(layout.createParallelGroup().addComponent(overviewScrollPane));
//		layout.setHorizontalGroup(hGroup);
//		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
//		//vGroup.addComponent(horizontalLine).addGroup(layout.createParallelGroup().addComponent(deleteButton).addComponent(modifyButton).addComponent(createButton)).addComponent(overviewScrollPane);
//		//vGroup.addComponent(overviewScrollPane);
//		vGroup.addGroup(layout.createParallelGroup().addComponent(overviewScrollPane));
//		layout.setHorizontalGroup(vGroup);
		this.add(overviewScrollPane, BorderLayout.CENTER);
		JPanel jp=new JPanel();
		jp.add(deleteButton);
		jp.add(modifyButton);
		jp.add(createButton);
//		this.add(deleteButton, BorderLayout.SOUTH);
//		this.add(modifyButton, BorderLayout.SOUTH);
		this.add(jp, BorderLayout.SOUTH);
		
	}
}
