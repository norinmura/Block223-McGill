package ca.mcgill.ecse223.block.controller;

import java.util.List;
import java.util.ArrayList;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.TOUserMode.Mode;
import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.model.PlayedGame.PlayStatus;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
// import ca.mcgill.ecse223.block.persistence.*;
import ca.mcgill.ecse223.block.view.Block223PlayModeInterface;


public class Block223Controller {

  // ****************************
  // Modifier methods
  // ****************************
  public static void createGame(String name) throws InvalidInputException {
    UserRole currentUserRole = Block223Application.getCurrentUserRole();
    Block223 block223 = Block223Application.getBlock223();

    if (!(currentUserRole instanceof Admin)) {
      throw new InvalidInputException("Admin privileges are required to create a game.");
    }

    // Check for duplicate game names and create new game
    if (!(block223.findGame(name) == null)) {
      throw new InvalidInputException("The name of a game must be unique.");
    }

    if (name == null || name.trim().isEmpty()) {
      throw new InvalidInputException("The name of a game must be specified.");
    }

    try {
      Game newGame = new Game(name, 1, (Admin) currentUserRole, 1, 1, 1, 10, 10, block223);
      Block223Application.setCurrentGame(newGame);
    } catch (RuntimeException e) {
      throw new RuntimeException(e);
    }
  }

  public static void setTestGame() {
    try {
      Game game = Block223Application.getBlock223().findGame("NewGame1");
      Block223Application.setCurrentGame(game);
      Block223Application
      .setCurrentPlayableGame(new PlayedGame("admin", game, Block223Application.getBlock223()));
      Block223Application.getCurrentPlayableGame()
      .setPlayer((Player) Block223Application.getCurrentUserRole());

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX,
      int minBallSpeedY, Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength)
          throws InvalidInputException {

    UserRole currentUserRole = Block223Application.getCurrentUserRole();
    Game currentGame = Block223Application.getCurrentGame();

    if (!(currentUserRole instanceof Admin)) {
      throw new InvalidInputException("Admin privileges are required to define game settings.");
    }

    if (currentGame == null) {
      throw new InvalidInputException("A game must be selected to define game settings.");
    }

    if (!(currentGame.getAdmin().equals(currentUserRole))) {
      throw new InvalidInputException(
          "Only the admin who created the game can define its game settings.");
    }

    if (nrLevels < 1 || nrLevels > 99) {
      throw new InvalidInputException("The number of levels must be between 1 and 99.");
    }

    if (nrBlocksPerLevel <= 0) {
      throw new InvalidInputException("The number of blocks per level must be greater than zero.");
    }

    if (minBallSpeedX < 0) {
      throw new InvalidInputException("The minimum speed of the ball must be greater than zero.");
    }

    if (minBallSpeedY < 0) {
      throw new InvalidInputException("The minimum speed of the ball must be greater than zero.");
    }

    if (minBallSpeedX == 0 && minBallSpeedY == 0) {
      throw new InvalidInputException("The minimum speed of the ball must be greater than zero.");
    }

    if (ballSpeedIncreaseFactor <= 0) {
      throw new InvalidInputException(
          "The speed increase factor of the ball must be greater than zero.");
    }

    if (maxPaddleLength <= 0 || maxPaddleLength > 390) {
      throw new InvalidInputException(
          "The maximum length of the paddle must be greater than zero and less than or equal to 390.");
    }

    if (minPaddleLength <= 0) {
      throw new InvalidInputException(
          "The minimum length of the paddle must be greater than zero.");
    }

    if (nrBlocksPerLevel < currentGame.getBlockAssignments().size()) {

      throw new InvalidInputException(
          "The maximum number of blocks per level cannot be less than the number of existing blocks in a level.");
    }

    // Catch RuntimeException from setting number of blocks per level
    try {
      currentGame.setNrBlocksPerLevel(nrBlocksPerLevel);
    } catch (RuntimeException e) {
      throw new RuntimeException(e);
    }

    // Catch RuntimeException from setting ball speed X,Y
    Ball ball = currentGame.getBall();
    try {
      ball.setMinBallSpeedX(minBallSpeedX);
    } catch (RuntimeException e) {
      throw new RuntimeException(e);
    }

    try {
      ball.setMinBallSpeedY(minBallSpeedY);
    } catch (RuntimeException e) {
      throw new RuntimeException(e);
    }

    // Catch RuntimeException from setting ball speed increase factor
    try {
      ball.setBallSpeedIncreaseFactor(ballSpeedIncreaseFactor);
    } catch (RuntimeException e) {
      throw new RuntimeException(e);
    }

    // Catch RunTimeException from setting max and min paddle length
    Paddle paddle = currentGame.getPaddle();
    try {
      paddle.setMaxPaddleLength(maxPaddleLength);
    } catch (RuntimeException e) {
      throw new RuntimeException(e);
    }

    try {
      paddle.setMinPaddleLength(minPaddleLength);
    } catch (RuntimeException e) {
      throw new RuntimeException(e);
    }

    // Set number of levels
    List<Level> levels = currentGame.getLevels();
    int size = levels.size();
    while (nrLevels > size) {
      currentGame.addLevel();
      size = levels.size();
    }
    while (nrLevels < size) {
      Level level = currentGame.getLevel(size - 1);
      level.delete();
      size = levels.size();
    }

  }

  public static void deleteGame(String name) throws InvalidInputException {
    // InvalidInputException

    UserRole currentUserRole = Block223Application.getCurrentUserRole();
    if (!currentUserRole.getClass().getSimpleName().equals("Admin")) {
      throw new InvalidInputException("Admin privileges are required to delete a game.");

    }
    // getWithName(name)
    Game game = Block223Application.getBlock223().findGame(name);
    if (game == null) {
      return;
    }

    if (game.getPublished()) {
      throw new InvalidInputException("A published game cannot be deleted.");
    }

    try {
      game.getAdmin();
    } catch (NullPointerException e) {
      throw new InvalidInputException("Only the admin who created the game can delete the game.");
    }
    if (!game.getAdmin().equals(currentUserRole)) {
      throw new InvalidInputException("Only the admin who created the game can delete the game.");
    }
    if (game.getPublished() == true) {
      throw new InvalidInputException("A published game cannot be deleted.");
    }

    // opt game != null
    if (game != null) {
      Block223 block223 = game.getBlock223();
      game.delete();
      // save to persistence layer
      Block223Persistence.save(block223);
    }
  }

  public static void selectGame(String name) throws InvalidInputException {
    // InvalidInputException
    UserRole currentUserRole = Block223Application.getCurrentUserRole();
    if (!currentUserRole.getClass().getSimpleName().equals("Admin")) {
      throw new InvalidInputException("Admin privileges are required to select a game.");

    }
    // getWithName(name)

    Game game = Block223Application.getBlock223().findGame(name);

    if (game == null) {
      throw new InvalidInputException("A game with name " + name + " does not exist.");
    }

    if (!game.getAdmin().equals(currentUserRole)) {
      throw new InvalidInputException("Only the admin who created the game can select the game.");
    }
    if (game.getPublished() == true) {
      throw new InvalidInputException("A published game cannot be changed.");
    }

    // setCurrentGame(game)
    Block223Application.setCurrentGame(game);

  }

  public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX,
      int minBallSpeedY, Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength)
          throws InvalidInputException {
    // InvalidInputException
    UserRole currentUserRole = Block223Application.getCurrentUserRole();
    if (!currentUserRole.getClass().getSimpleName().equals("Admin")) {
      throw new InvalidInputException("Admin privileges are required to define game settings.");

    }

    // getCurrentGame(game)
    Game game = Block223Application.getCurrentGame();
    if (game == null) {
      throw new InvalidInputException("A game must be selected to define game settings.");
    }

    if (!game.getAdmin().equals(currentUserRole)) {
      throw new InvalidInputException(
          "Only the admin who created the game can define its game settings.");
    }

    // currentName=getName()
    String currentName = game.getName();
    if (!currentName.equals(name)) {
      if (Block223Application.getBlock223().findGame(name) != null) {
        throw new InvalidInputException("The name of a game must be unique.");
      }
      game.setName(name);
    }
    if (name == null || name.length() == 0) {
      throw new InvalidInputException("The name of a game must be specified.");
    }

    // setGameDetail
    setGameDetails(nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY,
        ballSpeedIncreaseFactor, maxPaddleLength, minPaddleLength);

  }

