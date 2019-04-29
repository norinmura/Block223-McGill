package ca.mcgill.ecse223.block.test;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.model.*;

public class p8PublishGameTest {

  Block223 block223;
  Admin admin;
  Game game;
  Block block;

  @Before
  public void beforeClass() {
    block223 = new Block223();
    admin = new Admin("1234", block223);
    game = new Game("g1", 100, admin, 20, 20, 10, 100, 50, block223);
    block = new Block(10, 10, 20, 30, game);
    Block223Application.setCurrentGame(null);
    Block223Application.setCurrentUserRole(null);
  }

  @Test
  public void test() {
    String errorMessage = null;

    Block223Application.setCurrentGame(game);
    Block223Application.setCurrentUserRole(admin);

    try {
      Block223Controller.publishGame();
    } catch (InvalidInputException e) {
      errorMessage = e.toString();
    }

    assertEquals(game.getPublished(), true);
  }

  @Test
  public void noCurrentGameTest() {
    String errorMessage = null;

    Block223Application.setCurrentUserRole(admin);

    try {
      Block223Controller.publishGame();
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
    }

    assertEquals("A game must be selected to publish it.", errorMessage);

  }

  @Test
  public void noCurrentAdmin() {
    String errorMessage = null;

    Block223Application.setCurrentGame(game);

    try {
      Block223Controller.publishGame();
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
      e.printStackTrace();
    }

    assertEquals("Admin privileges are required to publish a game.", errorMessage);
  }
  
  @Test
  public void noAdminAndGame() {
    String errorMessage = null;

    try {
      Block223Controller.publishGame();
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
    }

    assertEquals("Admin privileges are required to publish a game.", errorMessage);
  }
  
  @Test
  public void adminDoesNotMatch() {
    String errorMessage = null;
    
    Admin a2 = new Admin("567", block223);
    
    Block223Application.setCurrentGame(game);
    Block223Application.setCurrentUserRole(a2);

    try {
      Block223Controller.publishGame();
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
    }

    assertEquals("Only the admin who created the game can publish it.", errorMessage);
  }
  
  @Test
  public void noBlockinGame() {
    String errorMessage = null;
    
    Block223Application.setCurrentGame(game);
    Block223Application.setCurrentUserRole(admin);

    for (Block aBlock:Block223Application.getCurrentGame().getBlocks()) {
      Block223Application.getCurrentGame().removeBlock(aBlock);
    }
    
    try {
      Block223Controller.publishGame();
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
    }

    assertEquals("At least one block must be defined for a game to be published.", errorMessage);
  }
 
}
