package ca.mcgill.ecse223.block.view;

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
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOGridCell;
import ca.mcgill.ecse223.block.controller.TOUserMode;


public class CreateNewGamePage extends JFrame {

  //UI elements
  private JLabel errorMessage;

  //New Game
  private JButton newGameButton;

  //Existing Game
  private JButton existingGameButton;

  //Log Out
  private JButton logOutButton;

  public CreateNewGamePage() {
    initComponents();
    //refreshData();
  }

  private void initComponents() {

    //elements for error message
    errorMessage = new JLabel();
    errorMessage.setForeground(Color.RED);

    //elements for new Game
    newGameButton = new JButton();
    newGameButton.setText("New Game");

    //elements for Existing Game
    existingGameButton = new JButton();
    existingGameButton.setText("Existing Game");

    //elements for Log out
    logOutButton = new JButton();
    logOutButton.setText("Log Out");

    //global settings
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("Block223");

    //listeners for new game
    newGameButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        newGameButtonActionPerformed(evt);
      }	
    });

    //listeners for existing game
    existingGameButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        existingGameButtonActionPerformed(evt);
      }
    });

    //listeners for log out 
    logOutButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        logOutButtonActionPerformed(evt);
      }
    });

    //layout
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);

    GroupLayout.ParallelGroup hpg = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
    hpg.addComponent(newGameButton,200,200,400);
    hpg.addComponent(existingGameButton,200,200,400);
    hpg.addComponent(logOutButton,200,200,400);
    GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
    hsg.addGap(50);
    hsg.addGroup(hpg);
    hsg.addGap(50);
    layout.setHorizontalGroup(hsg);

    //layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {newGameButton,existingGameButton,logOutButton});

    GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();	
    vsg.addGap(50);
    vsg.addComponent(newGameButton);
    vsg.addGap(50);
    vsg.addComponent(existingGameButton);
    vsg.addGap(50);
    vsg.addComponent(logOutButton);
    vsg.addGap(50);
    GroupLayout.ParallelGroup vpg = layout.createParallelGroup();
    vpg.addGroup(vsg);
    layout.setVerticalGroup(vpg);

    pack();
  }

  //private void refreshData() {

  //}

  private void newGameButtonActionPerformed(java.awt.event.ActionEvent evt) {
    new CreateGamePage().setVisible(true);

  }

  private void existingGameButtonActionPerformed(java.awt.event.ActionEvent evt) {
    new Block223SelectGame().setVisible(true);

  }
  private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {
    Block223Controller.logout();
    dispose();
    new Block223Login().setVisible(true);
  }



}