  public static void addBlock(int red, int green, int blue, int points)
      throws InvalidInputException {
    UserRole role = Block223Application.getCurrentUserRole();
    if (!(role instanceof Admin)) {
      throw new InvalidInputException("Admin privileges are required to add a block.");
    }

    Game game = Block223Application.getCurrentGame();
    if (game == null) {
      throw new InvalidInputException("A game must be selected to add a block.");
    }

    if (!((Admin) role).equals(game.getAdmin())) {
      throw new InvalidInputException("Only the admin who created the game can add a block.");
    }
    if (red > 255 || red < 0) {
      throw new InvalidInputException("Red must be between 0 and 255.");
    }
    if (green > 255 || green < 0) {
      throw new InvalidInputException("Green must be between 0 and 255.");
    }
    if (blue > 255 || blue < 0) {
      throw new InvalidInputException("Blue must be between 0 and 255.");
    }
    if (points > 1000 || points < 1) {
      throw new InvalidInputException("Points must be between 1 and 1000.");
    }
    List<Block> blocks = game.getBlocks();
    for (Block block : blocks) {

      if (red == block.getRed() && green == block.getGreen() && blue == block.getBlue()) {
        block.reinitializeAutoniqueId(game.getBlocks());
        throw new InvalidInputException("A block with the same color already exists for the game.");
        // break;
      }
    }
    // Game game=Block223Application.getCurrentGame();

    game.addBlock(red, green, blue, points);
    // Block223 block223=Block223Application.getBlock223();
    // Block223Persistence.save(block223);

  }



  public static void deleteBlock(int id) throws InvalidInputException {
    UserRole role = Block223Application.getCurrentUserRole();
    if (!(role instanceof Admin)) {
      throw new InvalidInputException("Admin privileges are required to delete a block.");
    }

    Game game = Block223Application.getCurrentGame();
    if (game == null) {
      throw new InvalidInputException("A game must be selected to delete a block.");
    }

    if (!((Admin) role).equals(game.getAdmin())) {
      throw new InvalidInputException("Only the admin who created the game can delete a block.");
    }

    // game=Block223Application.getCurrentGame();

    List<Block> blocks = game.getBlocks();
    for (Block block : blocks) {
      int blockId = block.getId();
      if (blockId == id) {
        block.delete();
        break;
      }
    }


    for (Block block : game.getBlocks()) {
      int blockId = block.getId();
      if (blockId == id) {
        block.delete();
        break;
      }
    }
    // Block223 block223=Block223Application.getBlock223();
    // Block223Persistence.save(block223);

  }

