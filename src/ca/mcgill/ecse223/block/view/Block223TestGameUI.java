package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOCurrentlyPlayedGame;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOGridCell;
import ca.mcgill.ecse223.block.controller.TOPlayableGame;
import ca.mcgill.ecse223.block.model.PlayedBlockAssignment;
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
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ItemEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Font;

public class Block223TestGameUI extends Block223PlayGameUI {
  TOGame thisTestGame;
  boolean isStarted;

  public Block223TestGameUI() {
    super();

    if (myInstance == null) {
      myInstance = this;
    }

    // first obtain the currently designed game to show stuffs
    try {
      thisTestGame = Block223Controller.getCurrentDesignableGame();
    } catch (InvalidInputException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    initTestLabels();
  }



  public void initPlayerUI() {
    initGamePanel();
    isStarted = false;
    // don't refresh data yet because there's no playedGame present yet
  }

  public void initTestLabels() {
    if (thisTestGame != null) {
      // Update the labels
      nrLivesLabel.setText("-");
      nrLevelLabel.setText(Integer.toString(thisTestGame.getNrLevels()));
      nrScoreLabel.setText(Integer.toString(0));
    }
  }

  public void startGameThread() {
    
    if (!isStarted) {
      gameThread = new Thread() {
        public void run() {
          System.out.println("test thread started");
          try {
            Block223Controller.testGame(myInstance);
          } catch (InvalidInputException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      };
      
    } else {

      gameThread = new Thread() {
        public void run() {
          System.out.println("thread started");
          try {
            Block223Controller.startGame(myInstance);
          } catch (InvalidInputException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      };
      
    }
    
    isStarted = true;
    gameThread.start();
  }

}


