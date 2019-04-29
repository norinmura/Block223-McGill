package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;

public class Block223AddBlockToLevel extends JFrame {


  private JPanel currentBlock;
  private JLabel blockIdLabel;
  private JLabel pointLabel;
  private JLabel rLabel;
  private JLabel gLabel;
  private JLabel bLabel;

  private JLabel blockIdValueLabel;
  private JLabel pointValueLabel;
  private JLabel rValueLabel;
  private JLabel gValueLabel;
  private JLabel bValueLabel;

  private JButton addToLevelButton;
  private JButton cancelButton;

  private JLabel blockDisplayLabel;
  private Block223BlockDisplayPanelInLevel blockDisplayPanel;
  private JScrollPane scrollPane;

  private String errorMessage;
  private JLabel errorLabel;

  private HashMap<Integer, Integer> blocks;
  private JTextField xTextField;
  private JTextField yTextField;
  private JLabel yLabel;
  private JLabel xLabel;
  
  private JComboBox<String> levelComboBox;
  private JLabel levelLabel;
  
  private JButton btnSaveGame;
  private Dimension displayPanelDimension;


  public Block223AddBlockToLevel() {
    setResizable(false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle("Position a Block in a Level");
    initComponents();
    refreshData();
  }

  private void initComponents() {

    this.setPreferredSize(new Dimension(600, 600));
    this.setVisible(true);
    setBounds(200, 200, 600,600);

    currentBlock = new JPanel();
    currentBlock.setBounds(66, 68, 98, 98);
    currentBlock.setMinimumSize(new Dimension(100, 100));

    blockIdLabel = new JLabel("Block");
    blockIdLabel.setBounds(34, 201, 37, 20);
    blockIdValueLabel = new JLabel();
    blockIdValueLabel.setBounds(95, 201, 140, 20);

    pointValueLabel = new JLabel();
    pointValueLabel.setBounds(95, 230, 140, 20);
    rValueLabel = new JLabel();
    rValueLabel.setBounds(95, 259, 140, 20);
    gValueLabel = new JLabel();
    gValueLabel.setBounds(95, 288, 140, 20);
    bValueLabel = new JLabel();
    bValueLabel.setBounds(95, 317, 140, 20);
    pointLabel = new JLabel("Points");
    pointLabel.setBounds(34, 230, 43, 20);
    rLabel = new JLabel("R");
    rLabel.setBounds(45, 259, 10, 20);
    gLabel = new JLabel("G");
    gLabel.setBounds(45, 288, 11, 20);
    bLabel = new JLabel("B");
    bLabel.setBounds(47, 317, 9, 20);

    addToLevelButton = new JButton("add to level");
    addToLevelButton.setBounds(15, 488, 115, 29);
    cancelButton = new JButton("cancel");
    cancelButton.setBounds(141, 488, 75, 29);

    blockDisplayLabel = new JLabel("Please select a block:");
    blockDisplayLabel.setBounds(250, 61, 149, 20);
    
    try {
      ArrayList<TOBlock> blocksFromController = (ArrayList<TOBlock>) Block223Controller.getBlocksOfCurrentDesignableGame();
      displayPanelDimension = new Dimension(250,(blocksFromController.size()/3+1)* ((250)/3));
      blockDisplayPanel = new Block223BlockDisplayPanelInLevel(blocksFromController, displayPanelDimension, pointValueLabel, rValueLabel, gValueLabel, bValueLabel, currentBlock, blockIdValueLabel);

      ((JPanel)blockDisplayPanel).setPreferredSize(displayPanelDimension);
    } catch (InvalidInputException e) {
      
      e.printStackTrace();
    }
    scrollPane = new JScrollPane(blockDisplayPanel);
    scrollPane.setBounds(250, 99, 329, 432);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
    

    errorMessage = new String();
    errorLabel = new JLabel(errorMessage);
    errorLabel.setBounds(15, 35, 473, 32);
    errorLabel.setForeground(Color.red);

    btnSaveGame = new JButton("Save Game");
    btnSaveGame.setBounds(468, 16, 111, 29);


    // add listener
    addToLevelButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addToLevelButtonClicked();
      }
    });

    cancelButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cancelButtonClicked();
      }
    });
    
    btnSaveGame.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        saveGameButtonClicked();
      }
    });
    
  

    JSeparator verticalSeparator = new JSeparator(SwingConstants.VERTICAL);
    verticalSeparator.setBounds(175, 0, 2, 0);
    
    xTextField = new JTextField();
    xTextField.setBounds(95, 346, 113, 26);
    xTextField.setColumns(10);
    
    yTextField = new JTextField();
    yTextField.setBounds(95, 381, 113, 26);
    yTextField.setColumns(10);
    
    xLabel = new JLabel("X");
    xLabel.setBounds(46, 349, 9, 20);
    
    yLabel = new JLabel("Y");
    yLabel.setBounds(46, 384, 10, 20);
    
    levelComboBox = new JComboBox<String>();
    levelComboBox.setBounds(95, 420, 113, 26);
    
    levelLabel = new JLabel("Level");
    levelLabel.setBounds(34, 420, 36, 20);
    getContentPane().setLayout(null);
    getContentPane().add(bLabel);
    getContentPane().add(rLabel);
    getContentPane().add(gLabel);
    getContentPane().add(yLabel);
    getContentPane().add(xLabel);
    getContentPane().add(blockIdLabel);
    getContentPane().add(pointLabel);
    getContentPane().add(bValueLabel);
    getContentPane().add(gValueLabel);
    getContentPane().add(rValueLabel);
    getContentPane().add(pointValueLabel);
    getContentPane().add(blockIdValueLabel);
    getContentPane().add(yTextField);
    getContentPane().add(xTextField);
    getContentPane().add(levelComboBox);
    getContentPane().add(verticalSeparator);
    getContentPane().add(currentBlock);
    getContentPane().add(blockDisplayLabel);
    getContentPane().add(btnSaveGame);
    getContentPane().add(scrollPane);
    getContentPane().add(addToLevelButton);
    getContentPane().add(levelLabel);
    getContentPane().add(cancelButton);
    getContentPane().add(errorLabel);


    pack();

  }

  private void refreshData()  {
    errorLabel.setText(errorMessage);
    if (errorMessage == null || errorMessage.length() == 0) {


      
      blockDisplayPanel.removeAll();

      blocks = new HashMap<Integer, Integer>();
      levelComboBox.removeAllItems();
      Integer index = 0;


      ArrayList<TOBlock> blockFromController = new ArrayList<TOBlock>();
      try {
        blockFromController = (ArrayList<TOBlock>) Block223Controller.getBlocksOfCurrentDesignableGame();
      } catch (InvalidInputException e2) {
        // TODO Auto-generated catch block
        errorMessage = e2.toString();
      }
    
      
      for (TOBlock block : blockFromController) {
        blocks.put(block.getId(), index);
        index++;
        
      }
      
      try {
        for(int i=0; i<Block223Controller.getCurrentDesignableGame().getNrLevels(); i++) {
          levelComboBox.addItem(""+(i +1));
          
        }
      } catch (InvalidInputException e) {
        errorMessage = e.getMessage();
      }
      
      pointValueLabel.setText("");
      rValueLabel.setText("");
      gValueLabel.setText("");
      bValueLabel.setText("");
      blockIdValueLabel.setText("");
      xTextField.setText("");
      yTextField.setText("");
      currentBlock.setBackground(new Color(240,240,240));
      currentBlock.setBorder(BorderFactory.createLineBorder(Color.GRAY) );
      levelComboBox.setSelectedIndex(-1);

    }
    
  }

  private void cancelButtonClicked() {
    this.dispose();
    new Block223BlockInGame().setVisible(true);
  }
  
  private void addToLevelButtonClicked() {
    errorMessage = null;
    int level = Integer.parseInt(levelComboBox.getSelectedItem().toString());
    int id = Integer.parseInt(blockIdValueLabel.getText());
    int x = Integer.parseInt(xTextField.getText());
    int y = Integer.parseInt(yTextField.getText());
    
    
    try {
      Block223Controller.positionBlock(id, level, x, y);
    } catch (InvalidInputException e) {
      // TODO Auto-generated catch block
      errorMessage = e.getMessage();
    }
    refreshData();
  }
  
  private void saveGameButtonClicked() {
    errorMessage = null;
    
    try {
      Block223Controller.saveGame();
    } catch (InvalidInputException e) {
      // TODO Auto-generated catch block
      errorMessage = e.getMessage();
    }
  }
}

