package ca.mcgill.ecse223.block.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.model.User;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;

public class Feature9Test {

  private Block223 block223;
  private Admin testAdmin;
  private Player testPlayer;
  private Game testGame;
  private Block testBlock;
  private Block testBlock2;
  private Level testLevel;


  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Before
  public void doBefore() {
    /*
     * 1 x Block223
     * 1 x Admin
     * 1 x Game
     * 2 x Block
     * 1 x Level
     */
    block223 = Block223Application.getBlock223();
    testAdmin = new Admin("123", block223);
    testPlayer = new Player("123",block223);
    testGame = new Game("TestGame", 3, testAdmin, 0, 0, 0, 0, 0, block223);
    testBlock = new Block(0, 0, 0, 0, testGame);
    testBlock2 = new Block(0, 0, 0, 0, testGame);
    testLevel = testGame.addLevel();
  }

  /*
   * Testing Admin Privilege validation
   */
  @Test
  public void MoveBlockAdminErrorTest() throws InvalidInputException {
    // Expected Exception
    expectedException.expect(InvalidInputException.class);
    // Expected Message
    expectedException.expectMessage("Admin privilegees are required to move a block.");

    // set currentGame and currentUserRole
    Block223Application.setCurrentGame(testGame);
    Block223Application.setCurrentUserRole(testPlayer);

    // Test if the level is correct
    int index = testGame.indexOfLevel(testLevel);
    assertEquals(index,0);

    // Add a block to grid 1,1
    Level thisLevel = testGame.getLevel(index);
    thisLevel.addBlockAssignment(1, 1, testBlock, testGame);

    assertEquals(testGame.getLevel(index).getBlockAssignments().size(),1);
    assertEquals(testGame.getLevel(index).getBlockAssignments().get(0).getGridHorizontalPosition(),1);
    assertEquals(testGame.getLevel(index).getBlockAssignments().get(0).getGridVerticalPosition(),1);

    // Add another block to grid 2,2
    testGame.getLevel(index).addBlockAssignment(2, 2, testBlock2, testGame);
    assertEquals(testGame.getLevel(index).getBlockAssignments().size(),2);
    assertEquals(testGame.getLevel(index).getBlockAssignments().get(1).getGridHorizontalPosition(),2);
    assertEquals(testGame.getLevel(index).getBlockAssignments().get(1).getGridVerticalPosition(),2);

    // Move from 1,1 to 3,3
    Block223Controller.moveBlock(1, 1, 1, 3, 3);    // Throws InvalidInputException
  }

  /*
   * Testing currentGame != null validation
   */
  @Test
  public void MoveBlockCurrentGameNullTest() throws InvalidInputException {
    // Expected Exception
    expectedException.expect(InvalidInputException.class);
    // Expected Message
    expectedException.expectMessage("A game must be selected to move a block.");

    // set currentGame and currentUserRole
    Block223Application.setCurrentGame(null);
    Block223Application.setCurrentUserRole(testAdmin);

    // Test if the level is correct
    int index = testGame.indexOfLevel(testLevel);
    assertEquals(index,0);

    // Add a block to grid 1,1
    testGame.getLevel(index).addBlockAssignment(1, 1, testBlock, testGame);

    assertEquals(testGame.getLevel(0).getBlockAssignments().size(),1);
    assertEquals(testGame.getLevel(0).getBlockAssignments().get(0).getGridHorizontalPosition(),1);
    assertEquals(testGame.getLevel(0).getBlockAssignments().get(0).getGridVerticalPosition(),1);

    // Move from 1,1 to 3,3
    Block223Controller.moveBlock(1, 1, 1, 3, 3);    // Throws InvalidInputException
  }