  public static void updateBlock(int id, int red, int green, int blue, int points)
      throws InvalidInputException {

    UserRole role = Block223Application.getCurrentUserRole();
    if (!(role instanceof Admin)) {
      throw new InvalidInputException("Admin privileges are required to update a block.");
    }

    Game game = Block223Application.getCurrentGame();
    if (game == null) {
      throw new InvalidInputException("A game must be selected to update a block.");
    }

    if (!((Admin) role).equals(game.getAdmin())) {
      throw new InvalidInputException("Only the admin who created the game can update a block.");
    }

    if (points < Block.MIN_POINTS || points > Block.MAX_POINTS) {
      throw new InvalidInputException(
          "Points must be between " + Block.MIN_POINTS + " and " + Block.MAX_POINTS + ".");
    }



    Block foundBlock = null;
    for (Block block : game.getBlocks()) {
      if (block.getBlue() == blue && block.getGreen() == green && block.getRed() == red) {
        throw new InvalidInputException("A block with the same color already exists for the game.");
      }
      if (block.getId() == id) {
        foundBlock = block;

      }
    }
    if (foundBlock == null) {
      throw new InvalidInputException("The block does not exist.");
    }

    if (red > 255 || red < 0) {
      throw new InvalidInputException("Red must be between 0 and 255.");
    }
    if (green > 255 || green < 0) {
      throw new InvalidInputException("Green must be between 0 and 255.");
    }
    if (blue > 255 || blue < 0) {
      throw new InvalidInputException("Blue must be between 0 and 255.");
    }
    if (points > 1000 || points < 1) {
      throw new InvalidInputException("Points must be between 1 and 1000.");
    }

    foundBlock.setBlue(blue);
    foundBlock.setGreen(green);
    foundBlock.setRed(red);
    foundBlock.setPoints(points);

  }

  public static void positionBlock(int id, int level, int gridHorizontalPosition,
      int gridVerticalPosition) throws InvalidInputException {

    UserRole role = Block223Application.getCurrentUserRole();
    if (!(role instanceof Admin)) {
      throw new InvalidInputException("Admin privileges are required to position a block.");
    }

    Game game = Block223Application.getCurrentGame();
    if (game == null) {
      throw new InvalidInputException("A game must be selected to position a block.");
    }

    if (!((Admin) role).equals(game.getAdmin())) {
      throw new InvalidInputException("Only the admin who created the game can position a block.");
    }

    if (level < Game.MIN_NR_LEVELS || level > game.numberOfLevels()) {
      throw new InvalidInputException("Level " + level + " does not exist for the game.");
    }
    Level lv = game.getLevel(--level); // start at 1

    if (lv.getBlockAssignments().size() == game.getNrBlocksPerLevel()) {
      throw new InvalidInputException("The number of blocks has reached the maximum number ("
          + game.getNrBlocksPerLevel() + ") allowed for this game.");
    }

    for (BlockAssignment assignment : lv.getBlockAssignments()) {
      if (assignment.getGridHorizontalPosition() == gridHorizontalPosition
          && assignment.getGridVerticalPosition() == gridVerticalPosition) {
        throw new InvalidInputException("A block already exists at location "
            + gridHorizontalPosition + "/" + gridVerticalPosition + ".");
      }
    }

    // findBlock
    Block block = null;

    for (Block temp : game.getBlocks()) {
      if (id == temp.getId()) {
        block = temp;
        break;
      }
    }

    if (block == null)
      throw new InvalidInputException("The block does not exist.");

    // generated in umple?
    if (gridHorizontalPosition < 1 || gridHorizontalPosition > (Game.PLAY_AREA_SIDE
        - 2 * Game.WALL_PADDING + Game.COLUMNS_PADDING) / (Block.SIZE + Game.COLUMNS_PADDING)) {
      throw new InvalidInputException("The horizontal position must be between 1 and "
          + (Game.PLAY_AREA_SIDE - 2 * Game.WALL_PADDING + Game.COLUMNS_PADDING)
          / (Block.SIZE + Game.COLUMNS_PADDING)
          + ".");
    }

    if (gridVerticalPosition < 1
        || gridVerticalPosition > (Game.PLAY_AREA_SIDE - 2 * Game.WALL_PADDING - Paddle.PADDLE_WIDTH
            - Paddle.VERTICAL_DISTANCE - Ball.BALL_DIAMETER + Game.ROW_PADDING)
        / (Block.SIZE + Game.ROW_PADDING))
      throw new InvalidInputException("The vertical position must be between 1 and "
          + (Game.PLAY_AREA_SIDE - 2 * Game.WALL_PADDING - Paddle.PADDLE_WIDTH
              - Paddle.VERTICAL_DISTANCE - Ball.BALL_DIAMETER + Game.ROW_PADDING)
          / (Block.SIZE + Game.ROW_PADDING)
          + ".");

    lv.addBlockAssignment(gridHorizontalPosition, gridVerticalPosition, block, game);

  }

