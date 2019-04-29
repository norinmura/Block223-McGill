package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOCurrentBlock;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOGridCell;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.BlockAssignment;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Level;
import ca.mcgill.ecse223.block.model.PlayedBlockAssignment;
import ca.mcgill.ecse223.block.model.Player;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;
import java.awt.Rectangle;

public class Block223MoveBlock extends JFrame {
  private JPanel contentPane;
  private static JTextField textField;
  private static JTextField textField_1;
  private static JComboBox<String> comboBox;
  
  private JLabel errorMessage;
  private JPanel panel;

  private static List<TOGridCell> listGridCell;
  private static boolean initialized;
  private static int oldGridHorizontalPosition;
  private static int oldGridVerticalPosition;
  private static TOGridCell selectedBlock;
  
  
  /**
   * Create the frame.
   * @throws InvalidInputException 
   */
  public Block223MoveBlock() {
    setTitle("Modify Block Location on a Grid");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 649, 457);
    
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    

    JLabel lblLevel = new JLabel("Level");
    lblLevel.setBounds(33, 22, 32, 16);
    comboBox = new JComboBox<String>();
    comboBox.setBounds(83, 22, 111, 27);

    JLabel lblBlock = new JLabel("Select a block in the preview: ");
    lblBlock.setBounds(20, 106, 199, 16);

    JLabel lblX = new JLabel("X");
    lblX.setBounds(33, 156, 25, 16);

    JLabel lblY = new JLabel("Y");
    lblY.setBounds(33, 193, 25, 16);

    textField = new JTextField();
    textField.setBounds(76, 151, 111, 26);
    textField.setColumns(10);

    textField_1 = new JTextField();
    textField_1.setBounds(76, 183, 111, 26);
    textField_1.setColumns(10);

    
    // Initialize the view
    try {
      init(1);
    } catch (InvalidInputException e2) {
      // TODO Auto-generated catch block
      errorMessage.setText(e2.getMessage());
    }



    /*
     * Level Changed
     */

    comboBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED && initialized) {
          int sel_level = Integer.parseInt((String) comboBox.getSelectedItem());
          try {
            init(sel_level);
          } catch (InvalidInputException e1) {
            // TODO Auto-generated catch block
            errorMessage.setText(e1.getMessage());
          }
        }

      }
    });


    
    /*
     * Apply change
     */
    JButton btnApplyChange = new JButton("Apply Change");
    btnApplyChange.setBounds(20, 238, 174, 29);
    btnApplyChange.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int sel_level = Integer.parseInt((String) comboBox.getSelectedItem());

        int newGridHorizontalPosition = Integer.parseInt(textField.getText());
        int newGridVerticalPosition = Integer.parseInt(textField_1.getText());

        try {
          Block223Controller.moveBlock(sel_level, oldGridHorizontalPosition, oldGridVerticalPosition, newGridHorizontalPosition, newGridVerticalPosition);
        } catch (InvalidInputException e1) {
          // TODO Auto-generated catch block
          errorMessage.setText(e1.getMessage());
        }

        System.out.println("Block moved to " + newGridHorizontalPosition + "/" + newGridVerticalPosition);

        clear();

        try {
          init(sel_level);
        } catch (InvalidInputException e1) {
          // TODO Auto-generated catch block
          errorMessage.setText(e1.getMessage());
        }

      }
    });

    /*
     * Remove Block
     */
    JButton btnRemove = new JButton("Remove");
    btnRemove.setBounds(20, 273, 174, 29);
    btnRemove.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int sel_level = Integer.parseInt((String) comboBox.getSelectedItem());

        try {
          Block223Controller.removeBlock(sel_level, Integer.parseInt(textField.getText()), Integer.parseInt(textField_1.getText()));
        } catch (NumberFormatException | InvalidInputException e1) {
          // TODO Auto-generated catch block
          errorMessage.setText(e1.getMessage());
        }

        try {
          init(sel_level);
        } catch (InvalidInputException e1) {
          // TODO Auto-generated catch block
          errorMessage.setText(e1.getMessage());
        }

      }
    });

    /*
     * Save and exit
     */
    JButton btnSaveAndReturn = new JButton("Save and Return");
    btnSaveAndReturn.setBounds(20, 360, 174, 29);
    btnSaveAndReturn.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        try {
          closeActionPerformed();
        } catch (InvalidInputException e1) {
          errorMessage.setText(e1.toString());
          errorMessage.setText(e1.getMessage());
        }
      }
    });

    JSeparator separator = new JSeparator();
    separator.setBounds(217, 6, 17, 410);
    separator.setOrientation(SwingConstants.VERTICAL);
    
    errorMessage = new JLabel("");
    errorMessage.setBounds(72, 314, 0, 0);
    errorMessage.setForeground(Color.RED);
    contentPane.setLayout(null);
        
    contentPane.add(panel);
    contentPane.add(lblBlock);
    contentPane.add(lblLevel);
    contentPane.add(lblY);
    contentPane.add(lblX);
    contentPane.add(comboBox);
    contentPane.add(textField_1);
    contentPane.add(textField);
    contentPane.add(btnRemove);
    contentPane.add(btnApplyChange);
    contentPane.add(btnSaveAndReturn);
    contentPane.add(errorMessage);
    contentPane.add(separator);
    
    JSeparator separator_1 = new JSeparator();
    separator_1.setBounds(6, 61, 199, 12);
    contentPane.add(separator_1);
  }
  
  public void blockSelected() {
    oldGridHorizontalPosition = selectedBlock.getGridHorizontalPosition();
    oldGridVerticalPosition = selectedBlock.getGridVerticalPosition();
    textField.setText(Integer.toString(oldGridHorizontalPosition));
    textField_1.setText(Integer.toString(oldGridVerticalPosition));
  }


  /**
   * Initialize the Block Dropdownlist
   * @param level
   * @throws InvalidInputException
   */
  public void init (int level) throws InvalidInputException {    
    // remove flag
    initialized = false;

    // clear both comboBox
    clear();

    // Obtain game
    TOGame currentGame = Block223Controller.getCurrentDesignableGame();

    // Update Level List
    int nrLevels = currentGame.getNrLevels();

    for (int i=0; i<nrLevels; i++) {
      String itemText = Integer.toString(i+1);
      comboBox.addItem(itemText);
      System.out.println("Added lv:" + itemText);
    }

    comboBox.setSelectedIndex(level-1);

    // Update Block List based on level
    listGridCell = Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(level);
    
    // Refresh the preview
    if (panel == null) {
      panel = new previewPanel();
      //panel = new JPanel();
      panel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          int blockSize = Block223Controller.getBlockSize();
          int wallPadding = Block223Controller.getWallPadding();
          int columnPadding = Block223Controller.getColumnPadding();
          int rowPadding = Block223Controller.getRowPadding();
          
          for (TOGridCell currentBlock : listGridCell) {
            int absX = wallPadding + (blockSize + columnPadding) * (currentBlock.getGridHorizontalPosition()-1);
            int absY = wallPadding + (blockSize + rowPadding) * (currentBlock.getGridVerticalPosition()-1);
            Rectangle2D.Double bounds = new Rectangle2D.Double(absX, absY, blockSize, blockSize);
            
            if (bounds.contains(e.getPoint())) {
              selectedBlock = currentBlock;
              blockSelected();
            }
          }
        }
      });
      panel.setSize(new Dimension(395, 395));
      panel.setBackground(Color.WHITE);
      panel.setBounds(new Rectangle(229, 20, 395, 395));
    } else {
      panel.repaint();
    }
    
    initialized = true;
  }
  

  /**
   * This method is used to clear both Combo Box and both textbox
   */
  public void clear() {
    comboBox.removeAllItems();
    textField.setText("");
    textField_1.setText("");
  }
  
  /**
   * Closing the window
   * @throws InvalidInputException
   */
  public void closeActionPerformed () throws InvalidInputException {
    Block223Controller.saveGame();
    this.dispose();
  }
  
  /**
   * This class extends JPanel, used to generate preview of the current block assignment
   * @author norinmura
   *
   */
  static class previewPanel extends JPanel {
    @Override
    public void paint (Graphics g) {
      super.paint(g);
      Graphics2D gp = (Graphics2D) g.create();
     
      if (listGridCell == null) {
        try {
          listGridCell = Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(1);
        } catch (NumberFormatException | InvalidInputException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      
      int blockSize = Block223Controller.getBlockSize();
      int wallPadding = Block223Controller.getWallPadding();
      int columnPadding = Block223Controller.getColumnPadding();
      int rowPadding = Block223Controller.getRowPadding();
      
      for (TOGridCell currentBlock : listGridCell) {
        int absX = wallPadding + (blockSize + columnPadding) * (currentBlock.getGridHorizontalPosition()-1);
        int absY = wallPadding + (blockSize + rowPadding) * (currentBlock.getGridVerticalPosition()-1);
        gp.setColor(new Color(currentBlock.getRed(), currentBlock.getGreen(),
            currentBlock.getBlue()));
        gp.fill(new Rectangle2D.Double(absX, absY, blockSize, blockSize));
      }
      
      // Create Paddle
      gp.setColor(Color.RED);
      gp.fill(new Rectangle2D.Double(390/2 - 100/2, 390 - Block223Controller.getPaddleVerticalDistance(), 100, Block223Controller.getPaddleWidth()));
    }
  }
}