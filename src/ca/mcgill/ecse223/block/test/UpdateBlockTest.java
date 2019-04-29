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

public class UpdateBlockTest {

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

    int red = 100;
    int green = 100;
    int blue = 50;
    int points = 50;
    try {
      Block223Controller.updateBlock(game.getBlock(0).getId(), red, green, blue, points);
    } catch (InvalidInputException e) {
      errorMessage = e.toString();
    }

    assertEquals(null, errorMessage);

    Block temp = game.getBlock(0);

    assertEquals(red, temp.getRed());
    assertEquals(green, temp.getGreen());
    assertEquals(blue, temp.getBlue());
    assertEquals(points, temp.getPoints());

  }

  @Test
  public void noCurrentGameTest() {
    String errorMessage = null;

    Block223Application.setCurrentUserRole(admin);

    int red = 100;
    int green = 100;
    int blue = 50;
    int points = 50;
    try {
      Block223Controller.updateBlock(game.getBlock(0).getId(), red, green, blue, points);
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
    }

    assertEquals("A game must be selected to update a block.", errorMessage);

  }

  @Test
  public void noCurrentAdmin() {
    String errorMessage = null;

    Block223Application.setCurrentGame(game);

    int red = 100;
    int green = 100;
    int blue = 50;
    int points = 50;
    try {
      Block223Controller.updateBlock(game.getBlock(0).getId(), red, green, blue, points);
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
    }

    assertEquals("Admin privileges are required to update a block.", errorMessage);
  }
  
  @Test
  public void noAdminAndGame() {
    String errorMessage = null;

    int red = 100;
    int green = 100;
    int blue = 50;
    int points = 50;
    try {
      Block223Controller.updateBlock(game.getBlock(0).getId(), red, green, blue, points);
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
    }

    assertEquals("Admin privileges are required to update a block.", errorMessage);
  }
  
  @Test
  public void adminDoesNotMatch() {
    String errorMessage = null;
    
    Admin a2 = new Admin("567", block223);
    
    Block223Application.setCurrentGame(game);
    Block223Application.setCurrentUserRole(a2);

    int red = 100;
    int green = 100;
    int blue = 50;
    int points = 50;
    try {
      Block223Controller.updateBlock(game.getBlock(0).getId(), red, green, blue, points);
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
    }

    assertEquals("Only the admin who created the game can update a block.", errorMessage);
  }
  
  @Test
  public void redOutofBound1() {
    String errorMessage = null;
    
    Block223Application.setCurrentGame(game);
    Block223Application.setCurrentUserRole(admin);

    int red = 256;
    int green = 100;
    int blue = 50;
    int points = 50;
    try {
      Block223Controller.updateBlock(game.getBlock(0).getId(), red, green, blue, points);
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
    }

    assertEquals("Red must be between 0 and 255.", errorMessage);
  }
  
  @Test
  public void redOutofBound2() {
    String errorMessage = null;
    
    Block223Application.setCurrentGame(game);
    Block223Application.setCurrentUserRole(admin);

    int red = -20;
    int green = 100;
    int blue = 50;
    int points = 50;
    try {
      Block223Controller.updateBlock(game.getBlock(0).getId(), red, green, blue, points);
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
    }

    assertEquals("Red must be between 0 and 255.", errorMessage);
  }
  
  @Test
  public void blockNotExist() {
    String errorMessage = null;
    
    Block223Application.setCurrentGame(game);
    Block223Application.setCurrentUserRole(admin);

    int red = 256;
    int green = 100;
    int blue = 50;
    int points = 50;
    try {
      Block223Controller.updateBlock(20, red, green, blue, points);
    } catch (InvalidInputException e) {
      errorMessage = e.getMessage();
    }

    assertEquals("The block does not exist.", errorMessage);
  }
}
