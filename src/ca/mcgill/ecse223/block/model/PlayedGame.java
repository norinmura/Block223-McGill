/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.awt.geom.*;
import ca.mcgill.ecse223.block.model.BouncePoint.BounceDirection;
import java.io.Serializable;
import java.util.*;

// line 13 "../../../../../Block223PlayMode.ump"
// line 112 "../../../../../Block223Persistence.ump"
// line 2 "../../../../../Block223States.ump"
public class PlayedGame implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------


  /**
   * at design time, the initial wait time may be adjusted as seen fit
   */
  public static final int INITIAL_WAIT_TIME = 20;
  private static int nextId = 1;
  public static final int NR_LIVES = 3;

  /**
   * the PlayedBall and PlayedPaddle are not in a separate class to avoid the bug in Umple that occurred for the second constructor of Game
   * no direct link to Ball, because the ball can be found by navigating to Game and then Ball
   */
  public static final int BALL_INITIAL_X = Game.PLAY_AREA_SIDE / 2;
  public static final int BALL_INITIAL_Y = Game.PLAY_AREA_SIDE / 2;

  /**
   * no direct link to Paddle, because the paddle can be found by navigating to Game and then Paddle
   * pixels moved when right arrow key is pressed
   */
  public static final int PADDLE_MOVE_RIGHT = 1;

  /**
   * pixels moved when left arrow key is pressed
   */
  public static final int PADDLE_MOVE_LEFT = -1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayedGame Attributes
  private int score;
  private int lives;
  private int currentLevel;
  private double waitTime;
  private String playername;
  private double ballDirectionX;
  private double ballDirectionY;
  private double currentBallX;
  private double currentBallY;
  private double currentPaddleLength;
  private double currentPaddleX;
  private double currentPaddleY;

  //Autounique Attributes
  private int id;

  //PlayedGame State Machines
  public enum PlayStatus { Ready, Moving, Paused, GameOver }
  private PlayStatus playStatus;

  //PlayedGame Associations
  private Player player;
  private Game game;
  private List<PlayedBlockAssignment> blocks;
  private BouncePoint bounce;
  private Block223 block223;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayedGame(String aPlayername, Game aGame, Block223 aBlock223)
  {
    // line 52 "../../../../../Block223PlayMode.ump"
    boolean didAddGameResult = setGame(aGame);
          if (!didAddGameResult)
          {
             throw new RuntimeException("Unable to create playedGame due to game");
          }
    // END OF UMPLE BEFORE INJECTION
    score = 0;
    lives = NR_LIVES;
    currentLevel = 1;
    waitTime = INITIAL_WAIT_TIME;
    playername = aPlayername;
    resetBallDirectionX();
    resetBallDirectionY();
    resetCurrentBallX();
    resetCurrentBallY();
    currentPaddleLength = getGame().getPaddle().getMaxPaddleLength();
    resetCurrentPaddleX();
    currentPaddleY = Game.PLAY_AREA_SIDE - Paddle.VERTICAL_DISTANCE - Paddle.PADDLE_WIDTH;
    id = nextId++;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create playedGame due to game");
    }
    blocks = new ArrayList<PlayedBlockAssignment>();
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create playedGame due to block223");
    }
    setPlayStatus(PlayStatus.Ready);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setScore(int aScore)
  {
    boolean wasSet = false;
    score = aScore;
    wasSet = true;
    return wasSet;
  }

  public boolean setLives(int aLives)
  {
    boolean wasSet = false;
    lives = aLives;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentLevel(int aCurrentLevel)
  {
    boolean wasSet = false;
    currentLevel = aCurrentLevel;
    wasSet = true;
    return wasSet;
  }

  public boolean setWaitTime(double aWaitTime)
  {
    boolean wasSet = false;
    waitTime = aWaitTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setPlayername(String aPlayername)
  {
    boolean wasSet = false;
    playername = aPlayername;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallDirectionX(double aBallDirectionX)
  {
    boolean wasSet = false;
    ballDirectionX = aBallDirectionX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallDirectionX()
  {
    boolean wasReset = false;
    ballDirectionX = getDefaultBallDirectionX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallDirectionY(double aBallDirectionY)
  {
    boolean wasSet = false;
    ballDirectionY = aBallDirectionY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallDirectionY()
  {
    boolean wasReset = false;
    ballDirectionY = getDefaultBallDirectionY();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentBallX(double aCurrentBallX)
  {
    boolean wasSet = false;
    currentBallX = aCurrentBallX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentBallX()
  {
    boolean wasReset = false;
    currentBallX = getDefaultCurrentBallX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentBallY(double aCurrentBallY)
  {
    boolean wasSet = false;
    currentBallY = aCurrentBallY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentBallY()
  {
    boolean wasReset = false;
    currentBallY = getDefaultCurrentBallY();
    wasReset = true;
    return wasReset;
  }

  public boolean setCurrentPaddleLength(double aCurrentPaddleLength)
  {
    boolean wasSet = false;
    currentPaddleLength = aCurrentPaddleLength;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentPaddleX(double aCurrentPaddleX)
  {
    boolean wasSet = false;
    currentPaddleX = aCurrentPaddleX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentPaddleX()
  {
    boolean wasReset = false;
    currentPaddleX = getDefaultCurrentPaddleX();
    wasReset = true;
    return wasReset;
  }

  public int getScore()
  {
    return score;
  }

  public int getLives()
  {
    return lives;
  }

  public int getCurrentLevel()
  {
    return currentLevel;
  }

  public double getWaitTime()
  {
    return waitTime;
  }

  /**
   * added here so that it only needs to be determined once
   */
  public String getPlayername()
  {
    return playername;
  }

  /**
   * 0/0 is the top left corner of the play area, i.e., a directionX/Y of 0/1 moves the ball down in a straight line
   */
  public double getBallDirectionX()
  {
    return ballDirectionX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultBallDirectionX()
  {
    return getGame().getBall().getMinBallSpeedX();
  }

  public double getBallDirectionY()
  {
    return ballDirectionY;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultBallDirectionY()
  {
    return getGame().getBall().getMinBallSpeedY();
  }

  /**
   * the position of the ball is at the center of the ball
   */
  public double getCurrentBallX()
  {
    return currentBallX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentBallX()
  {
    return BALL_INITIAL_X;
  }

  public double getCurrentBallY()
  {
    return currentBallY;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentBallY()
  {
    return BALL_INITIAL_Y;
  }

  public double getCurrentPaddleLength()
  {
    return currentPaddleLength;
  }

  /**
   * the position of the paddle is at its top left corner
   */
  public double getCurrentPaddleX()
  {
    return currentPaddleX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentPaddleX()
  {
    return (Game.PLAY_AREA_SIDE - currentPaddleLength) / 2;
  }

  public double getCurrentPaddleY()
  {
    return currentPaddleY;
  }

  public int getId()
  {
    return id;
  }

  public String getPlayStatusFullName()
  {
    String answer = playStatus.toString();
    return answer;
  }

  public PlayStatus getPlayStatus()
  {
    return playStatus;
  }

  public boolean play()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Ready:
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      case Paused:
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean pause()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Moving:
        setPlayStatus(PlayStatus.Paused);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean move()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Moving:
        if (hitPaddle())
        {
        // line 13 "../../../../../Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBoundsAndLastLife())
        {
        // line 14 "../../../../../Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBounds())
        {
        // line 15 "../../../../../Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlockAndLastLevel())
        {
        // line 16 "../../../../../Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlock())
        {
        // line 17 "../../../../../Block223States.ump"
          doHitBlockNextLevel();
          setPlayStatus(PlayStatus.Ready);
          wasEventProcessed = true;
          break;
        }
        if (hitBlock())
        {
        // line 18 "../../../../../Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (hitWall())
        {
        // line 19 "../../../../../Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        // line 20 "../../../../../Block223States.ump"
        doHitNothingAndNotOutOfBounds();
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setPlayStatus(PlayStatus aPlayStatus)
  {
    playStatus = aPlayStatus;

    // entry actions and do activities
    switch(playStatus)
    {
      case Ready:
        // line 8 "../../../../../Block223States.ump"
        doSetup();
        break;
      case GameOver:
        // line 26 "../../../../../Block223States.ump"
        doGameOver();
        break;
    }
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }

  public boolean hasPlayer()
  {
    boolean has = player != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetMany */
  public PlayedBlockAssignment getBlock(int index)
  {
    PlayedBlockAssignment aBlock = blocks.get(index);
    return aBlock;
  }

  public List<PlayedBlockAssignment> getBlocks()
  {
    List<PlayedBlockAssignment> newBlocks = Collections.unmodifiableList(blocks);
    return newBlocks;
  }

  public int numberOfBlocks()
  {
    int number = blocks.size();
    return number;
  }

  public boolean hasBlocks()
  {
    boolean has = blocks.size() > 0;
    return has;
  }

  public int indexOfBlock(PlayedBlockAssignment aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_GetOne */
  public BouncePoint getBounce()
  {
    return bounce;
  }

  public boolean hasBounce()
  {
    boolean has = bounce != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setPlayer(Player aPlayer)
  {
    boolean wasSet = false;
    Player existingPlayer = player;
    player = aPlayer;
    if (existingPlayer != null && !existingPlayer.equals(aPlayer))
    {
      existingPlayer.removePlayedGame(this);
    }
    if (aPlayer != null)
    {
      aPlayer.addPlayedGame(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    if (aGame == null)
    {
      return wasSet;
    }

    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      existingGame.removePlayedGame(this);
    }
    game.addPlayedGame(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PlayedBlockAssignment addBlock(int aX, int aY, Block aBlock)
  {
    return new PlayedBlockAssignment(aX, aY, aBlock, this);
  }

  public boolean addBlock(PlayedBlockAssignment aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    PlayedGame existingPlayedGame = aBlock.getPlayedGame();
    boolean isNewPlayedGame = existingPlayedGame != null && !this.equals(existingPlayedGame);
    if (isNewPlayedGame)
    {
      aBlock.setPlayedGame(this);
    }
    else
    {
      blocks.add(aBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(PlayedBlockAssignment aBlock)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlock, as it must always have a playedGame
    if (!this.equals(aBlock.getPlayedGame()))
    {
      blocks.remove(aBlock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(PlayedBlockAssignment aBlock, int index)
  {  
    boolean wasAdded = false;
    if(addBlock(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockAt(PlayedBlockAssignment aBlock, int index)
  {
    boolean wasAdded = false;
    if(blocks.contains(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockAt(aBlock, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setBounce(BouncePoint aNewBounce)
  {
    boolean wasSet = false;
    bounce = aNewBounce;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBlock223(Block223 aBlock223)
  {
    boolean wasSet = false;
    if (aBlock223 == null)
    {
      return wasSet;
    }

    Block223 existingBlock223 = block223;
    block223 = aBlock223;
    if (existingBlock223 != null && !existingBlock223.equals(aBlock223))
    {
      existingBlock223.removePlayedGame(this);
    }
    block223.addPlayedGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (player != null)
    {
      Player placeholderPlayer = player;
      this.player = null;
      placeholderPlayer.removePlayedGame(this);
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removePlayedGame(this);
    }
    while (blocks.size() > 0)
    {
      PlayedBlockAssignment aBlock = blocks.get(blocks.size() - 1);
      aBlock.delete();
      blocks.remove(aBlock);
    }
    
    bounce = null;
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removePlayedGame(this);
    }
  }

  // line 62 "../../../../../Block223PlayMode.ump"
   public BouncePoint calculateBouncePointPaddle(){
    double boxLength = Ball.BALL_DIAMETER + currentPaddleLength;
   	double boxWidth = Ball.BALL_DIAMETER/2 + Paddle.PADDLE_WIDTH;
   	//TODO check the currentPaddleX and currentPaddleY
   	Rectangle2D.Double fullBox = new Rectangle2D.Double(currentPaddleX - Ball.BALL_DIAMETER/2, currentPaddleY - Ball.BALL_DIAMETER/2, boxLength, boxWidth);
   	double finalBallX = currentBallX + ballDirectionX;
   	double finalBallY = currentBallY + ballDirectionY;
   	boolean isHit = fullBox.intersectsLine(currentBallX, currentBallY, finalBallX, finalBallY);
   	if(isHit){
   		Line2D.Double lineBallSegment = new Line2D.Double(currentBallX, currentBallY, finalBallX, finalBallY);
   		Line2D.Double lineA = new Line2D.Double(currentPaddleX, currentPaddleY - Ball.BALL_DIAMETER/2, currentPaddleX+currentPaddleLength, currentPaddleY - Ball.BALL_DIAMETER/2);
		Line2D.Double lineB = new Line2D.Double(currentPaddleX- Ball.BALL_DIAMETER/2, currentPaddleY, currentPaddleX- Ball.BALL_DIAMETER/2, currentPaddleY + Paddle.PADDLE_WIDTH);
		Line2D.Double lineC = new Line2D.Double(currentPaddleX +currentPaddleLength+ Ball.BALL_DIAMETER/2, currentPaddleY, currentPaddleX+currentPaddleLength+ Ball.BALL_DIAMETER/2, currentPaddleY + Paddle.PADDLE_WIDTH);
		
		Arc2D.Double arcE = new Arc2D.Double(currentPaddleX - Ball.BALL_DIAMETER/2,currentPaddleY - Ball.BALL_DIAMETER/2,  Ball.BALL_DIAMETER, Ball.BALL_DIAMETER, 90, 90, Arc2D.OPEN);
		Arc2D.Double arcF = new Arc2D.Double(currentPaddleX + currentPaddleLength- Ball.BALL_DIAMETER/2,currentPaddleY - Ball.BALL_DIAMETER/2,  Ball.BALL_DIAMETER, Ball.BALL_DIAMETER, 0, 90, Arc2D.OPEN);
		
		ArrayList<BouncePoint> list = new ArrayList<BouncePoint>();
   		if(lineA.intersectsLine(lineBallSegment)){
   			Point2D.Double intersection = calculateLineIntersection(lineA, lineBallSegment);
   			list.add(new BouncePoint(intersection.getX(),intersection.getY() , BounceDirection.FLIP_Y));
   		}
   		if(lineB.intersectsLine(lineBallSegment)){
   		   	Point2D.Double intersection = calculateLineIntersection(lineB, lineBallSegment);
   			list.add(new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_X));
   		}
   		if(lineC.intersectsLine(lineBallSegment)){
   		   	Point2D.Double intersection = calculateLineIntersection(lineC, lineBallSegment);
   			list.add(new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_X));
   		}
   		if(isIntersectingALine(arcE, lineBallSegment)){
	   		Point2D.Double intersection = calculateArcIntersection(arcE, lineBallSegment);
   			if(ballDirectionX > 0){
   			
   			list.add(new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_X));
   			}
   			else{
   			list.add(new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_Y));
   			}
   		}
   		if(isIntersectingALine(arcF, lineBallSegment)){
   			Point2D.Double intersection = calculateArcIntersection(arcF, lineBallSegment);
   		   	if(ballDirectionX > 0){
   		   	
   			list.add(new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_Y));
   			}
   			else{
   			list.add(new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_X));
   			}
   		}
   		
   		double minDistance = Double.MAX_VALUE;
   		BouncePoint temp = null;
   		for(BouncePoint point: list){
   			double distance = Math.sqrt((Math.pow(point.getX()-currentBallX, 2) + Math.pow(point.getY()-currentBallY, 2)));
   			if(distance < minDistance){
   				minDistance = distance;
   				temp = point;
   			}
   		}
   		
   		return temp;
   		
   	}
   	
   	return null;
  }

  // line 132 "../../../../../Block223PlayMode.ump"
   public BouncePoint calculateBouncePointWall(){
    //left wall
   		Line2D.Double lineA = new Line2D.Double(Ball.BALL_DIAMETER/2, Ball.BALL_DIAMETER/2, Ball.BALL_DIAMETER/2, Game.PLAY_AREA_SIDE-Ball.BALL_DIAMETER/2);
   		//top wall
   		Line2D.Double lineB = new Line2D.Double(Ball.BALL_DIAMETER/2, Ball.BALL_DIAMETER/2, Game.PLAY_AREA_SIDE-Ball.BALL_DIAMETER/2, Ball.BALL_DIAMETER/2);
   		//right wall
   		Line2D.Double lineC = new Line2D.Double(Game.PLAY_AREA_SIDE-Ball.BALL_DIAMETER/2, Ball.BALL_DIAMETER/2, Game.PLAY_AREA_SIDE-Ball.BALL_DIAMETER/2, Game.PLAY_AREA_SIDE-Ball.BALL_DIAMETER/2);
   		double finalBallX = currentBallX + ballDirectionX;
   		double finalBallY = currentBallY + ballDirectionY;
   		Line2D.Double ballSegment = new Line2D.Double(currentBallX, currentBallY, finalBallX, finalBallY);
   		ArrayList<BouncePoint> list = new ArrayList<BouncePoint>();
   		
   		if(pointInLine(Ball.BALL_DIAMETER/2, Ball.BALL_DIAMETER/2, ballSegment)){
   			Point2D.Double intersections = calculateLineIntersection(lineA, ballSegment);
   			list.add(new BouncePoint(intersections.getX(), intersections.getY(), BounceDirection.FLIP_BOTH));
   		}
   		else if(pointInLine(Game.PLAY_AREA_SIDE-Ball.BALL_DIAMETER/2, Ball.BALL_DIAMETER/2, ballSegment)){
   			Point2D.Double intersections = calculateLineIntersection(lineC, ballSegment);
   			list.add(new BouncePoint(intersections.getX(), intersections.getY(), BounceDirection.FLIP_BOTH));
   		}
   		else{
   			if(lineA.intersectsLine(ballSegment)){
   				Point2D.Double intersections = calculateLineIntersection(lineA, ballSegment);
   				list.add(new BouncePoint(intersections.getX(), intersections.getY(), BounceDirection.FLIP_X));
   			}
   			else if(lineB.intersectsLine(ballSegment)){
   				Point2D.Double intersections = calculateLineIntersection(lineB, ballSegment);
   				list.add(new BouncePoint(intersections.getX(), intersections.getY(), BounceDirection.FLIP_Y));
   			}
   			else if(lineC.intersectsLine(ballSegment)){
   				Point2D.Double intersections = calculateLineIntersection(lineC, ballSegment);
   				list.add(new BouncePoint(intersections.getX(), intersections.getY(), BounceDirection.FLIP_X));
   			}
   		}
   		
   		
   		double minDistance = Double.MAX_VALUE;
   		BouncePoint temp = null;
   		for(BouncePoint point: list){
   			double distance = Math.sqrt((Math.pow(point.getX()-currentBallX, 2) + Math.pow(point.getY()-currentBallY, 2)));
   			if(distance < minDistance){
   				minDistance = distance;
   				temp = point;
   			}
   		}
   		
   		return temp;
  }

  // line 184 "../../../../../Block223PlayMode.ump"
   private Point2D.Double calculateLineIntersection(Line2D.Double l1, Line2D.Double ballSegment){
    Point2D l1p1 = l1.getP1();
   		Point2D l1p2 = l1.getP2();
   		Point2D bp1 = ballSegment.getP1();
   		Point2D bp2 = ballSegment.getP2();
   		
   		double newPointX;
	   	double newPointY;

   		if(bp1.getX() == bp2.getX()){
   			//ball segment is perpendicular line, l1 is horizonal
   			newPointX = bp1.getX();
   			newPointY = l1p1.getY();
   		}
   		else if(bp1.getY() == bp2.getY()){
   			//ball segment is horizontal, l1 is vertical
   			newPointX = l1p1.getX();
   			newPointY = bp1.getY();
   		}
   		else{
   			
   		   	double k2 = (bp1.getY()-bp2.getY())/(bp1.getX() - bp2.getX());
	   		double c2 = bp1.getY() - k2 * bp1.getX();
   		   	if(l1p1.getY() == l1p2.getY()){
   		   		//l1 is horizontal
   		   		newPointY = l1p1.getY();
   		   		newPointX = (newPointY - c2)/k2;
   		   	}else{
   		   		//l1 is perpendicular
   		   		newPointX = l1p1.getX();
   		   		newPointY = k2 * newPointX + c2;
   		   	}
   		}

   		
   		return new Point2D.Double(newPointX, newPointY);
  }

  // line 223 "../../../../../Block223PlayMode.ump"
   private Point2D.Double calculateArcIntersection(Arc2D.Double arc, Line2D.Double line){
    /*
   		Point2D lp1 = line.getP1();
   		Point2D lp2 = line.getP2();
   		
   		double dx = lp1.getX() - lp2.getX();
   		double dy = lp1.getY() - lp2.getY();
   		double al = Math.atan(dy/dx);
   		
   		double c = (arc.getX()+arc.getWidth()*Math.cos(al)-lp1.getX())/dx;
   	
   		
   		return new Point2D.Double((lp1.getX()+c*dx), lp1.getY()+c*dy);
   		*/
   		
   		//circle formula: (x - centerX)^2 + (y-centerY)^2 = r^2
   		double radius = arc.getWidth()/2;
   		double centerX = arc.getX() + arc.getWidth()/2;
   		double centerY = arc.getY() + arc.getWidth()/2;
   		
   		//line calculation
   		Point2D lp1 = line.getP1();
   		Point2D lp2 = line.getP2();
   		double k = (lp1.getY() - lp2.getY()) / (lp1.getX() - lp2.getX());
   		double c = lp1.getY() - k*lp1.getX();
   		
   		double A = k*k +1;
   		double B = 2*k*c - 2*k*centerY - 2*centerX;
   		double C = centerY*centerY - radius*radius + centerX *centerX - 2*c*centerY +c*c;
   		
   		double x = -1;
   		if(ballDirectionX>= 0){
   			x = (-B - Math.pow((B*B-4*A*C), 0.5))/(2*A);
   		}
   		else if(ballDirectionX <0){
   			x = (-B + Math.pow((B*B-4*A*C), 0.5))/(2*A);
   		}
   		
   		double y = k* x +c;
   		return new Point2D.Double(x, y);
  }

  // line 266 "../../../../../Block223PlayMode.ump"
   private boolean isIntersectingALine(Arc2D.Double arc, Line2D.Double line){
    /*
      	Point2D lp1 = line.getP1();
   		Point2D lp2 = line.getP2();
      	double dx = lp1.getX() - lp2.getX();
   		double dy = lp1.getY() - lp2.getY();
   		double al = Math.atan(dy-dx);
   		
   		if(al<startingAngle && al> endingAngle){
   			return true;
   		}
   		else{
   			return false;
   		}
   		*/
   		
   		//point2 is the new position
   		Point2D lp2 = line.getP2();
   		double radius = arc.getWidth()/2;
   		double centerX = arc.getX() + arc.getWidth()/2;
   		double centerY = arc.getY() + arc.getWidth()/2;
   		double dis = Math.pow(Math.pow((lp2.getX()-centerX), 2) + Math.pow((lp2.getY()-centerY), 2), 0.5);
   		if(dis <= radius){
   			//in the circle
   			if(arc.getAngleStart() == 0 && lp2.getX()<centerX+radius && lp2.getY()>centerY-radius){
   				return true;
   			}
   			else if(arc.getAngleStart() == 90 && lp2.getX()>centerX-radius && lp2.getY()>centerY-radius){
   				return true;
   			}
   			else if(arc.getAngleStart() == 180 && lp2.getX()>centerX-radius && lp2.getY()<centerY+radius){
   				return true;
   			}
   			else if(arc.getAngleStart() == 270 && lp2.getX()<centerX+radius && lp2.getY()<centerY+radius){
   				return true;
   			}
   		}
   		
   		return false;
  }

  // line 309 "../../../../../Block223PlayMode.ump"
   public void bounceBall(){
    if(bounce.getDirection() == BounceDirection.FLIP_X){
   			
   			//adjust x
   			double  newBallDirectionX = -ballDirectionX;
   			double incomeDistanceX = bounce.getX() - currentBallX;
   			double outgoingDistanceX = ballDirectionX - incomeDistanceX;
   			if(outgoingDistanceX ==0){
   				currentBallX = bounce.getX();
   				currentBallY = bounce.getY();
   				return;
   			}
   			
   			currentBallX = bounce.getX() + outgoingDistanceX/ballDirectionX*newBallDirectionX;

   			
   			//adjust y
   			//determine the sign
   			double sign = 0;
   			if(ballDirectionY == 0){
   				sign =1;
   			}else{
   				sign = Math.signum(ballDirectionY);
   			}
   			double newBallDirectionY = ballDirectionY + sign*0.1*Math.abs(ballDirectionX);
   			currentBallY = bounce.getY() + outgoingDistanceX/ballDirectionX*newBallDirectionY;
   			ballDirectionY = newBallDirectionY;
   			ballDirectionX = newBallDirectionX;

   		}
   		else if(bounce.getDirection() == BounceDirection.FLIP_Y){
   			
   			//flip y
   			double incomingDistanceY = bounce.getY() - currentBallY;
   			double outgoingDistanceY = ballDirectionY - incomingDistanceY;
   			double newBallDirectionY = -ballDirectionY;
   			
   			if(outgoingDistanceY ==0){
   				currentBallX = bounce.getX();
   				currentBallY = bounce.getY();
   				return;
   			}

   			currentBallY = bounce.getY() + outgoingDistanceY/ballDirectionY * newBallDirectionY;

   			//determine the sign
   			double sign = 0;
   			if(ballDirectionX == 0){
   				sign =1;
   			}else{
   				sign = Math.signum(ballDirectionX);
   			}
   			
   			//adjust x
   			double newBallDirectionX  =ballDirectionX + sign*0.1*Math.abs(ballDirectionY);
   			currentBallX = bounce.getX()+outgoingDistanceY/ballDirectionY * newBallDirectionX;
   			ballDirectionX = newBallDirectionX;
   			ballDirectionY = newBallDirectionY;
   		}
   		else if(bounce.getDirection() == BounceDirection.FLIP_BOTH){
   		
   			
   			double newBallDirectionX = -ballDirectionX;
   			double newBallDirectionY = -ballDirectionY;
   			
   			//adjust x and y
   			double incomingDistanceY = bounce.getY() - currentBallY;
   			double outgoingDistanceY = ballDirectionY - incomingDistanceY;
   			double incomingDistanceX = bounce.getX() - currentBallX;
   			double outgoingDistanceX = ballDirectionX - incomingDistanceX;
   			
   			if(outgoingDistanceX ==0 && outgoingDistanceY ==0){
   				currentBallX = bounce.getX();
   				currentBallY = bounce.getY();
   				return;
   			}
   			
   			currentBallY = bounce.getY() + outgoingDistanceY/ballDirectionY * newBallDirectionY;
   			ballDirectionY = newBallDirectionY;
   			

   			currentBallX = bounce.getX() + outgoingDistanceX/ballDirectionX * newBallDirectionX;
   			ballDirectionX = newBallDirectionX;
   		}
   		
    if(ballDirectionX>10) {
      ballDirectionX = ballDirectionX/10;
    }
    if(ballDirectionY>10) {
      ballDirectionY = ballDirectionY/10;
    }
    
  if(currentBallX<Ball.BALL_DIAMETER/2 || currentBallX> Game.PLAY_AREA_SIDE -Ball.BALL_DIAMETER/2){
  	ballDirectionX  = -ballDirectionX;
  	if(currentBallX<Ball.BALL_DIAMETER/2){
  		currentBallX = Ball.BALL_DIAMETER/2+0.1;
  	}
  	else if(currentBallX> Game.PLAY_AREA_SIDE -Ball.BALL_DIAMETER/2){
  		currentBallX = Game.PLAY_AREA_SIDE -Ball.BALL_DIAMETER/2 -0.1;
  	}
  }
  if(currentBallY <Ball.BALL_DIAMETER/2){
  	ballDirectionY = -ballDirectionY;
  	currentBallY = Ball.BALL_DIAMETER/2+0.1;
  }
  }

  // line 420 "../../../../../Block223PlayMode.ump"
   public boolean isCloser(BouncePoint first, BouncePoint second){
    if (first == null){
   	  	return false;
	}
	if (second == null ){
    	return true;
   	}
	double firstLine = Math.sqrt((Math.pow(first.getX()-currentBallX, 2) + Math.pow(first.getY()-
	currentBallY, 2)));
	double secondLine = Math.sqrt((Math.pow(second.getX()-currentBallX, 2) + Math.pow(second.getY()-
	currentBallY, 2)));
	if (firstLine < secondLine){
		return true;
	}
   	return false;
  }

  // line 437 "../../../../../Block223PlayMode.ump"
   public BouncePoint calculateBouncePointBlock(PlayedBlockAssignment blockAssignment){
    double radius = Ball.BALL_DIAMETER/2;
   double boxLength = Ball.BALL_DIAMETER + Block.SIZE;
   double boxWidth = boxLength;
   double blockX = blockAssignment.getX();
   double blockY = blockAssignment.getY();
   double boxX = blockX - radius;
   double boxY = blockY - radius;
   Rectangle2D.Double fullBox = new Rectangle2D.Double(boxX,boxY,boxWidth,boxLength);
   double finalBallX = currentBallX + ballDirectionX;
   double finalBallY = currentBallY + ballDirectionY;
   boolean isHit = fullBox.intersectsLine(currentBallX, currentBallY, finalBallX, finalBallY);
   
   if(isHit){
    Line2D.Double lineBallSegment = new Line2D.Double(currentBallX, currentBallY, finalBallX, finalBallY);
    Line2D.Double lineA = new Line2D.Double(blockX, blockY-radius, blockX+Block.SIZE, blockY-radius);
    Line2D.Double lineB = new Line2D.Double(blockX-radius, blockY, blockX-radius, blockY+Block.SIZE);
    Line2D.Double lineC = new Line2D.Double(blockX+Block.SIZE+radius, blockY, blockX+Block.SIZE+radius, blockY+Block.SIZE);
    Line2D.Double lineD = new Line2D.Double(blockX, blockY+Block.SIZE+radius, blockX+Block.SIZE, blockY+Block.SIZE+radius);
    
    Arc2D.Double arcE = new Arc2D.Double(boxX, boxY, Ball.BALL_DIAMETER, Ball.BALL_DIAMETER,90,90,Arc2D.OPEN);
    Arc2D.Double arcF = new Arc2D.Double(boxX+Block.SIZE, boxY, Ball.BALL_DIAMETER, Ball.BALL_DIAMETER,0,90,Arc2D.OPEN);
    Arc2D.Double arcG = new Arc2D.Double(boxX, boxY+Block.SIZE, Ball.BALL_DIAMETER, Ball.BALL_DIAMETER,180,90,Arc2D.OPEN);
    Arc2D.Double arcH = new Arc2D.Double(boxX+Block.SIZE, boxY+Block.SIZE, Ball.BALL_DIAMETER, Ball.BALL_DIAMETER,270,90,Arc2D.OPEN);
    
    ArrayList<BouncePoint> list = new ArrayList<BouncePoint>();
    
    if(lineA.intersectsLine(lineBallSegment)) {
     Point2D.Double intersection = calculateLineIntersection(lineA,lineBallSegment);
     list.add(new BouncePoint(intersection.getX(),intersection.getY(), BouncePoint.BounceDirection.FLIP_Y));  
    }
    
    if(lineB.intersectsLine(lineBallSegment)){
     Point2D.Double intersection = calculateLineIntersection(lineB,lineBallSegment);
     list.add(new BouncePoint(intersection.getX(),intersection.getY(), BouncePoint.BounceDirection.FLIP_X));  
    }
    
    if(lineC.intersectsLine(lineBallSegment)){
     Point2D.Double intersection = calculateLineIntersection(lineC,lineBallSegment);
     list.add(new BouncePoint(intersection.getX(),intersection.getY(), BouncePoint.BounceDirection.FLIP_X));  
    }
    
    if(lineD.intersectsLine(lineBallSegment)){
     Point2D.Double intersection = calculateLineIntersection(lineD,lineBallSegment);
     list.add(new BouncePoint(intersection.getX(),intersection.getY(), BouncePoint.BounceDirection.FLIP_Y));  
    }
    
    if(isIntersectingALine(arcE, lineBallSegment)){
    	Point2D.Double intersection = calculateArcIntersection(arcE, lineBallSegment);
    	if(ballDirectionX < 0){
    		list.add(new BouncePoint(intersection.getX(),intersection.getY(), BouncePoint.BounceDirection.FLIP_Y));  
    	}
    	else{
    		list.add(new BouncePoint(intersection.getX(),intersection.getY(), BouncePoint.BounceDirection.FLIP_X));  
    	}
    }
    
    if(isIntersectingALine(arcF, lineBallSegment)){
    	Point2D.Double intersection = calculateArcIntersection(arcF, lineBallSegment);
    	if(ballDirectionX < 0){
    		list.add(new BouncePoint(intersection.getX(),intersection.getY(), BouncePoint.BounceDirection.FLIP_X));  
    	}
    	else{
    		list.add(new BouncePoint(intersection.getX(),intersection.getY(), BouncePoint.BounceDirection.FLIP_Y));  
    	}
    }
    
    if(isIntersectingALine(arcG, lineBallSegment)){
    	Point2D.Double intersection = calculateArcIntersection(arcG, lineBallSegment);
    	if(ballDirectionX < 0){
    		list.add(new BouncePoint(intersection.getX(),intersection.getY(), BouncePoint.BounceDirection.FLIP_Y));  
    	}
    	else{
    		list.add(new BouncePoint(intersection.getX(),intersection.getY(), BouncePoint.BounceDirection.FLIP_X));  
    	}
    }
    
    if(isIntersectingALine(arcH, lineBallSegment)){
    	Point2D.Double intersection = calculateArcIntersection(arcH, lineBallSegment);
    	if(ballDirectionX < 0){
    		list.add(new BouncePoint(intersection.getX(),intersection.getY(), BouncePoint.BounceDirection.FLIP_X));  
    	}
    	else{
    		list.add(new BouncePoint(intersection.getX(),intersection.getY(), BouncePoint.BounceDirection.FLIP_Y));  
    	}
    }
    
    double minDistance = Double.MAX_VALUE;
    BouncePoint temp = null;
    for(BouncePoint point: list){
    	double distance = Math.sqrt((Math.pow(point.getX()-currentBallX, 2) + Math.pow(point.getY()-currentBallY, 2)));
    	if(distance < minDistance){
    		minDistance = distance;
    		temp = point;
    	} 
    }
    
    if (temp != null){
    	if(temp.getDirection() == BounceDirection.FLIP_X){
    		double incomeDistanceX = temp.getX() - currentBallX;
			double outgoingDistanceX = ballDirectionX - incomeDistanceX;
			if(outgoingDistanceX == 0){
				temp = null;
			}
		}		
    	else if(temp.getDirection() == BounceDirection.FLIP_Y){
			double incomingDistanceY = temp.getY() - currentBallY;
			double outgoingDistanceY = ballDirectionY - incomingDistanceY;	
				if(outgoingDistanceY == 0){
					temp = null;
			}
    	}
    }
    if (temp != null){
   		 temp.setHitBlock(blockAssignment);
    }
    return temp;
   }
   return null;
  }

  // line 559 "../../../../../Block223PlayMode.ump"
   private boolean pointInLine(double x, double y, Line2D.Double line){
    double k = (line.getY2() - line.getY1())/(line.getX2() - line.getX1());
  	double c = line.getY2() - line.getX2()*k;
  	if(Math.abs(y - (x*k + c))<0.00001 && x>=Math.min(line.getX1(), line.getX2()) && x<=Math.max(line.getX1(), line.getX2())){
  		return true;
  	}
  	return false;
  }


  /**
   * Guards
   */
  // line 34 "../../../../../Block223States.ump"
   private boolean hitPaddle(){
    BouncePoint bp = calculateBouncePointPaddle();
	setBounce(bp);
    return bp!=null;
  }

  // line 41 "../../../../../Block223States.ump"
   private boolean isOutOfBoundsAndLastLife(){
    boolean outOfBounds = false;
  	int lives = this.getLives();
  	if(lives==1){
  	outOfBounds=this.isBallOutOfBounds();
  	} 
    // TODO implement
    return outOfBounds;
  }

  // line 51 "../../../../../Block223States.ump"
   private boolean isBallOutOfBounds(){
    boolean isBallOutOfBounds = false;
  	isBallOutOfBounds=this.getCurrentBallY()>(Game.PLAY_AREA_SIDE-(Ball.BALL_DIAMETER)/2);
  	return isBallOutOfBounds;
  }

  // line 57 "../../../../../Block223States.ump"
   private boolean isOutOfBounds(){
    // TODO implement
    boolean isOutOfBounds=this.isBallOutOfBounds();
    return isOutOfBounds;
  }

  // line 63 "../../../../../Block223States.ump"
   private boolean hitLastBlockAndLastLevel(){
    // TODO implement
    Game game = this.getGame();
    int nrLevels = game.numberOfLevels();
    setBounce(null);
    if (nrLevels == currentLevel){
    	int nrBlocks = numberOfBlocks();
   	    if(nrBlocks == 1){
     		 PlayedBlockAssignment block = getBlock(0);
    		 BouncePoint bp = calculateBouncePointBlock(block);
    		 setBounce(bp);
      		 return (bp!=null);
      	}
    }
    return false;
  }

  // line 80 "../../../../../Block223States.ump"
   private boolean hitLastBlock(){
    // TODO implement
    int nrBlocks = this.numberOfBlocks();
    setBounce(null);
    if(nrBlocks == 1){
    	PlayedBlockAssignment block = getBlock(0);
    	BouncePoint bp = calculateBouncePointBlock(block);
     	setBounce(bp);
   	    return (bp!=null);
    }
    return false;
  }

  // line 93 "../../../../../Block223States.ump"
   private boolean hitBlock(){
    // TODO implement
    int nrBlocks = this.numberOfBlocks();
    setBounce(null);
    for (int i=0; i <= (nrBlocks-1); i++){
    	PlayedBlockAssignment block = getBlock(i);
    	BouncePoint bp = calculateBouncePointBlock(block);
    	BouncePoint bounce = getBounce();
    	boolean closer = isCloser(bp,bounce);
    	if (closer){
			setBounce(bp);
		}
    }
    return (getBounce()!=null);
  }

  // line 109 "../../../../../Block223States.ump"
   private boolean hitWall(){
    BouncePoint bp = calculateBouncePointWall();
    setBounce(bp);
    return bp!=null;
  }


  /**
   * Actions
   */
  // line 120 "../../../../../Block223States.ump"
   private void doSetup(){
    resetCurrentBallX();
    resetCurrentBallY();
    resetBallDirectionX();
    resetBallDirectionY();
    resetCurrentPaddleX();
    Game game = getGame();
    Level level = game.getLevel(currentLevel-1);
    List<BlockAssignment> assignments = level.getBlockAssignments();
    for(BlockAssignment a: assignments){
    	PlayedBlockAssignment pblock = new PlayedBlockAssignment(Game.WALL_PADDING+(Block.SIZE+Game.COLUMNS_PADDING)*(a.getGridHorizontalPosition()-1),Game.WALL_PADDING+(Block.SIZE +Game.ROW_PADDING)*(a.getGridVerticalPosition()-1),a.getBlock(),this);
    }
    while(numberOfBlocks()<game.getNrBlocksPerLevel()){
    	Random r1 = new Random();
    	Random r2 = new Random();
    	int x = Game.WALL_PADDING + (Block.SIZE + Game.COLUMNS_PADDING)*(Math.abs(r1.nextInt())%((Game.PLAY_AREA_SIDE - 2*Game.WALL_PADDING - Game.COLUMNS_PADDING)/(Block.SIZE+Game.COLUMNS_PADDING)));
    	int y = Game.WALL_PADDING + (Block.SIZE + Game.ROW_PADDING)*(Math.abs(r2.nextInt())%5);
    	boolean occupied = false;
    	for(PlayedBlockAssignment assignment:this.getBlocks()){
    		if(assignment.getX() == x && assignment.getY() == y){
    			occupied = true;
    		}
    	}
    	if(!occupied) {
    		PlayedBlockAssignment pblock = new PlayedBlockAssignment(x,y,game.getRandomBlock(),this);
    	}
    }
  }

  // line 149 "../../../../../Block223States.ump"
   private void doHitPaddleOrWall(){
    bounceBall();
  }

  // line 153 "../../../../../Block223States.ump"
   private void doOutOfBounds(){
    // TODO implement
    this.setLives(this.getLives()-1);
    this.resetCurrentBallX();
    this.resetCurrentBallY();
    this.resetBallDirectionX();
    this.resetBallDirectionY();
    this.resetCurrentPaddleX();
  }

  // line 163 "../../../../../Block223States.ump"
   private void doHitBlock(){
    // TODO implement
    int score = getScore();
    BouncePoint bounce = getBounce();
    PlayedBlockAssignment pblock = bounce.getHitBlock();
	Block block = pblock.getBlock();
    int points = block.getPoints();
   	setScore(score + points);
 	pblock.delete();
    bounceBall();
  }

  // line 176 "../../../../../Block223States.ump"
   private void doHitBlockNextLevel(){
    // TODO implement
    doHitBlock();
    int level = getCurrentLevel();
    setCurrentLevel(level+1);
    setCurrentPaddleLength(getGame().getPaddle().getMaxPaddleLength()-
    (getGame().getPaddle().getMaxPaddleLength()-getGame().getPaddle().getMinPaddleLength())/
    (getGame().numberOfLevels()-1)*(getCurrentLevel()-1));
    setWaitTime(INITIAL_WAIT_TIME *
    Math.pow(getGame().getBall().getBallSpeedIncreaseFactor(),(getCurrentLevel()-1)));
  }

  // line 188 "../../../../../Block223States.ump"
   private void doHitNothingAndNotOutOfBounds(){
    double x = currentBallX;
    double y = currentBallY;
    
    double dx = ballDirectionX;
    double dy = ballDirectionY;
    
    currentBallX = x + dx;
    currentBallY = y + dy;
  }

  // line 199 "../../../../../Block223States.ump"
   private void doGameOver(){
    // TODO implement
    Block223 block223=this.getBlock223();
    Player p=this.getPlayer();
    if(p!=null){
    Game game=this.getGame();
    HallOfFameEntry hof = new HallOfFameEntry(this.getScore(),this.getPlayername(),p,game,block223);
    game.setMostRecentEntry(hof);
    }
    this.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "score" + ":" + getScore()+ "," +
            "lives" + ":" + getLives()+ "," +
            "currentLevel" + ":" + getCurrentLevel()+ "," +
            "waitTime" + ":" + getWaitTime()+ "," +
            "playername" + ":" + getPlayername()+ "," +
            "ballDirectionX" + ":" + getBallDirectionX()+ "," +
            "ballDirectionY" + ":" + getBallDirectionY()+ "," +
            "currentBallX" + ":" + getCurrentBallX()+ "," +
            "currentBallY" + ":" + getCurrentBallY()+ "," +
            "currentPaddleLength" + ":" + getCurrentPaddleLength()+ "," +
            "currentPaddleX" + ":" + getCurrentPaddleX()+ "," +
            "currentPaddleY" + ":" + getCurrentPaddleY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "bounce = "+(getBounce()!=null?Integer.toHexString(System.identityHashCode(getBounce())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 115 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 8597675110221231714L ;

  
}