  /*
   * Testing Author validation
   */
  @Test
  public void MoveBlockCurrentAdminMismatchTest() throws InvalidInputException {
    // Expected Exception
    expectedException.expect(InvalidInputException.class);
    // Expected Message
    expectedException.expectMessage("Only the admin who created the game can move a block.");
    
    // Creating another admin
    Admin admin2 = new Admin("abc", block223);

    // set currentGame and currentUserRole
    Block223Application.setCurrentGame(testGame);
    Block223Application.setCurrentUserRole(admin2);

    // Test if the level is correct
    int index = testGame.indexOfLevel(testLevel);
    assertEquals(index,0);

    // Add a block to grid 1,1
    testGame.getLevel(index).addBlockAssignment(1, 1, testBlock, testGame);

    assertEquals(testGame.getLevel(0).getBlockAssignments().size(),1);
    assertEquals(testGame.getLevel(0).getBlockAssignments().get(0).getGridHorizontalPosition(),1);
    assertEquals(testGame.getLevel(0).getBlockAssignments().get(0).getGridVerticalPosition(),1);

    // Add another block to grid 2,2
    testGame.getLevel(0).addBlockAssignment(2, 2, testBlock2, testGame);
    assertEquals(testGame.getLevel(0).getBlockAssignments().size(),2);
    assertEquals(testGame.getLevel(0).getBlockAssignments().get(1).getGridHorizontalPosition(),2);
    assertEquals(testGame.getLevel(0).getBlockAssignments().get(1).getGridVerticalPosition(),2);

    // Move from 1,1 to 3,3
    Block223Controller.moveBlock(1, 1, 1, 3, 3);    // Throws InvalidInputException
  }

  /*
   * Testing level number validation
   */
  @Test
  public void MoveBlockWrongLevelTest() throws InvalidInputException {
    // Expected Exception
    expectedException.expect(InvalidInputException.class);
    // Expected Message
    int random = (int) Math.random() * 100 + 5;
    expectedException.expectMessage("Level "+ random + " does not exist for the game.");
    
    // set currentGame and currentUserRole
    Block223Application.setCurrentGame(testGame);
    Block223Application.setCurrentUserRole(testAdmin);

    // Test if the level is correct
    int index = testGame.indexOfLevel(testLevel);
    assertEquals(index,0);

    // Add a block to grid 1,1
    testGame.getLevel(index).addBlockAssignment(1, 1, testBlock, testGame);
    
    // Give a wrong level
    Block223Controller.moveBlock(random, 1, 1, 3, 3);
  }
  
  @Test
  public void MoveBlockNegativeLevelTest() throws InvalidInputException {
    // Expected Exception
    expectedException.expect(InvalidInputException.class);
    
    // Expected Message
    int random = (int) -Math.random() * 100 + 5;
    expectedException.expectMessage("Level "+ random + " does not exist for the game.");
    
    // set currentGame and currentUserRole
    Block223Application.setCurrentGame(testGame);
    Block223Application.setCurrentUserRole(testAdmin);

    // Test if the level is correct
    int index = testGame.indexOfLevel(testLevel);
    assertEquals(index,0);

    // Add a block to grid 1,1
    testGame.getLevel(index).addBlockAssignment(1, 1, testBlock, testGame);
    
    // Give a wrong level
    Block223Controller.moveBlock(random, 1, 1, 3, 3);
  }
  
  /*
   * Moving block to occupied grid test
   */
  @Test
  public void MoveBlockToOccupiedGridTest() throws InvalidInputException {
    // Expected Exception
    expectedException.expect(InvalidInputException.class);
    // Expected Message
    expectedException.expectMessage("A block already exists at location 2/2");
    
    // Set Current
    Block223Application.setCurrentGame(testGame);
    Block223Application.setCurrentUserRole(testAdmin);

    // Test if the level is correct
    int index = testGame.indexOfLevel(testLevel);
    assertEquals(index,0);

    // Add a block to grid 1,1
    testGame.getLevel(index).addBlockAssignment(1, 1, testBlock, testGame);

    assertEquals(testGame.getLevel(0).getBlockAssignments().size(),1);
    assertEquals(testGame.getLevel(0).getBlockAssignments().get(0).getGridHorizontalPosition(),1);
    assertEquals(testGame.getLevel(0).getBlockAssignments().get(0).getGridVerticalPosition(),1);

    // Add another block to grid 2,2
    Block testBlock2 = new Block(0, 0, 0, 0, testGame);
    testGame.getLevel(0).addBlockAssignment(2, 2, testBlock2, testGame);
    assertEquals(testGame.getLevel(0).getBlockAssignments().size(),2);
    assertEquals(testGame.getLevel(0).getBlockAssignments().get(1).getGridHorizontalPosition(),2);
    assertEquals(testGame.getLevel(0).getBlockAssignments().get(1).getGridVerticalPosition(),2);

    // Move from 1,1 to 2,2
    Block223Controller.moveBlock(1, 1, 1, 2, 2);
  }
  
