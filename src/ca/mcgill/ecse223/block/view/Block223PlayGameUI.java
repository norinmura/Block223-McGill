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
import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;
import ca.mcgill.ecse223.block.controller.TOPlayableGame;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.PlayedBlockAssignment;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Block223PlayGameUI extends JFrame implements Block223GamePanelInterface {

	int page = 0;
	Block223PlayGameUI myInstance;
	TOCurrentlyPlayedGame thisGame;
	private JList list;
	JPanel panel;
	JLabel GameOverLabel;
	JLabel livesLabel;
	JLabel levelLabel;
	JLabel nrLevelLabel;
	JLabel nrScoreLabel;
	JLabel nrLivesLabel;
	String[] HOFList;

	String keyName = "";
	Thread gameThread;

	public Block223PlayGameUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("GameUIWindowClosed");
				gameThread.interrupt();
			}
		});
		if (myInstance == null) {
			myInstance = this;
		}
		initComponents();
		initPlayerUI();
	}

	// Called only when player is playing the game
	public void initPlayerUI() {
		initGamePanel();
		refreshData();
		initHOF();
		refreshHOF();
	}

	public void initComponents() {
		setTitle("Playing Game");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 602, 561);
		getContentPane().setLayout(null);

		JLabel gameLabel = new JLabel("BLOCK223");
		gameLabel.setFocusable(false);
		gameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		gameLabel.setForeground(new Color(0, 0, 255));
		gameLabel.setBounds(36, 23, 163, 44);
		getContentPane().add(gameLabel);

		livesLabel = new JLabel("Lives:");
		livesLabel.setFocusable(false);
		livesLabel.setBounds(331, 23, 61, 16);
		getContentPane().add(livesLabel);

		levelLabel = new JLabel("Level:");
		levelLabel.setFocusTraversalKeysEnabled(false);
		levelLabel.setFocusable(false);
		levelLabel.setBounds(211, 23, 61, 16);
		getContentPane().add(levelLabel);

		JLabel scoreLabel = new JLabel("Score:");
		scoreLabel.setFocusable(false);
		scoreLabel.setFocusTraversalKeysEnabled(false);
		scoreLabel.setBounds(258, 51, 61, 16);
		getContentPane().add(scoreLabel);

		nrLevelLabel = new JLabel("90");
		nrLevelLabel.setFocusTraversalKeysEnabled(false);
		nrLevelLabel.setFocusable(false);
		nrLevelLabel.setBounds(258, 23, 61, 16);
		getContentPane().add(nrLevelLabel);

		nrScoreLabel = new JLabel("3000");
		nrScoreLabel.setFocusTraversalKeysEnabled(false);
		nrScoreLabel.setFocusable(false);
		nrScoreLabel.setBounds(307, 51, 61, 16);
		getContentPane().add(nrScoreLabel);

		nrLivesLabel = new JLabel("2");
		nrLivesLabel.setFocusTraversalKeysEnabled(false);
		nrLivesLabel.setFocusable(false);
		nrLivesLabel.setBounds(387, 23, 61, 16);
		getContentPane().add(nrLivesLabel);

		GameOverLabel = new JLabel("Game Over");
		GameOverLabel.setForeground(Color.RED);
		GameOverLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 45));
		GameOverLabel.setVisible(false);
		// panel.add(GameOverLabel);

		list = new JList();
		//    list.setRowSelectionAllowed(false);
		list.setFocusTraversalKeysEnabled(false);
		list.setFocusable(false);
		list.setEnabled(false);
		list.setBounds(443, 136, 135, 340);
		getContentPane().add(list);

		JLabel HLFLabel = new JLabel("Hall Of Fame");
		HLFLabel.setBounds(469, 73, 81, 16);
		getContentPane().add(HLFLabel);

		JLabel nameLabel = new JLabel("Mcgill Group");
		nameLabel.setBounds(469, 101, 81, 16);
		getContentPane().add(nameLabel);

		JButton previousButton = new JButton("Previous");
		previousButton.setFocusPainted(false);
		previousButton.setFocusTraversalKeysEnabled(false);
		previousButton.setFocusable(false);
		previousButton.setBounds(420, 488, 81, 29);
		getContentPane().add(previousButton);

		JButton nextButton = new JButton("Next");
		nextButton.setFocusTraversalKeysEnabled(false);
		nextButton.setFocusPainted(false);
		nextButton.setFocusable(false);
		nextButton.setBounds(521, 488, 75, 29);
		getContentPane().add(nextButton);

		JButton saveButton = new JButton("Save");
		saveButton.setFocusable(false);
		saveButton.setFocusTraversalKeysEnabled(false);
		saveButton.setFocusPainted(false);
		saveButton.setBounds(505, 6, 91, 29);
		getContentPane().add(saveButton);

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

	public void initGamePanel() {
		// Add GamePanel
		panel = new Block223GamePanel(this, Block223Controller.getBlockSize(),
				Block223Controller.getBallSize(), Block223Controller.getPaddleWidth(),
				Block223Controller.getPaddleVerticalDistance());

		panel.setBounds(26, 116, 395, 395);
		panel.setBackground(Color.WHITE);
		panel.setFocusable(false);
		getContentPane().add(panel);
		// Adding key listner to the frame to detect input
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					keyName = "l";
					break;
				case KeyEvent.VK_RIGHT:
					keyName = "r";
					break;
				case KeyEvent.VK_SPACE:
					if (gameThread == null || !gameThread.isAlive()) {
						startGameThread();
					} else {
						keyName = " ";
					}
					break;
				default:
					keyName = "";
					break;
				}
			}
			public void keyReleased(KeyEvent e) {
				keyName = "";
			}
		});
	}

	public void startGameThread() {
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

		gameThread.start();
	}

	public void refreshData() {
		// Get latest playedGame
		if(!Block223Controller.isGameOver()) {
			try {
				thisGame = Block223Controller.getCurrentPlayableGame();
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Update the labels
			nrLivesLabel.setText(Integer.toString(thisGame.getLives()));
			nrLevelLabel.setText(Integer.toString(thisGame.getCurrentLevel()));
			nrScoreLabel.setText(Integer.toString(thisGame.getScore()));

			// Repaint the game panel
			panel.repaint();
		}
	}

	private void initHOF() {
		int start = 0;
		int end = Block223Controller.getHOFLength(thisGame.getGamename());
		HOFList = new String[end];
		for (int i=start; i<end; i++) {
			int rank;
			try {
				rank = Block223Controller.getHallOfFame(start, end).getEntry(i).getPosition();
				String name =  Block223Controller.getHallOfFame(start, end).getEntry(i).getPlayername();
				int score =  Block223Controller.getHallOfFame(start, end).getEntry(i).getScore();
				System.out.println();
				HOFList[i] = name +" "+ score + " pts";
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void refreshHOF() {	 
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


		//    try {
		//      for (TOHallOfFameEntry entry: Block223Controller.getHallOfFame(page*10, (page*10)+9).getEntries()) {
		//        int rank = entry.getPosition();
		//        String name = entry.getPlayername();
		//        int score = entry.getScore();
		//        JLabel thisLabel = new JLabel(rank + " - " +name + ": " + score);
		//        list.add(thisLabel);
		//      }
		//    } catch (InvalidInputException e) {
		//      // TODO Auto-generated catch block
		//      e.printStackTrace();
		//    }
	}

	private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {
		page -= 1;
		refreshHOF();
	}

	private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {
		page += 1;
		refreshHOF();
	}

	public TOCurrentlyPlayedGame getCurrentPlayableGame() {
		return thisGame;
	}

	@Override
	public String takeInputs() {
		return keyName;
	}

	@Override
	public void refresh() {
		refreshData();
	}

	@Override
	public void endGame(int nrOfLives, TOHallOfFameEntry hof) {
		// TODO Auto-generated method stub
		dispose();
		new Block223HallOffame().setVisible(true);

	}
}


