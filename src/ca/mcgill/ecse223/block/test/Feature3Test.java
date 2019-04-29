package ca.mcgill.ecse223.block.test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.model.BouncePoint.BounceDirection;

public class Feature3Test {

  Block223 block223;
  Admin admin;
  Player player;
  User user;
  Game game;
  PlayedGame playedGame;
  
  @Before
  public void setup() {
    block223 = new Block223();
    admin = new Admin("123", block223);
    player = new Player("456", block223);
    user = new User("u1", block223, admin, player);
    String gameName = "g1";
    int numBlock = 20;
    int maxPaddleLength = 20;
    int minPaddleLength = 3;
    int minSpeedX = 10;
    int minSpeedY = 10;
    double speedIncreaseFactor = 5;
    game = new Game(gameName, numBlock, (Admin) user.getRole(0), minSpeedX, minSpeedY,
        speedIncreaseFactor, maxPaddleLength, minPaddleLength, block223);
    game.addLevel();
    Block block = new Block(10,10,10,10,game);
    playedGame = new PlayedGame(user.getUsername(), game, block223);
  }
  
  @Test
  public void testCalculateBouncePointPaddlePerpendicularBallSegment() {
    playedGame.setCurrentPaddleX(20);
    playedGame.setBallDirectionX(0);
    playedGame.setBallDirectionY(12);
    playedGame.setCurrentBallY(playedGame.getCurrentPaddleY()-15);   //15 pixel above the paddle
    playedGame.setCurrentBallX(27);
    playedGame.setCurrentPaddleLength(10);
    //expected bounce point (25,350)
    BouncePoint bp = new BouncePoint(27,350, BounceDirection.FLIP_Y);
    
    BouncePoint temp = playedGame.calculateBouncePointPaddle();
    
    assertEquals(bp.getX(), temp.getX(), 0.1);
    assertEquals(bp.getY(), temp.getY(), 0.1);
    assertEquals(bp.getDirection(), temp.getDirection());
    
  }
  
  @Test
  public void testCalculateBouncePointHorizontalBallSegment() {
    playedGame.setCurrentPaddleX(20);
    playedGame.setBallDirectionX(12);
    playedGame.setBallDirectionY(0);
    playedGame.setCurrentBallY(playedGame.getCurrentPaddleY()+3);   
    playedGame.setCurrentBallX(13);
    playedGame.setCurrentPaddleLength(10);
    //expected bounce point (15,358)
    BouncePoint bp = new BouncePoint(15,358, BounceDirection.FLIP_X);
    
    BouncePoint temp = playedGame.calculateBouncePointPaddle();
    
    assertEquals(bp.getX(), temp.getX(), 0.1);
    assertEquals(bp.getY(), temp.getY(), 0.1);
    assertEquals(bp.getDirection(), temp.getDirection());
  }
  
  @Test
  public void testCalculateBouncePointHorizontalBallSegment2() {
    playedGame.setCurrentPaddleX(20);
    playedGame.setBallDirectionX(-12);
    playedGame.setBallDirectionY(0);
    playedGame.setCurrentBallY(playedGame.getCurrentPaddleY()+3);   
    playedGame.setCurrentBallX(37);
    playedGame.setCurrentPaddleLength(10);
    //expected bounce point (35,358)
    BouncePoint bp = new BouncePoint(35,358, BounceDirection.FLIP_X);
    
    BouncePoint temp = playedGame.calculateBouncePointPaddle();
    
    assertEquals(bp.getX(), temp.getX(), 0.1);
    assertEquals(bp.getY(), temp.getY(), 0.1);
    assertEquals(bp.getDirection(), temp.getDirection());
  }
  
  @Test
  public void testCalculateBouncePointBallSegment1() {
    playedGame.setCurrentPaddleX(20);
    playedGame.setBallDirectionX(4);
    playedGame.setBallDirectionY(12);
    playedGame.setCurrentBallY(playedGame.getCurrentPaddleY()-15);   //15 pixel above the paddle
    playedGame.setCurrentBallX(24);
    playedGame.setCurrentPaddleLength(10);
    //expected bounce point (82/3,350)
    BouncePoint bp = new BouncePoint((double)82/3,350, BounceDirection.FLIP_Y);
    
    BouncePoint temp = playedGame.calculateBouncePointPaddle();
    
    assertEquals(bp.getX(), temp.getX(), 0.1);
    assertEquals(bp.getY(), temp.getY(), 0.1);
    assertEquals(bp.getDirection(), temp.getDirection());
  }
  
  @Test
  public void testCalculateBouncePointBallSegment2() {
    playedGame.setCurrentPaddleX(20);
    playedGame.setBallDirectionX(4);
    playedGame.setBallDirectionY(11);
    playedGame.setCurrentBallY(playedGame.getCurrentPaddleY()-10);  
    playedGame.setCurrentBallX(13);
    playedGame.setCurrentPaddleLength(10);
    //expected bounce point (82/3,350)
    BouncePoint bp = new BouncePoint(15,350.5, BounceDirection.FLIP_X);
    
    BouncePoint temp = playedGame.calculateBouncePointPaddle();
    
    assertEquals(bp.getX(), temp.getX(), 0);
    assertEquals(bp.getY(), temp.getY(), 0);
    assertEquals(bp.getDirection(), temp.getDirection());
  }
  
