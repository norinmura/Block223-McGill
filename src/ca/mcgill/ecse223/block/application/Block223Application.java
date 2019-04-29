package ca.mcgill.ecse223.block.application;


import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
// import ca.mcgill.ecse223.block.persistence.Block223Persistence;
// import ca.mcgill.ecse223.block.view.Block223Page;
import ca.mcgill.ecse223.block.view.*;
// import ca.mcgill.ecse223.block.per*;


public class Block223Application {

	private static Block223 block223;
	private static Game currentGame;
	private static PlayedGame currentPlayedGame;

	private static UserRole currentUserRole;
	private static PlayedGame currentPlayableGame;
	private static String currentGameName;

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {

				Block223Login page = new Block223Login();
				page.setVisible(true);

				//        GameSettingPage page = new GameSettingPage();
				//        page.setVisible(true);

				//         new Block223CreateBlock().setVisible(true);
				//        Block223 block223 = new Block223();
				//        Admin admin = new Admin("123", block223);
				//        Game game = new Game("g1", 20, admin, 20, 20, 10, 300, 100, block223);
				//        currentGame = game;
				//        currentUserRole = admin;
				//        game.addBlock(new Block(20,20,20,100, game));
				//        game.addBlock(new Block(20,200,20,100, game));
				//        game.addBlock(new Block(20,20,200,100, game));
				//        game.addBlock(new Block(20,20,20,100, game));
				//        game.addBlock(new Block(20,200,20,100, game));
				//        game.addBlock(new Block(20,20,200,100, game));
				//        game.addBlock(new Block(20,20,20,100, game));
				//        game.addBlock(new Block(20,200,20,100, game));
				//        game.addBlock(new Block(20,20,200,100, game));
				//        game.addBlock(new Block(20,20,20,100, game));
				//        game.addBlock(new Block(20,200,20,100, game));
				//        game.addBlock(new Block(20,20,200,100, game));
				//        game.addBlock(new Block(20,20,20,100, game));
				//        game.addBlock(new Block(20,200,20,100, game));
				//        game.addBlock(new Block(20,20,200,100, game));
				//        game.addBlock(new Block(20,20,20,100, game));
				//        game.addBlock(new Block(20,200,20,100, game));
				//        game.addBlock(new Block(20,20,200,100, game));
				//        game.addBlock(new Block(20,20,20,100, game));
				//        game.addBlock(new Block(20,200,20,100, game));
				//        game.addBlock(new Block(20,20,200,100, game));
				//        
				//        new Block223BlockInGame().setVisible(true);

			}
		});
	}

	public static Block223 getBlock223() {
		if (block223 == null) {

			// load model
			block223 = Block223Persistence.load();
		}
		return block223;
	}

	/*
	 * Getter and Setter
	 */

	/**
	 * This method returns Game that the program is currently working with
	 * 
	 * @return Game - Instance of Game that the program is currently working with
	 */
	public static Game getCurrentGame() {
		return currentGame;
	}

	/**
	 * This method allows controller to set currentGame that the program is going to work with
	 * 
	 * @param currentGame - Instance of Game
	 */
	public static void setCurrentGame(Game currentGame) {
		Block223Application.currentGame = currentGame;
	}

	/**
	 * This method returns UserRole of the logged in user
	 * 
	 * @return UserRole - UserRole instance of the logged in user
	 */
	public static UserRole getCurrentUserRole() {
		return currentUserRole;
	}

	/**
	 * This method allows controller to set currentUserRole as the user log-in is successful
	 * 
	 * @param currentUserRole - UserRole instance of the logged in user
	 */
	public static void setCurrentUserRole(UserRole currentUserRole) {
		Block223Application.currentUserRole = currentUserRole;
	}


	/**
	 * This method returns instance of currentPlayedGame
	 * @return PlayedGame - PlayedGame instance of the current game
	 */
	public static PlayedGame getCurrentPlayableGame() {
		return currentPlayedGame;
	}

	/**
	 * This method allows controller to set currentPlayedGame that the program is goign to work with
	 * @param currentPlayedGame - Instance of PlayedGame
	 */
	public static void setCurrentPlayableGame(PlayedGame aGame) {
		Block223Application.currentPlayedGame = aGame;
	}

	public static void reset() {
		block223 = Block223Persistence.load();
	}

	public static String getCurrentGameName() {
		return currentGameName;
	}

	public static void setCurrentGameName(String currentGameName) {
		Block223Application.currentGameName = currentGameName;
	}

}
