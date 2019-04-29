package ca.mcgill.ecse223.block.view;
import ca.mcgill.ecse223.block.controller.TOCurrentlyPlayedGame;

public interface Block223GamePanelInterface extends Block223PlayModeInterface {
    public TOCurrentlyPlayedGame getCurrentPlayableGame();
}