  public static void moveBlock(int level, int oldGridHorizontalPosition,
      int oldGridVerticalPosition, int newGridHorizontalPosition, int newGridVerticalPosition)
          throws InvalidInputException {

    // Obtain current working set
    UserRole currentUserRole = Block223Application.getCurrentUserRole();
    Game currentGame = Block223Application.getCurrentGame();

    // Input check
    if (!currentUserRole.getClass().getSimpleName().equals("Admin")) {
      throw new InvalidInputException("Admin privileges are required to move a block.");
    }

    if (currentGame == null) {
      throw new InvalidInputException("A game must be selected to move a block.");
    }

    if (!currentGame.getAdmin().equals(currentUserRole)) {
      throw new InvalidInputException("Only the admin who created the game can move a block.");
    }

    if (level < 1 || level > currentGame.numberOfLevels()) {
      throw new InvalidInputException("Level " + level + " does not exist for the game.");
    }

    level--; // Adjust to obtain index

    Level myLevel = currentGame.getLevel(level);
    List<BlockAssignment> assignments = myLevel.getBlockAssignments();
    boolean found = false;

    for (int i = 0; i < myLevel.numberOfBlockAssignments(); i++) {
      BlockAssignment assignment = assignments.get(i);
      if (assignment.getGridHorizontalPosition() == oldGridHorizontalPosition) {
        if (assignment.getGridVerticalPosition() == oldGridVerticalPosition) { // found assignment

          // Making sure the new destination is empty
          for (int j = 0; j < myLevel.numberOfBlockAssignments(); j++) {
            BlockAssignment newAssignment = assignments.get(j);
            if (newAssignment.getGridHorizontalPosition() == newGridHorizontalPosition) {
              if (newAssignment.getGridVerticalPosition() == newGridVerticalPosition) { // found:
                // assignment
                // already
                // exists
                throw new InvalidInputException("A block already exists at location "
                    + newGridHorizontalPosition + "/" + newGridVerticalPosition + ".");
              }
            }
          }

          // No time to make it elegant but ok
          if (newGridHorizontalPosition < 1
              || newGridHorizontalPosition > (Game.PLAY_AREA_SIDE - 2 * Game.WALL_PADDING
                  + Game.COLUMNS_PADDING) / (Block.SIZE + Game.COLUMNS_PADDING)) {
            throw new InvalidInputException("The horizontal position must be between 1 and "
                + (Game.PLAY_AREA_SIDE - 2 * Game.WALL_PADDING + Game.COLUMNS_PADDING)
                / (Block.SIZE + Game.COLUMNS_PADDING)
                + ".");
          }

          if (newGridVerticalPosition < 1 || newGridVerticalPosition > (Game.PLAY_AREA_SIDE
              - Game.WALL_PADDING - Paddle.PADDLE_WIDTH - Paddle.VERTICAL_DISTANCE
              - Ball.BALL_DIAMETER + Game.ROW_PADDING) / (Block.SIZE + Game.ROW_PADDING))
            throw new InvalidInputException("The vertical position must be between 1 and "
                + (Game.PLAY_AREA_SIDE - Game.WALL_PADDING - Paddle.PADDLE_WIDTH
                    - Paddle.VERTICAL_DISTANCE - Ball.BALL_DIAMETER + Game.ROW_PADDING)
                / (Block.SIZE + Game.ROW_PADDING)
                + ".");

          // Update the value
          try { // Catch RuntimeException from Assignment
            assignment.setGridHorizontalPosition(newGridHorizontalPosition);
            assignment.setGridVerticalPosition(newGridVerticalPosition);
          } catch (RuntimeException e) {
            throw new RuntimeException(e);
          }

          found = true;
          break;
        }
      }
    }

    if (!found) {
      throw new InvalidInputException("A block does not exist at location "
          + oldGridHorizontalPosition + "/" + oldGridVerticalPosition + ".");
    }
  }

  public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
      throws InvalidInputException {

    // Obtain current working set
    UserRole currentUserRole = Block223Application.getCurrentUserRole();
    Game currentGame = Block223Application.getCurrentGame();

    // Input check
    if (!currentUserRole.getClass().getSimpleName().equals("Admin")) {
      throw new InvalidInputException("Admin privileges are required to remove a block.");
    }

    if (currentGame == null) {
      throw new InvalidInputException("A game must be selected to remove a block.");
    }

    if (!currentGame.getAdmin().equals(currentUserRole)) {
      throw new InvalidInputException("Only the admin who created the game can remove a block.");
    }

    level--; // Adjust to obtain index

    // Search for the BlockAssignment with given value
    Level myLevel = currentGame.getLevel(level);
    List<BlockAssignment> assignments = myLevel.getBlockAssignments();

    for (int i = 0; i < myLevel.numberOfBlockAssignments(); i++) {
      BlockAssignment assignment = assignments.get(i);
      if (assignment.getGridHorizontalPosition() == gridHorizontalPosition) {
        if (assignment.getGridVerticalPosition() == gridVerticalPosition) { // found assignment
          // Delete the value
          assignment.delete();
          break;
        }
      }
    }

  }

