package ca.mcgill.ecse223.block.test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Level;

public class PositionBlockInLevelTest {

  Block223 block223;
  Admin admin;
  Game game;
  Block block;
  Level l1;
  Level l2;

  @Before
  public void beforeTest() {
    block223 = new Block223();
    admin = new Admin("1234", block223);
    game = new Game("g1", 100, admin, 20, 20, 10, 100, 50, block223);
    l1 = game.addLevel();
    l2 = game.addLevel();
    block = new Block(10, 10, 20, 30, game);
    Block223Application.setCurrentGame(null);
    Block223Application.setCurrentUserRole(null);
  }

  @Test
  public void test() {

    String errorMessage1 = null;
    String errorMessage2 = null;

    Block223Application.setCurrentGame(game);
    Block223Application.setCurrentUserRole(admin);

    try {
      Block223Controller.positionBlock(game.getBlock(0).getId(), 1, 10, 1);
    } catch (InvalidInputException e) {
      // TODO Auto-generated catch block
      errorMessage1 = e.getMessage();
    }

    assertEquals(null, errorMessage1);

    assertEquals(1, game.getBlockAssignments().size());
    assertEquals(10, game.getBlockAssignment(0).getGridHorizontalPosition());
    assertEquals(1, game.getBlockAssignment(0).getGridVerticalPosition());
    assertEquals(block, game.getBlockAssignment(0).getBlock());
    assertEquals(l1, game.getBlockAssignment(0).getLevel());

    try {
      Block223Controller.positionBlock(game.getBlock(0).getId(), 2, 10, 20);
    } catch (InvalidInputException e) {
      errorMessage2 = e.getMessage();
    }

    assertEquals(null, errorMessage2);

    assertEquals(2, game.getBlockAssignments().size());
    assertEquals(10, game.getBlockAssignment(1).getGridHorizontalPosition());
    assertEquals(20, game.getBlockAssignment(1).getGridVerticalPosition());
    assertEquals(block, game.getBlockAssignment(1).getBlock());
    assertEquals(l2, game.getBlockAssignment(1).getLevel());

  }

  @Test
  public void levelNotExist() {
    String errorMessage = null;

    Block223Application.setCurrentGame(game);
    Block223Application.setCurrentUserRole(admin);

    try {
      Block223Controller.positionBlock(game.getBlock(0).getId(), 3, 10, 1);
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
    }

    assertEquals("Level" + 3 + " does not exist for the game", errorMessage);

  }

  @Test
  public void numberOfBlockTest() {
    String errorMessage = null;

    game = new Game("g1", 1, admin, 20, 20, 10, 100, 50, block223);
    l1 = game.addLevel();
    l2 = game.addLevel();
    block = new Block(10, 10, 20, 30, game);

    Block223Application.setCurrentGame(game);
    Block223Application.setCurrentUserRole(admin);

    try {
      Block223Controller.positionBlock(game.getBlock(0).getId(), 1, 10, 1);
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
    }

    assertEquals(null, errorMessage);

    try {
      Block223Controller.positionBlock(game.getBlock(0).getId(), 1, 10, 20);
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
    }

    assertEquals("The number of blocks has reached the maximum number ("
        + game.getNrBlocksPerLevel() + ") allowed for this game.", errorMessage);

  }

  @Test
  public void blockExisted() {
    String errorMessage = null;

    Block223Application.setCurrentGame(game);
    Block223Application.setCurrentUserRole(admin);

    try {
      Block223Controller.positionBlock(game.getBlock(0).getId(), 1, 10, 1);
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
    }

    assertEquals(null, errorMessage);

    try {
      Block223Controller.positionBlock(game.getBlock(0).getId(), 1, 10, 1);
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
    }

    assertEquals("A block already exists at location"
        + 10 + "/" +
        1 + ".", errorMessage);
  }

  @Test
  public void xTest1() {
    String errorMessage = null;

    Block223Application.setCurrentGame(game);
    Block223Application.setCurrentUserRole(admin);

    try {
      Block223Controller.positionBlock(game.getBlock(0).getId(), 1, 0, 1);
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
    }

    assertEquals("The horizontal position must be between 1 and " +
        (Game.PLAY_AREA_SIDE/Block.SIZE) +
       ".", errorMessage);
  }
  
  @Test
  public void xTest2() {
    String errorMessage = null;

    Block223Application.setCurrentGame(game);
    Block223Application.setCurrentUserRole(admin);

    try {
      Block223Controller.positionBlock(game.getBlock(0).getId(), 1, 21, 1);
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
    }

    assertEquals("The horizontal position must be between 1 and " +
        (Game.PLAY_AREA_SIDE/Block.SIZE) +
       ".", errorMessage);
  }
  
  @Test
  public void yTest1() {
    String errorMessage = null;

    Block223Application.setCurrentGame(game);
    Block223Application.setCurrentUserRole(admin);

    try {
      Block223Controller.positionBlock(game.getBlock(0).getId(), 1, 20, 0);
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
    }

    assertEquals(
        "The vertical position must be between 1 and " +
            (Game.PLAY_AREA_SIDE/Block.SIZE) + ".", errorMessage);
  }
  
  @Test
  public void yTest2() {
    String errorMessage = null;

    Block223Application.setCurrentGame(game);
    Block223Application.setCurrentUserRole(admin);

    try {
      Block223Controller.positionBlock(game.getBlock(0).getId(), 1, 20, 30);
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
    }

    assertEquals(
        "The vertical position must be between 1 and " +
            (Game.PLAY_AREA_SIDE/Block.SIZE) + ".", errorMessage);
  }
  
}
