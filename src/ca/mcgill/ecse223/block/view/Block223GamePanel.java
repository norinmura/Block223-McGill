package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOCurrentBlock;
import ca.mcgill.ecse223.block.controller.TOCurrentlyPlayedGame;
import ca.mcgill.ecse223.block.controller.TOGridCell;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Paddle;

public class Block223GamePanel extends JPanel {

  private Block223GamePanelInterface ui;
  private TOCurrentlyPlayedGame playedGame;
  private int blockSize;
  private int ballDiameter;
  private int paddleWidth;
  private int paddleVerticalDistance;
    
  public Block223GamePanel(Block223GamePanelInterface ui, int blockSize, int ballDiameter, int paddleWidth, int paddleVerticalDistance) {
    this.ui = ui;
    this.blockSize = blockSize;
    this.ballDiameter = ballDiameter;
    this.paddleWidth = paddleWidth;
    //TODO: fix this
    this.paddleVerticalDistance = Game.PLAY_AREA_SIDE - Paddle.VERTICAL_DISTANCE - Paddle.PADDLE_WIDTH;
  }


  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D gp = (Graphics2D) g.create();

    // Obtain the latest getCurrentPlayableGame
    playedGame = ui.getCurrentPlayableGame();
    
    // Only if playedGame exists, do the paint
    if (playedGame != null) {
      // Create Blocks
      for (TOCurrentBlock currentBlock : playedGame.getBlocks()) {
        gp.setColor(new Color(currentBlock.getRed(), currentBlock.getGreen(),
            currentBlock.getBlue()));
        gp.fill(new Rectangle2D.Double(currentBlock.getX(),
            currentBlock.getY(), blockSize, blockSize));
      }

      // Create Ball
      gp.setColor(Color.GREEN);
      gp.fill(new Ellipse2D.Double(playedGame.getCurrentBallX() - ((double) ballDiameter) / 2,
          playedGame.getCurrentBallY() - ((double) ballDiameter) / 2, ballDiameter, ballDiameter));

      // Create Paddle
      gp.setColor(Color.RED);
      gp.fill(new Rectangle2D.Double(playedGame.getCurrentPaddleX(), paddleVerticalDistance, playedGame.getCurrentPaddleLength(), paddleWidth));
    }

  }
}
