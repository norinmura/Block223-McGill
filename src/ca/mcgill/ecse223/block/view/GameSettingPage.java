package ca.mcgill.ecse223.block.view;

import java.awt.Color;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import ca.mcgill.ecse223.block.controller.*;
import ca.mcgill.ecse223.block.view.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameSettingPage extends JFrame {

  //	Font fontTitle = new Font("Comic Sans MS",Font.PLAIN,20);
  Font newTitleFont = new Font("Label.font",Font.PLAIN,18);

  // UI elements
  private JLabel errorMessage;
  // General 
  private JTextField numberOfLevelsTextField;
  private JLabel numberOfLevelsLabel;
  private JTextField numberOfBlocksPerLevelTextField;
  private JLabel numberOfBlocksPerLevelLabel;

  // Ball
  private JTextField minimumSpeedXTextField;
  private JLabel minimumSpeedXLabel;
  private JTextField speedIncreaseFactorTextField;
  private JLabel speedIncreaseFactorLabel;
  private JTextField minimumSpeedYTextField;
  private JLabel minimumSpeedYLabel;

  // Paddle
  private JTextField minimumLengthTextField;
  private JLabel minimumLengthLabel;
  private JTextField maximumLengthTextField;
  private JLabel maximumLengthLabel;

  // Buttons
  private JButton saveButton;
  private JButton setButton;
  private JButton blockSettingButton;
  private JButton levelSettingButton;

  // Subtitles
  private JLabel generalLabel;
  private JLabel ballLabel;
  private JLabel paddleLabel;

  // data elements
  private String error = null;

  /** Creates new form GameSettingPage */
  public GameSettingPage() {
    initComponents();
    refreshData();
  }

  private void initComponents() {
    this.setVisible(true);
    this.setSize(800,800);
    this.setLocation(300, 300);

    // elements for error message
    errorMessage = new JLabel();
    errorMessage.setForeground(Color.RED);

    // elements for subtitles
    generalLabel = new JLabel();
    generalLabel.setText("General:");
    generalLabel.setFont(newTitleFont);

    ballLabel = new JLabel();
    ballLabel.setText("Ball:");
    ballLabel.setFont(newTitleFont);

    paddleLabel = new JLabel();
    paddleLabel.setText("Paddle:");
    paddleLabel.setFont(newTitleFont);

    // elements for General		
    numberOfLevelsTextField = new JTextField();
    numberOfLevelsLabel = new JLabel();
    numberOfLevelsLabel.setText("Number of Levels:");

    numberOfBlocksPerLevelTextField = new JTextField();
    numberOfBlocksPerLevelLabel = new JLabel();
    numberOfBlocksPerLevelLabel.setText("Number of Blocks per Level:");		

    // elements for ball
    minimumSpeedXTextField = new JTextField();
    minimumSpeedXLabel = new JLabel();
    minimumSpeedXLabel.setText("Minimum Speed X:");

    speedIncreaseFactorTextField = new JTextField();
    speedIncreaseFactorLabel = new JLabel();
    speedIncreaseFactorLabel.setText("Speed Increase Factor:");

    minimumSpeedYTextField = new JTextField();
    minimumSpeedYLabel = new JLabel();
    minimumSpeedYLabel.setText("Minimum Speed Y:");

    // elements for paddle
    minimumLengthTextField = new JTextField();
    minimumLengthLabel = new JLabel();
    minimumLengthLabel.setText("Minimum Length:");

    maximumLengthTextField = new JTextField();
    maximumLengthLabel = new JLabel();
    maximumLengthLabel.setText("Maximum Length:");

    // elements for button
    saveButton = new JButton();
    saveButton.setText("Save Game");
    setButton = new JButton();
    setButton.setText("Set");
    blockSettingButton = new JButton();
    blockSettingButton.setText("Block Setting");
    levelSettingButton = new JButton();
    levelSettingButton.setText("Level Setting");


    // global settings
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle("GAME SETTING");

    // listeners for game setting
    setButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        setButtonActionPerformed(evt);
      }
    });

    saveButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        saveButtonActionPerformed(evt);
      }
    });

    blockSettingButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        blockSettingButtonActionPerformed(evt);
      }
    });

    levelSettingButton.addActionListener(new java.awt.event.ActionListener(){
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        levelSettingButtonActionPerformed(evt);

      }
    });

    JButton btnTest = new JButton("Test Game");
    btnTest.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        testGameButtonActionPerformed(evt);
      }
    });

    JButton btnPublishGame = new JButton("Publish Game");
    btnPublishGame.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        publishGameButtonActionPerformed(evt);
      }
    });

    // layout
    GroupLayout layout = new GroupLayout(getContentPane());
    layout.setHorizontalGroup(
        layout.createParallelGroup(Alignment.LEADING)
        .addComponent(errorMessage)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addComponent(generalLabel)
                .addComponent(numberOfLevelsLabel)
                .addComponent(ballLabel)
                .addComponent(minimumSpeedXLabel)
                .addComponent(speedIncreaseFactorLabel)
                .addComponent(paddleLabel)
                .addComponent(minimumLengthLabel))
            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(numberOfLevelsTextField, 100, 200, 400)
                        .addComponent(minimumSpeedXTextField, 200, 200, 200)
                        .addComponent(speedIncreaseFactorTextField, 100, 200, 400)
                        .addComponent(minimumLengthTextField, 200, 200, 200)
                        .addComponent(setButton, 100, 200, 400))
                    .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(numberOfBlocksPerLevelLabel)
                        .addComponent(minimumSpeedYLabel)
                        .addComponent(maximumLengthLabel)
                        .addComponent(blockSettingButton, 100, 200, 400)))
                .addGroup(layout.createSequentialGroup()
                    .addGap(139)
                    .addComponent(btnTest)
                    .addGap(22)
                    .addComponent(btnPublishGame)
                    .addGap(18)))
            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addComponent(saveButton)
                .addComponent(numberOfBlocksPerLevelTextField, 100, 200, 400)
                .addComponent(minimumSpeedYTextField, 200, 200, 200)
                .addComponent(maximumLengthTextField, 200, 200, 200)
                .addComponent(levelSettingButton, 100, 200, 400)))
        );
    layout.setVerticalGroup(
        layout.createParallelGroup(Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(errorMessage)
            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addComponent(generalLabel)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(btnTest)
                    .addComponent(btnPublishGame)))
            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addComponent(numberOfLevelsLabel)
                .addComponent(numberOfLevelsTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(numberOfBlocksPerLevelLabel)
                .addComponent(numberOfBlocksPerLevelTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addComponent(ballLabel)
            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addComponent(minimumSpeedXLabel)
                .addComponent(minimumSpeedXTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(minimumSpeedYLabel)
                .addComponent(minimumSpeedYTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addComponent(speedIncreaseFactorLabel)
                .addComponent(speedIncreaseFactorTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addComponent(paddleLabel)
            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addComponent(minimumLengthLabel)
                .addComponent(minimumLengthTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(maximumLengthLabel)
                .addComponent(maximumLengthTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addComponent(setButton)
                .addComponent(blockSettingButton)
                .addComponent(levelSettingButton)))
        );
    getContentPane().setLayout(layout);
    layout.setAutoCreateGaps(true);		
    layout.setAutoCreateContainerGaps(true);

    pack();
    // this.setSize(600,600);
  }

  private void refreshData() {
    // error
    errorMessage.setText(error);
    /*	if (error == null || error.length() == 0) {
		}*/
    pack();
  }

  private void setButtonActionPerformed(java.awt.event.ActionEvent evt) {
    // clear error message
    error = "";
    int nrLevels = -1;
    int nrBlocksPerLevel = -1;
    int minBallSpeedX = -1;
    int minBallSpeedY = -1;
    double ballSpeedIncreaseFactor = -1;
    int maxPaddleLength = -1;
    int minPaddleLength = -1;

    try {
      nrLevels = Integer.parseInt(numberOfLevelsTextField.getText());
      nrBlocksPerLevel = Integer.parseInt(numberOfBlocksPerLevelTextField.getText());
      minBallSpeedX = Integer.parseInt(minimumSpeedXTextField.getText());
      minBallSpeedY = Integer.parseInt(minimumSpeedYTextField.getText());
      ballSpeedIncreaseFactor = Double.parseDouble(speedIncreaseFactorTextField.getText());
      minPaddleLength = Integer.parseInt(minimumLengthTextField.getText());
      maxPaddleLength = Integer.parseInt(maximumLengthTextField.getText());
    }
    catch (NumberFormatException e) {
      error = "Integers should be used for all game settings, except speed increase factor which could have decimals.";
    }

    if (!(error == "Integers should be used for all game settings, except speed increase factor which could have decimals.")) {
      if (nrLevels < 1 || nrLevels > 99) {
        error = "The number of levels must be between 1 and 99."; 
      }
      if (nrBlocksPerLevel <= 0) {
        error = "The number of blocks per level must be greater than zero.";
      }
      if (minBallSpeedX < 0) {
        error = "The minimum speed of the ball must be greater than zero.";
      }
      if (minBallSpeedY < 0) {
        error = "The minimum speed of the ball must be greater than zero.";
      }
      if (minBallSpeedY == 0 && minBallSpeedX == 0) {
          error = "The minimum speed of the ball must be greater than zero.";
      }
      if (ballSpeedIncreaseFactor <= 0) {
        error = "The speed increase factor of the ball must be greater than zero.";
      }
      if (maxPaddleLength <= 0 || maxPaddleLength > 390 ) {
        error = "The maximum length of the paddle must be greater than zero and less than or equal to 390.";
      }
      if (minPaddleLength <= 0) {
        error = "The minimum length of the paddle must be greater than zero.";
      }
    }

    // call the controller
    if(error.length() == 0 || error == null) {
      try {
        Block223Controller.setGameDetails(nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY,
            ballSpeedIncreaseFactor, maxPaddleLength, minPaddleLength);			
      } 
      catch (InvalidInputException e) {
        error = e.getMessage();
      }
    }
    refreshData();
  }

  private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
    try {
      Block223Controller.saveGame();
    }
    catch (InvalidInputException e) {
      error = e.getMessage();
    }
  }



  private void publishGameButtonActionPerformed(java.awt.event.ActionEvent evt) {   
    // Jump to block setting UI
    try {
      Block223Controller.publishGame();
      Block223Controller.saveGame();
      JOptionPane.showMessageDialog(null, "published.");
      dispose();
    } catch (InvalidInputException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private void testGameButtonActionPerformed(java.awt.event.ActionEvent evt) { 
    // Jump to play
    new Block223TestGameUI().setVisible(true);
  }

  private void blockSettingButtonActionPerformed(java.awt.event.ActionEvent evt) {	
    // Jump to block setting UI
    new Block223BlockInGame().setVisible(true);
    // this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING) );	
  }

  private void levelSettingButtonActionPerformed(java.awt.event.ActionEvent evt) {
    //	Jump to level setting UI
    new Block223MoveBlock().setVisible(true);
    //	this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING) );	
  }
}
