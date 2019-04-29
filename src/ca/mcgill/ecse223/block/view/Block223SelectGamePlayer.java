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
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOPlayableGame;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;
import javax.swing.JList;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;

public class Block223SelectGamePlayer extends JFrame{
  private JButton playButton;
  JList list;

  //error
  private String errorMessage;
  private JLabel errorLabel;

  ArrayList<TOPlayableGame> gamesFromController;

  //constructor
  public Block223SelectGamePlayer() {
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowActivated(WindowEvent e) {
        System.out.println("windows regained focus");
        refreshData();
      }
    });

    initComponents();
    refreshData();

  }
  public void initComponents(){
    
    //modifyButton
    playButton = new JButton();
    playButton.setText("Play Game");

    //error message
    errorMessage = new String();
    errorLabel = new JLabel(errorMessage);


    this.setVisible(true);
    this.setSize(600,600);
    this.setVisible(true);
    this.setLocation(400,200);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Game");

    getContentPane().setLayout(null);



    playButton.setBounds(234, 516, 117, 29);
    getContentPane().add(playButton);

    JLabel lblPleaseSelectA = new JLabel("Please select a game:");
    lblPleaseSelectA.setBounds(38, 16, 182, 16);
    getContentPane().add(lblPleaseSelectA);

    list = new JList();
    list.setBounds(54, 61, 495, 436);
    getContentPane().add(list);

    JButton btnLogout = new JButton("Logout");
    btnLogout.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        logOutButtonActionPerformed();
      }
    });
    btnLogout.setBounds(432, 11, 117, 29);
    getContentPane().add(btnLogout);

    //updateButton listener
    playButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        playButtonActionPerformed(evt);
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

    gamesFromController = new ArrayList<TOPlayableGame>();
    try {
      gamesFromController = (ArrayList<TOPlayableGame>) Block223Controller.getPlayableGames();
    }catch(InvalidInputException e) {
      errorMessage = e.getMessage();		
    }

    String[] listArray = new String[gamesFromController.size()];

    for (int i=0; i<gamesFromController.size(); i++) {
      TOPlayableGame game = gamesFromController.get(i);
      String aText;
      if (game.getNumber() == -1 && game.getCurrentLevel() == 0) {
        aText = "Start new game: "+ game.getName();
      } else {
        aText = "Continue: "+ game.getName() + ": at Level " + game.getCurrentLevel();         
      }
      listArray[i] = aText;
    }

    list.setListData(listArray);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.setSelectedIndex(0);
  }
  private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {
    try {
      TOPlayableGame selected = gamesFromController.get(list.getSelectedIndex());
      if (selected.getNumber() == -1 && selected.getCurrentLevel() == 0) {
        Block223Controller.selectPlayableGame(selected.getName(),selected.getNumber());
      } else {
        Block223Controller.selectPlayableGame(null,selected.getNumber());
      }
      
    } catch (InvalidInputException e) {
      // TODO Auto-generated catch block
      errorMessage = e.getMessage();
    }
    new Block223PlayGameUI().setVisible(true);
  }
  
  private void logOutButtonActionPerformed() {
    Block223Controller.logout();
    dispose();
    new Block223Login().setVisible(true);
  }
}
