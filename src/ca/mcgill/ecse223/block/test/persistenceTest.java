package ca.mcgill.ecse223.block.test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.persistence.*;

public class persistenceTest {

  @Test
  public void testSaveUser() {
    Block223 block223 = new Block223();
    User user = new User("hahahha", block223, new Admin("123", block223));
    User user2 = new User("u2", block223, new Player("6666", block223));
    user.addRole(new Player("456", block223));
    
    Block223Persistence.save(block223);

    block223 = null;

    block223 = Block223Persistence.load();

    assertEquals(2, block223.getUsers().size());
    assertEquals(3, block223.getRoles().size());
    assertEquals(user.getUsername(), block223.getUsers().get(0).getUsername());
    assertEquals(user.getRoles().size(), block223.getUsers().get(0).getRoles().size());
    assertEquals(user.getRole(0).getPassword(),
        block223.getUsers().get(0).getRole(0).getPassword());
    assertEquals(user.getRole(1).getPassword(),
        block223.getUsers().get(0).getRole(1).getPassword());
    
    assertEquals(user2.getUsername(), block223.getUsers().get(1).getUsername());
    assertEquals(user2.getRoles().size(), block223.getUsers().get(1).getRoles().size());
    assertEquals(user2.getRole(0).getPassword(),
        block223.getUsers().get(1).getRole(0).getPassword());
  }

  @Test
  public void testSaveGame() {
    Block223 block223 = new Block223();
    User user = new User("u1", block223, new Admin("123", block223));

    String gameName = "g1";
    int numBlock = 20;
    int maxPaddleLength = 9;
    int minPaddleLength = 3;
    int minSpeedX = 10;
    int minSpeedY = 10;
    double speedIncreaseFactor = 5;
    Game game = new Game(gameName, numBlock, (Admin) user.getRole(0), minSpeedX, minSpeedY,
        speedIncreaseFactor, maxPaddleLength, minPaddleLength, block223);

    game.addBlock(10, 0, 0, 20);
    game.addBlock(20, 12, 14, 100);
    
    Block223Persistence.save(block223);
    
    block223 = Block223Persistence.load();
    
    assertEquals(1, block223.getGames().size());
    assertEquals(1, block223.getUsers().size());
    
    Game game2 = block223.getGame(0);
    assertEquals(gameName, game2.getName());
    assertEquals(numBlock, game2.getNrBlocksPerLevel());
    assertEquals(maxPaddleLength, game2.getPaddle().getMaxPaddleLength());
    assertEquals(minPaddleLength, game2.getPaddle().getMinPaddleLength());
    assertEquals(minSpeedX, game2.getBall().getMinBallSpeedX());
    assertEquals(minSpeedY, game2.getBall().getMinBallSpeedY());
    
    
    Block b = game2.getBlock(0);
    assertEquals(10, b.getRed());
    assertEquals(0, b.getGreen());
    assertEquals(0, b.getBlue());
    
    b = game2.getBlock(1);
    assertEquals(20, b.getRed());
    assertEquals(12, b.getGreen());
    assertEquals(14, b.getBlue());
    
    assertEquals(game2.getAdmin(), block223.getUser(0).getRole(0));
    
  }
  
  @Test
  public void testAddBlockToLevel() {
    Block223 block223 = new Block223();
    User user = new User("u1", block223, new Admin("123", block223));

    String gameName = "g1";
    int numBlock = 20;
    int maxPaddleLength = 9;
    int minPaddleLength = 3;
    int minSpeedX = 10;
    int minSpeedY = 10;
    double speedIncreaseFactor = 5;
    Game game = new Game(gameName, numBlock, (Admin) user.getRole(0), minSpeedX, minSpeedY,
        speedIncreaseFactor, maxPaddleLength, minPaddleLength, block223);
    
    Block b1 = game.addBlock(100, 200, 255, 1);
    Block b2 = game.addBlock(0, 2, 25, 10);
    
    Level l1 = game.addLevel();
    Level l2 = game.addLevel();
    
    l1.addBlockAssignment(10, 10, b1, game);
    l1.addBlockAssignment(20, 10, b1, game);
    
    l2.addBlockAssignment(0, 0, b2, game);
    
    Block223Persistence.save(block223);
    
    block223 = Block223Persistence.load();
    
    assertEquals(2, block223.getGame(0).getLevels().size());
    assertEquals(3, block223.getGame(0).getBlockAssignments().size());
    
    assertEquals(2, block223.getGame(0).getLevel(0).getBlockAssignments().size());
    assertEquals(10, block223.getGame(0).getLevel(0).getBlockAssignment(0).getGridHorizontalPosition());
    assertEquals(10, block223.getGame(0).getLevel(0).getBlockAssignment(0).getGridVerticalPosition());
    assertEquals(b1.getId(), block223.getGame(0).getLevel(0).getBlockAssignment(0).getBlock().getId());
    assertEquals(b1.getId(), block223.getGame(0).getLevel(0).getBlockAssignment(1).getBlock().getId());
    
    
    assertEquals(1, block223.getGame(0).getLevel(1).getBlockAssignments().size());
    assertEquals(0, block223.getGame(0).getLevel(1).getBlockAssignment(0).getGridHorizontalPosition());
    assertEquals(0, block223.getGame(0).getLevel(1).getBlockAssignment(0).getGridVerticalPosition());
    assertEquals(b2.getId(), block223.getGame(0).getLevel(1).getBlockAssignment(0).getBlock().getId());

  }

}