  /*
   * Setting invalid grid test
   */
  @Test
  public void MoveBlockToInvalidGridTest() throws InvalidInputException, RuntimeException {
    // Expected Exception
    expectedException.expect(RuntimeException.class);
 
    // Set Current
    Block223Application.setCurrentGame(testGame);
    Block223Application.setCurrentUserRole(testAdmin);

    // Test if the level is correct
    int index = testGame.indexOfLevel(testLevel);
    assertEquals(index,0);

    // Add a block to grid 1,1
    testGame.getLevel(index).addBlockAssignment(1, 1, testBlock, testGame);

    assertEquals(testGame.getLevel(0).getBlockAssignments().size(),1);
    assertEquals(testGame.getLevel(0).getBlockAssignments().get(0).getGridHorizontalPosition(),1);
    assertEquals(testGame.getLevel(0).getBlockAssignments().get(0).getGridVerticalPosition(),1);

    // Add another block to grid 2,2
    Block testBlock2 = new Block(0, 0, 0, 0, testGame);
    testGame.getLevel(0).addBlockAssignment(2, 2, testBlock2, testGame);
    assertEquals(testGame.getLevel(0).getBlockAssignments().size(),2);
    assertEquals(testGame.getLevel(0).getBlockAssignments().get(1).getGridHorizontalPosition(),2);
    assertEquals(testGame.getLevel(0).getBlockAssignments().get(1).getGridVerticalPosition(),2);

    // Move from 1,1 to 2,2
    Block223Controller.moveBlock(1, 1, 1, -2, -2);
  }


  @Test
  public void MoveBlockTest() throws InvalidInputException {
    
    // Set current
    Block223Application.setCurrentGame(testGame);
    Block223Application.setCurrentUserRole(testAdmin);

    // Test if the level is correct
    int index = testGame.indexOfLevel(testLevel);
    assertEquals(index,0);

    // Add a block to grid 1,1
    testGame.getLevel(index).addBlockAssignment(1, 1, testBlock, testGame);
    assertEquals(testGame.getLevel(0).getBlockAssignments().size(),1);
    assertEquals(testGame.getLevel(0).getBlockAssignments().get(0).getGridHorizontalPosition(),1);
    assertEquals(testGame.getLevel(0).getBlockAssignments().get(0).getGridVerticalPosition(),1);

    // Add another block to grid 2,2
    testGame.getLevel(0).addBlockAssignment(2, 2, testBlock2, testGame);
    assertEquals(testGame.getLevel(0).getBlockAssignments().size(),2);
    assertEquals(testGame.getLevel(0).getBlockAssignments().get(1).getGridHorizontalPosition(),2);
    assertEquals(testGame.getLevel(0).getBlockAssignments().get(1).getGridVerticalPosition(),2);

    // Move from 1,1 to 3,3
    Block223Controller.moveBlock(1, 1, 1, 3, 3);
    assertEquals(testGame.getLevel(0).getBlockAssignments().size(),2);
    assertEquals(testGame.getLevel(0).getBlockAssignments().get(0).getGridHorizontalPosition(),3);
    assertEquals(testGame.getLevel(0).getBlockAssignments().get(0).getGridVerticalPosition(),3);
  }

}