  @Test
  public void testCalculateBouncePointHitArcE() {
    playedGame.setCurrentPaddleX(20);
    playedGame.setBallDirectionX(3);
    playedGame.setBallDirectionY(7);
    playedGame.setCurrentBallY(playedGame.getCurrentPaddleY()-8); 
    playedGame.setCurrentBallX(14);
    playedGame.setCurrentPaddleLength(10);
    //expected bounce point (82/3,350)
    BouncePoint bp = new BouncePoint(16.09,351.88, BounceDirection.FLIP_X);
    
    BouncePoint temp = playedGame.calculateBouncePointPaddle();
    
    assertEquals(bp.getX(), temp.getX(), 0.5);
    assertEquals(bp.getY(), temp.getY(), 0.5);
    assertEquals(bp.getDirection(), temp.getDirection());
  }
  
  @Test
  public void testCalculateBouncePointHitArcF() {
    playedGame.setCurrentPaddleX(20);
    playedGame.setBallDirectionX(2);
    playedGame.setBallDirectionY(11);
    playedGame.setCurrentBallY(playedGame.getCurrentPaddleY()-12);  
    playedGame.setCurrentBallX(32);
    playedGame.setCurrentPaddleLength(10);
    BouncePoint bp = new BouncePoint(33.54,351.47, BounceDirection.FLIP_Y);
    
    BouncePoint temp = playedGame.calculateBouncePointPaddle();
    
    assertEquals(bp.getX(), temp.getX(), 0.5);
    assertEquals(bp.getY(), temp.getY(), 0.5);
    assertEquals(bp.getDirection(), temp.getDirection());
  }
  
  @Test
  public void testBounceBall() {
    playedGame.setCurrentPaddleX(20);
    playedGame.setBallDirectionX(2);
    playedGame.setBallDirectionY(11);
    playedGame.setCurrentBallY(playedGame.getCurrentPaddleY()-12);  
    playedGame.setCurrentBallX(32);
    playedGame.setCurrentPaddleLength(10);
    BouncePoint bp = new BouncePoint(33.54,351.47, BounceDirection.FLIP_Y);
    
    BouncePoint temp = playedGame.calculateBouncePointPaddle();
    
    assertEquals(bp.getX(), temp.getX(), 0.5);
    assertEquals(bp.getY(), temp.getY(), 0.5);
    assertEquals(bp.getDirection(), temp.getDirection());
  }
  
  @Test
  public void testCalculateBouncePointLeftWall() {
    playedGame.setBallDirectionX(-20);
    playedGame.setBallDirectionY(11);
    playedGame.setCurrentBallY(10);   
    playedGame.setCurrentBallX(10);
    BouncePoint bp = new BouncePoint(5,12.75, BounceDirection.FLIP_X);
    
    BouncePoint temp = playedGame.calculateBouncePointWall();
    
    assertEquals(bp.getX(), temp.getX(), 0.5);
    assertEquals(bp.getY(), temp.getY(), 0.5);
    assertEquals(bp.getDirection(), temp.getDirection());
  }
  
  @Test
  public void testCalculateBouncePointRightWall() {
    playedGame.setBallDirectionX(20);
    playedGame.setBallDirectionY(-50);
    playedGame.setCurrentBallY(100);   
    playedGame.setCurrentBallX(370);
    BouncePoint bp = new BouncePoint(385,62.5, BounceDirection.FLIP_X);
    
    BouncePoint temp = playedGame.calculateBouncePointWall();
    
    assertEquals(bp.getX(), temp.getX(), 0.5);
    assertEquals(bp.getY(), temp.getY(), 0.5);
    assertEquals(bp.getDirection(), temp.getDirection());
  }
  
  @Test
  public void testCalculateBouncePointTopWall() {
    playedGame.setBallDirectionX(10);
    playedGame.setBallDirectionY(-15);
    playedGame.setCurrentBallY(10);   
    playedGame.setCurrentBallX(200);
    BouncePoint bp = new BouncePoint((double)610/3,5, BounceDirection.FLIP_X);
    
    BouncePoint temp = playedGame.calculateBouncePointWall();
    
    assertEquals(bp.getX(), temp.getX(), 0.5);
    assertEquals(bp.getY(), temp.getY(), 0.5);
    assertEquals(bp.getDirection(), temp.getDirection());
  }
  
  @Test
  public void testBouncePoint() {
    playedGame.setBallDirectionX(0);
    playedGame.setBallDirectionY(10);
    playedGame.setCurrentBallX(195);   
    playedGame.setCurrentBallY(346);
    BouncePoint bp = new BouncePoint(195,350, BounceDirection.FLIP_Y);
    playedGame.setBounce(bp);
    playedGame.bounceBall();
    
    
    assertEquals(344, playedGame.getCurrentBallY(), 0);
    assertEquals(195.6, playedGame.getCurrentBallX(), 0);
    assertEquals(1, playedGame.getBallDirectionX(), 0);
    assertEquals(-10, playedGame.getBallDirectionY(),0);
    
  }
  
  @After
  public void tearDown() {
    block223.delete();
  }

}