  public static void saveGame() throws InvalidInputException {
    Block223 block223 = Block223Application.getBlock223();
    if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
      throw new InvalidInputException("Admin privileges are required to save a game.");
    } else if (Block223Application.getCurrentGame() == null) {
      throw new InvalidInputException("A game must be selected to save it.");
    } else if (Block223Application.getCurrentGame().getAdmin() != Block223Application
        .getCurrentUserRole()) {
      throw new InvalidInputException("Only the admin who created the game can save it.");
    } else {
      Block223Persistence.save(block223);
    }


  }

  public static void register(String username, String password, String adminPassword)
      throws InvalidInputException {

    Block223 block223 = Block223Application.getBlock223();
    String error = "";

    if (password == null) {
      error = "The player password needs to be specified.";
    } else if (password.isEmpty()) {
      error = "The player password needs to be specified.";
    } else if (password.equals(adminPassword)) {
      error = "The passwords have to be different.";
    }

    if (username == null) {
      error = "The username must be specified.";
    } else if (username.isEmpty()) {

      error = "The username must be specified.";
    }



    User user = User.getWithUsername(username);


    if (user != null) {
      error = "The username has already been taken.";
    }
    if (Block223Application.getCurrentUserRole() != null) {
      error = "Cannot register a new user while a user is logged in.";
    }

    if (error.length() > 0) {
      throw new InvalidInputException(error);
    }

    else {
      if (adminPassword == null || adminPassword.isEmpty()) {
        Player player = new Player(password, block223);
        User newUser = new User(username, block223, player);

      } else {
        Admin admin = new Admin(adminPassword, block223);
        Player player = new Player(password, block223);
        User newUser = new User(username, block223, player, admin);

      }
      Block223Persistence.save(block223);
    }



  }

  public static void login(String username, String password) throws InvalidInputException {
    Block223Application.reset();
    Block223 block223 = Block223Application.getBlock223();
    Boolean validLogin = false;
    String error = "";
    if (username.isEmpty()) {
      error = "* username is left blank";
    } else if (password.isEmpty()) {
      error = "* password is left blank";
    }

    else {
      for (User user : block223.getUsers()) {
        if (user.getUsername().equals(username)) {
          for (UserRole role : user.getRoles()) {
            if (role.getPassword().equals(password)) {
              Block223Application.setCurrentUserRole(role);
              validLogin = true;
            }
          }
        }
      }
    }
    if (!validLogin) {
      error = "The username and password do not match.";
    }
    if (error.length() > 0) {
      throw new InvalidInputException(error);
    }
    Block223Persistence.save(block223);
  }

  public static void logout() {
    Block223 block223 = Block223Application.getBlock223();
    Block223Application.setCurrentUserRole(null);
    Block223Persistence.save(block223);
  }

  // play mode

  public static void selectPlayableGame(String name, int id) throws InvalidInputException {
    if (!(Block223Application.getCurrentUserRole() instanceof Player)) {
      throw new InvalidInputException("Player privileges are required to play a game.");
    }

    Block223 block223 = Block223Application.getBlock223();
    Game game = Game.getWithName(name);
    game = block223.findGame(name);
    PlayedGame pgame;// = new PlayedGame(name,block223);
    Player player = (Player) Block223Application.getCurrentUserRole();

    if (game != null) { // if game was found by name (new game)
      String username = User.findUsername(player);
      pgame = new PlayedGame(username, game, block223);
      pgame.setPlayer(player);
    } else {    // if it's continuation
      pgame = block223.findPlayableGame(id);
      if (pgame == null && block223.findGame(name) == null) {
        throw new InvalidInputException("The game does not exist.");
      }

      try {
        if (pgame.getPlayer() != Block223Application.getCurrentUserRole()) {
          throw new InvalidInputException(
              "Only the player that started a game can continue the game.");
        }
        pgame.setPlayer(player);
      } catch (NullPointerException e) {

      }


    }

    Block223Application.setCurrentPlayableGame(pgame);
  }


  public static void startGame(Block223PlayModeInterface ui) throws InvalidInputException {



    if (Block223Application.getCurrentPlayableGame() == null) {
      throw new InvalidInputException("A game must be selected to play it.");

    }

    if (!(Block223Application.getCurrentUserRole() instanceof Player) && Block223Application.getCurrentPlayableGame().getPlayer() != null) {
      throw new InvalidInputException("Player privileges are required to play a game.");
    }
    if (!(Block223Application.getCurrentUserRole() instanceof Admin) && Block223Application.getCurrentPlayableGame().getPlayer() == null) {
      throw new InvalidInputException("Admin privileges are required to test a game.");
    }

    UserRole gameMaker = null;

    for(UserRole userRole:User.getWithUsername(Block223Application.getCurrentPlayableGame().getPlayername()).getRoles()) {
      if(userRole instanceof Admin) {
        gameMaker = userRole;
      }
    }

    if (!(Block223Application.getCurrentUserRole() instanceof Admin)
        && !Block223Application.getCurrentPlayableGame().getGame().getPublished()) {
      throw new InvalidInputException("Admin privileges are required to test a game.");
    } else if (Block223Application.getCurrentPlayableGame() == null) {
      throw new InvalidInputException("A game must be selected to play it.");
    } 


    else if ( gameMaker!= Block223Application
        .getCurrentUserRole() && Block223Application.getCurrentPlayableGame().getPlayer() == null) {
      throw new InvalidInputException("Only the admin of a game can test the game.");

    }



    PlayedGame game = Block223Application.getCurrentPlayableGame();
    game.play();


    // Not sure why this is here
    ui.takeInputs();
    
    Block223Application.setCurrentGameName(game.getGame().getName());

    while (game.getPlayStatus() == PlayStatus.Moving) {
      String userInputs = ui.takeInputs();
      updatePaddlePosition(userInputs);
      ui.refresh();
      game.move();

      if (userInputs.contains(" ")) {
        game.pause();
      }

      try {
        Thread.sleep((long) game.getWaitTime());
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      // refresh view
      ui.refresh();
    }
    
    

    if (game.getPlayer() != null) {
      System.out.println("123456");
      Block223Application.getCurrentPlayableGame().setBounce(null);
      Block223 block223 = Block223Application.getBlock223();
      Block223Persistence.save(block223);
    }
    if (game.getPlayStatus() == PlayStatus.GameOver) {
      System.out.println("GAMEOVER! ");
      ui.endGame(game.getLives(), null);
      Block223Application.setCurrentPlayableGame(null);

    }


  }


  private static void updatePaddlePosition(String userInputs) {
    PlayedGame game = Block223Application.getCurrentPlayableGame();
    for (int i = 0; i < userInputs.length(); i++) {
      if (userInputs.substring(i, i + 1).equals("l") && game.getCurrentPaddleX() > 0) {
        game.setCurrentPaddleX(game.getCurrentPaddleX() + PlayedGame.PADDLE_MOVE_LEFT*5);
      } else if (userInputs.substring(i, i + 1).equals("r") && game.getCurrentPaddleX() < (Game.PLAY_AREA_SIDE-game.getCurrentPaddleLength())) {
        game.setCurrentPaddleX(game.getCurrentPaddleX() + PlayedGame.PADDLE_MOVE_RIGHT*5);
      }
    }
  }

  /**
   * This method allows admin to test
   * 
   * @param ui
   * @throws InvalidInputException
   */
  public static void testGame(Block223PlayModeInterface ui) throws InvalidInputException {
    // getBlock223()
    Block223 block223 = Block223Application.getBlock223();

    // Obtain current working set
    UserRole currentUserRole = Block223Application.getCurrentUserRole();
    Game currentGame = Block223Application.getCurrentGame();

    // Input check
    if (!currentUserRole.getClass().getSimpleName().equals("Admin")) {
      throw new InvalidInputException("Admin privileges are required to test a game.");
    }

    if (currentGame == null) {
      throw new InvalidInputException("A game must be selected to test it.");
    }

    if (!currentGame.getAdmin().equals(currentUserRole)) {
      throw new InvalidInputException("Only the admin who created the game can test it.");
    }

    // Obtain username
    String username = User.findUsername(currentUserRole);

    // Create new PlayedGame in order to play
    PlayedGame pgame = new PlayedGame(username, currentGame, block223);
    pgame.setPlayer(null);

    // Play
    Block223Application.setCurrentPlayableGame(pgame);
    startGame(ui);
  }

  public static void publishGame() throws InvalidInputException {
    // Obtain current working set
    UserRole currentUserRole = Block223Application.getCurrentUserRole();
    Game currentGame = Block223Application.getCurrentGame();

    // Input check
    currentUserRole.getClass();
    if (!currentUserRole.getClass().getSimpleName().equals("Admin")) {
      throw new InvalidInputException("Admin privileges are required to publish a game.");
    }

    if (currentGame == null) {
      throw new InvalidInputException("A game must be selected to publish it.");
    }

    if (!currentGame.getAdmin().equals(currentUserRole)) {
      throw new InvalidInputException("Only the admin who created the game can publish it.");
    }

    if (currentGame.getBlocks().size() < 1) {
      throw new InvalidInputException(
          "At least one block must be defined for a game to be published.");
    }

    currentGame.setPublished(true);
  }


  // ****************************
  // Query methods
  // ****************************
  public static List<TOGame> getDesignableGames() throws InvalidInputException {
    // InvalidInputException
    // get Admin
    UserRole currentUserRole = Block223Application.getCurrentUserRole();
    if (!currentUserRole.getClass().getSimpleName().equals("Admin")) {
      throw new InvalidInputException("Admin privileges are required to access game information.");
    }

    // getBlock223()
    Block223 block223 = Block223Application.getBlock223();


    // create List<ToGame>
    List<TOGame> result = new ArrayList<TOGame>();

    for (Game game : block223.getGames()) {
      if (game.getAdmin().equals(currentUserRole)) {
        if (game.getPublished() == false) {
          TOGame toGame = new TOGame(game.getName(), game.getLevels().size(),
              game.getNrBlocksPerLevel(), game.getBall().getMinBallSpeedX(),
              game.getBall().getMinBallSpeedY(), game.getBall().getBallSpeedIncreaseFactor(),
              game.getPaddle().getMaxPaddleLength(), game.getPaddle().getMinPaddleLength());
          result.add(toGame);
        }
      }
    }

    return result;
  }

  public static TOGame getCurrentDesignableGame() throws InvalidInputException {
    // InvalidInputException
    // get Admin
    UserRole currentUserRole = Block223Application.getCurrentUserRole();
    if (!currentUserRole.getClass().getSimpleName().equals("Admin")) {
      throw new InvalidInputException("Admin privileges are required to access game information.");
    }

    // getCurrentGame
    Game game = Block223Application.getCurrentGame();
    if (game == null) {
      throw new InvalidInputException("A game must be selected to access its information.");
    }

    if (!game.getAdmin().equals(currentUserRole)) {
      throw new InvalidInputException(
          "Only the admin who created the game can access its information.");
    }

    // create TOGame
    TOGame toGame = new TOGame(game.getName(), game.getLevels().size(), game.getNrBlocksPerLevel(),
        game.getBall().getMinBallSpeedX(), game.getBall().getMinBallSpeedY(),
        game.getBall().getBallSpeedIncreaseFactor(), game.getPaddle().getMaxPaddleLength(),
        game.getPaddle().getMinPaddleLength());

    return toGame;
  }

  public static List<TOBlock> getBlocksOfCurrentDesignableGame() throws InvalidInputException {

    ArrayList<TOBlock> dtoBlocks = new ArrayList<TOBlock>();

    UserRole role = Block223Application.getCurrentUserRole();
    if (!(role instanceof Admin)) {
      throw new InvalidInputException("Admin privileges are required to access game information.");
    }

    Game game = Block223Application.getCurrentGame();
    if (game == null) {
      throw new InvalidInputException("A game must be selected to access its information.");
    }

    if (!((Admin) role).equals(game.getAdmin())) {
      throw new InvalidInputException(
          "Only the admin who created the game can access its information.");
    }

    for (Block block : game.getBlocks()) {
      TOBlock temp = new TOBlock(block.getId(), block.getRed(), block.getGreen(), block.getBlue(),
          block.getPoints());
      dtoBlocks.add(temp);
    }
    return dtoBlocks;
  }

  public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {

    UserRole role = Block223Application.getCurrentUserRole();
    if (!(role instanceof Admin)) {
      throw new InvalidInputException("Admin privileges are required to access game information.");
    }

    Game game = Block223Application.getCurrentGame();
    if (game == null) {
      throw new InvalidInputException("A game must be selected to access its information.");
    }

    if (!((Admin) role).equals(game.getAdmin())) {
      throw new InvalidInputException(
          "Only the admin who created the game can access its information.");
    }

    for (Block temp : game.getBlocks()) {
      if (temp.getId() == id) {
        return new TOBlock(temp.getId(), temp.getRed(), temp.getGreen(), temp.getBlue(),
            temp.getPoints());
      }
    }

    throw new InvalidInputException("The block does not exist.");
  }

  public static List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level)
      throws InvalidInputException {
    // getCurrentGame

    UserRole role = Block223Application.getCurrentUserRole();
    if (!(role instanceof Admin)) {
      throw new InvalidInputException("Admin privileges are required to access game information.");
    }

    Game game = Block223Application.getCurrentGame();
    if (game == null) {
      throw new InvalidInputException("A game must be selected to access its information.");
    }

    if (!((Admin) role).equals(game.getAdmin())) {
      throw new InvalidInputException(
          "Only the admin who created the game can access its information.");
    }

    if (game.getLevels().size() < level || level < Game.MIN_NR_LEVELS) {
      throw new InvalidInputException("Level " + level + " does not exist for the game.");
    }

    level--;

    Level thisLevel = game.getLevel(level);
    List<BlockAssignment> assignments = thisLevel.getBlockAssignments();
    List<TOGridCell> TOassignments = new ArrayList<TOGridCell>();
    for (BlockAssignment assignment : assignments) {
      TOassignments.add(new TOGridCell(assignment.getGridHorizontalPosition(),
          assignment.getGridVerticalPosition(), assignment.getBlock().getId(),
          assignment.getBlock().getRed(), assignment.getBlock().getGreen(),
          assignment.getBlock().getBlue(), assignment.getBlock().getPoints()));
    }

    return TOassignments;
  }


  public static TOUserMode getUserMode() {
    TOUserMode usermode = new TOUserMode(Mode.None);

    if (Block223Application.getCurrentUserRole() instanceof Player) {
      usermode = new TOUserMode(Mode.Play);
    } else if (Block223Application.getCurrentUserRole() instanceof Admin) {
      usermode = new TOUserMode(Mode.Design);
    }
    return usermode;
  }

  // play mode

  public static List<TOPlayableGame> getPlayableGames() throws InvalidInputException {
    if (!(Block223Application.getCurrentUserRole() instanceof Player)) {
      throw new InvalidInputException("Player privileges are required to play a game.");
    }
    Block223 block223 = Block223Application.getBlock223();
    if (!(Block223Application.getCurrentUserRole() instanceof Player)) {
      throw new InvalidInputException("Player privileges are required to play a game");
    }
    Player player = (Player) Block223Application.getCurrentUserRole();
    List<TOPlayableGame> result = new ArrayList<TOPlayableGame>();
    List<Game> games = block223.getGames();
    for (Game game : games) {
      if (game.isPublished()) {
        TOPlayableGame to = new TOPlayableGame(game.getName(), -1, 0);
        result.add(to);

      }
    }
    List<PlayedGame> playedGames = player.getPlayedGames();
    for (PlayedGame game : playedGames) {
      TOPlayableGame to =
          new TOPlayableGame(game.getGame().getName(), game.getId(), game.getCurrentLevel());
      result.add(to);
    }
    return result;
  }



  public static TOCurrentlyPlayedGame getCurrentPlayableGame() throws InvalidInputException {

    PlayedGame pgame = Block223Application.getCurrentPlayableGame();
    
    if (Block223Application.getCurrentPlayableGame() == null) {
      throw new InvalidInputException("A game must be selected to play it.");
    } else if (Block223Application.getCurrentPlayableGame().getPlayer() == null) {
      // Testing Mode
      if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
        throw new InvalidInputException("Admin privileges are required to test a game.");
      } else if (!(pgame.getGame().getAdmin().equals(Block223Application.getCurrentUserRole()))) {
        throw new InvalidInputException("Only the admin of a game can test the game.");
      }

    } else if (!(Block223Application.getCurrentUserRole() instanceof Player)) {
      throw new InvalidInputException("Player privileges are required to play a game.");
    }


    boolean paused =
        pgame.getPlayStatus() == PlayStatus.Ready || pgame.getPlayStatus() == PlayStatus.Paused;
    TOCurrentlyPlayedGame result = new TOCurrentlyPlayedGame(pgame.getGame().getName(), paused,
        pgame.getScore(), pgame.getLives(), pgame.getCurrentLevel(), pgame.getPlayername(),
        pgame.getCurrentBallX(), pgame.getCurrentBallY(), pgame.getCurrentPaddleLength(),
        pgame.getCurrentPaddleX());
    List<PlayedBlockAssignment> blocks = pgame.getBlocks();
    for (PlayedBlockAssignment pblock : blocks) {
      TOCurrentBlock to = new TOCurrentBlock(pblock.getBlock().getRed(),
          pblock.getBlock().getGreen(), pblock.getBlock().getBlue(), pblock.getBlock().getPoints(),
          pblock.getX(), pblock.getY(), result);
    }
    return result;
  }

  public static List<PlayedBlockAssignment> getBlocksAtLevelOfCurrentPlayableGame()
      throws InvalidInputException {
    // getCurrentGame

    PlayedGame game = Block223Application.getCurrentPlayableGame();
    if (game == null) {
      throw new InvalidInputException(
          "A playable game must be selected to access its information.");
    }

    return game.getBlocks();
  }


  public static TOHallOfFame getHallOfFame(int start, int end) throws InvalidInputException {
    // invalid input exception
    UserRole role = Block223Application.getCurrentUserRole();
    if (!(role instanceof Player)) {
      throw new InvalidInputException(
          "Player privileges are required to access a game's hall of fame.");
    }
    PlayedGame pgame = Block223Application.getCurrentPlayableGame();
    if (pgame == null) {
      throw new InvalidInputException("A game must be selected to view its hall of fame.");
    }


    Game game = pgame.getGame();
    TOHallOfFame result = new TOHallOfFame(game.getName());

    if (start < 1) {
      start = 1;
    }

    if (end > game.numberOfHallOfFameEntries()) {
      end = game.numberOfHallOfFameEntries();

    }
    start = game.numberOfHallOfFameEntries() - start;
    end = game.numberOfHallOfFameEntries() - end;

    for (int i = start; i >= end; i--) {
      TOHallOfFameEntry toHallOfFameEntry =
          new TOHallOfFameEntry(i + 1, game.getHallOfFameEntry(i).getPlayername(),
              game.getHallOfFameEntry(i).getScore(), result);


    }

    return result;
  }

  public static TOHallOfFame getHallOfFameWithMostRecentEntry(int numberOfEntries)
      throws InvalidInputException {
    // invalid input exception
    UserRole role = Block223Application.getCurrentUserRole();
    if (!(role instanceof Player)) {
      throw new InvalidInputException(
          "Player privileges are required to access a game's hall of fame.");
    }

    PlayedGame pgame = Block223Application.getCurrentPlayableGame();
    if (pgame == null) {
      throw new InvalidInputException("A game must be selected to view its hall of fame.");
    }

    Game game = pgame.getGame();
    TOHallOfFame result = new TOHallOfFame(game.getName());
    HallOfFameEntry mostRecent = game.getMostRecentEntry();
    int index = game.indexOfHallOfFameEntry(mostRecent);
    int start = index + numberOfEntries / 2;

    if (start > game.numberOfHallOfFameEntries() - 1) {
      start = game.numberOfHallOfFameEntries() - 1;

    }
    int end = start - numberOfEntries + 1;

    if (end < 0) {
      end = 0;
    }
    for (index = start; index >= end; index--) {
      TOHallOfFameEntry toHallOfFameEntry =
          new TOHallOfFameEntry(index + 1, game.getHallOfFameEntry(index).getPlayername(),
              game.getHallOfFameEntry(index).getScore(), result);
    }
    return result;
  }
  
  public static TOHallOfFame getHallOfFame(String gameName, int start, int end) throws InvalidInputException {
	    // invalid input exception
	    UserRole role = Block223Application.getCurrentUserRole();
	    if (!(role instanceof Player)) {
	      throw new InvalidInputException(
	          "Player privileges are required to access a game's hall of fame.");
	    }

	    Game game = Block223Application.getBlock223().findGame(gameName);
	    TOHallOfFame result = new TOHallOfFame(game.getName());

	    if (start < 1) {
	      start = 1;
	    }

	    if (end > game.numberOfHallOfFameEntries()) {
	      end = game.numberOfHallOfFameEntries();

	    }
	    start = game.numberOfHallOfFameEntries() - start;
	    end = game.numberOfHallOfFameEntries() - end;

	    System.out.println(start + "  " + end);
	    
	    for (int i = start; i >= end; i--) {
	      TOHallOfFameEntry toHallOfFameEntry =
	          new TOHallOfFameEntry(i + 1, game.getHallOfFameEntry(i).getPlayername(),
	              game.getHallOfFameEntry(i).getScore(), result);
	    }

	    return result;
	  }
  
  public static String getCurrentGameName() {
	  return Block223Application.getCurrentGameName();
  }
  
  public static int getHOFLength(String gameName) {
	  Game game = Block223Application.getBlock223().findGame(gameName);	  
	  return game.numberOfHallOfFameEntries();
  }

  public static boolean isCurrentUserAdmin() {
    if (Block223Application.getCurrentUserRole() instanceof Admin) {
      return true;
    }
    return false;
  }

  public void initTempPlayeableGame() {

  }

  public static int getBlockSize() {
    return Block.SIZE;
  }

  public static int getBallSize() {
    return Ball.BALL_DIAMETER;
  }

  public static int getPaddleVerticalDistance() {
    return Paddle.VERTICAL_DISTANCE;
  }

  public static int getPaddleWidth() {
    return Paddle.PADDLE_WIDTH;
  }
  public static int getWallPadding() {
    return Game.WALL_PADDING;
  }
  public static int getColumnPadding() {
    return Game.COLUMNS_PADDING;
  }
  public static int getRowPadding() {
    return Game.ROW_PADDING;
  }
  public static boolean isGameOver() {
    PlayedGame game = Block223Application.getCurrentPlayableGame();
    boolean isGameOver= game.getPlayStatus()==PlayStatus.GameOver;
    return isGameOver;
  }
  
}
