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
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Block223BlockInGame extends JFrame {
  private JTextField pointTextField;
  private JTextField rTextField;
  private JTextField gTextField;
  private JTextField bTextField;
  private JScrollPane scrollPane;
  private JPanel currentBlock;
  private JComboBox<String> blockIdSelection;
  private JLabel blockIdLabel;
  private JLabel pointLabel;
  private JLabel rLabel;
  private JLabel gLabel;
  private JLabel bLabel;
  private JLabel blockDisplayLabel;
  private JButton btnSaveGame;
  private JButton saveButton;
  private JButton deleteButton;
  private JButton addButton;
  private JButton lvConfigButton;
  private JLabel errorLabel;
  private Block223BlockDisplayPanel blockDisplayPanel;
  private Dimension displayPanelDimension;
  
  private String errorMessage;
  private HashMap<Integer, Integer> blocks;
  
  public Block223BlockInGame() {
    setBounds(200, 200, 600,600);
    getContentPane().setLayout(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle("Update or Delete a Block in Game");
    initComponents();
    refreshData();

  }
  
  private void initComponents() {
   
    currentBlock = new JPanel();
    currentBlock.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    currentBlock.setBounds(57, 59, 98, 98);
    getContentPane().add(currentBlock);
    
    pointTextField = new JTextField();
    pointTextField.setBounds(113, 249, 85, 26);
    getContentPane().add(pointTextField);
    pointTextField.setColumns(10);
    
    rTextField = new JTextField();
    rTextField.setBounds(113, 291, 85, 26);
    getContentPane().add(rTextField);
    rTextField.setColumns(10);
    
    gTextField = new JTextField();
    gTextField.setBounds(113, 333, 85, 26);
    getContentPane().add(gTextField);
    gTextField.setColumns(10);
    
    blockIdSelection = new JComboBox<String>();
    blockIdSelection.setBounds(113, 207, 85, 26);
    getContentPane().add(blockIdSelection);
    
    bTextField = new JTextField();
    bTextField.setBounds(113, 375, 85, 26);
    getContentPane().add(bTextField);
    bTextField.setColumns(10);
    
    blockIdLabel = new JLabel("Block");
    blockIdLabel.setBounds(29, 207, 69, 20);
    getContentPane().add(blockIdLabel);
    
    pointLabel = new JLabel("Points");
    pointLabel.setBounds(29, 249, 69, 20);
    getContentPane().add(pointLabel);
    
    rLabel = new JLabel("R");
    rLabel.setBounds(29, 291, 69, 20);
    getContentPane().add(rLabel);
    
    gLabel = new JLabel("G");
    gLabel.setBounds(29, 333, 69, 20);
    getContentPane().add(gLabel);
    
    bLabel = new JLabel("B");
    bLabel.setBounds(29, 375, 69, 20);
    getContentPane().add(bLabel);
    
    blockDisplayLabel = new JLabel("All block in this game:");
    blockDisplayLabel.setBounds(234, 59, 203, 20);
    getContentPane().add(blockDisplayLabel);
    
    btnSaveGame = new JButton("Save Game");
    btnSaveGame.setBounds(448, 16, 115, 29);
    getContentPane().add(btnSaveGame);
    
    saveButton = new JButton("Save");
    saveButton.setBounds(15, 427, 100, 29);
    getContentPane().add(saveButton);
    
    deleteButton = new JButton("Delete");
    deleteButton.setBounds(119, 427, 100, 29);
    getContentPane().add(deleteButton);
    
    addButton = new JButton("New Block");
    addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
      }
    });
    addButton.setBounds(15, 463, 204, 29);
    getContentPane().add(addButton);
    
    lvConfigButton = new JButton("Add Block to Level");
    lvConfigButton.setBounds(15, 499, 204, 29);
    getContentPane().add(lvConfigButton);
    
    errorMessage = new String();
    errorLabel = new JLabel("");
    errorLabel.setForeground(Color.red);
    errorLabel.setBounds(29, 20, 364, 20);
    getContentPane().add(errorLabel);


    
    try {
      ArrayList<TOBlock> blocksFromController = (ArrayList<TOBlock>) Block223Controller.getBlocksOfCurrentDesignableGame();
      displayPanelDimension = new Dimension(250,(blocksFromController.size()/3+1)* ((250)/3));
      blockDisplayPanel = new Block223BlockDisplayPanel(blocksFromController, displayPanelDimension, blockIdSelection, pointTextField, rTextField, gTextField, bTextField, currentBlock, blocks);

      ((JPanel)blockDisplayPanel).setPreferredSize(displayPanelDimension);
    } catch (InvalidInputException e) {
      
      e.printStackTrace();
    }
    scrollPane = new JScrollPane(blockDisplayPanel);
    blockDisplayPanel.setLayout(null);
    scrollPane.setBounds(234, 96, 329, 432);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    getContentPane().add(scrollPane);
    
    
    addButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addButtonClicked();
      }
    });
    
    saveButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        saveButtonClicked();
      }
    });
    
    deleteButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        deleteButtonClicked();
      }
    });
    
    lvConfigButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        lvConfigButtonClicked();
      }
    });
    
    blockIdSelection.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        if(blockIdSelection.getSelectedIndex()!=-1) {
         int id = Integer.parseInt(blockIdSelection.getSelectedItem().toString());
         try {
          TOBlock temp = Block223Controller.getBlockOfCurrentDesignableGame(id);
          pointTextField.setText(""+temp.getPoints());
          rTextField.setText(""+temp.getRed());
          gTextField.setText(""+temp.getGreen());
          bTextField.setText(""+temp.getBlue());
          
          currentBlock.setBackground(new Color(temp.getRed(), temp.getGreen(), temp.getBlue()));
        } catch (InvalidInputException e) {
          // TODO Auto-generated catch block
          errorMessage = e.getMessage();
        }
        }
      }
    });
    
    btnSaveGame.addActionListener(new ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent e) {
          saveSettingActionPerformed(e);
      }
  });

    
    
    
    
  }
  
  private void refreshData()  {
    errorLabel.setText(errorMessage);
    if (errorMessage == null || errorMessage.length() == 0) {


      blockIdSelection.removeAllItems();
      blocks = new HashMap<Integer, Integer>();
      Integer index = 0;


      ArrayList<TOBlock> blockFromController = new ArrayList<TOBlock>();
      try {
        blockFromController = (ArrayList<TOBlock>) Block223Controller.getBlocksOfCurrentDesignableGame();

      } catch (InvalidInputException e2) {
        // TODO Auto-generated catch block
        errorMessage = e2.getMessage();
      }
      
      
      for (TOBlock block : blockFromController) {
        blocks.put(block.getId(), index);
        index++;
        blockIdSelection.addItem("" + block.getId());
        // add block to the panel in the scrollPane
      }
      
      blockDisplayPanel.setBlocks(blockFromController, blocks);
      blockIdSelection.setSelectedIndex(-1);
      
      pointTextField.setText("");
      rTextField.setText("");
      gTextField.setText("");
      bTextField.setText("");
      currentBlock.setBackground(new Color(240,240,240));
      currentBlock.setBorder(BorderFactory.createLineBorder(Color.GRAY));

    }
    
  }

  private void saveButtonClicked() {

    errorMessage = null;
    
    int id = Integer.parseInt(blockIdSelection.getSelectedItem().toString());

    String red = rTextField.getText();
    String green = gTextField.getText();
    String blue = bTextField.getText();
    String points = pointTextField.getText();

    try {
      Block223Controller.updateBlock(id, Integer.parseInt(red), Integer.parseInt(green),
          Integer.parseInt(blue), Integer.parseInt(points));
    } catch (NumberFormatException e) {
      errorMessage = "Red/green/blue number needs to be a numerical value!";
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
      //errorLabel.setText(errorMessage);
    }
    
    refreshData();

  }

  private void addButtonClicked() {
    this.dispose();
    new Block223CreateBlock().setVisible(true);
  }

  private void deleteButtonClicked() {
    errorMessage = null;
    int id = Integer.parseInt(blockIdSelection.getSelectedItem().toString());

    try {
      Block223Controller.deleteBlock(id);
    } catch (InvalidInputException e) {
      // TODO Auto-generated catch block
      errorMessage = e.getMessage();
//      errorLabel.setText(errorMessage);
    }
    
    refreshData();
  }
  
  private void saveSettingActionPerformed(ActionEvent evt) {
    errorMessage = null;
        try {
            Block223Controller.saveGame();
        } catch (InvalidInputException e) {
            // TODO Auto-generated catch block
            errorMessage = e.getMessage();
        }
    }

  private void lvConfigButtonClicked() {
    this.dispose();
    new Block223AddBlockToLevel().setVisible(true);
  }

}