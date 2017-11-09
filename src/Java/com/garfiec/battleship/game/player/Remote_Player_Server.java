package Java.com.garfiec.battleship.game.player;

import Java.com.garfiec.battleship.game.Game_Manager;
import Java.com.garfiec.battleship.game.ui.Battleship_Display;
import Java.com.garfiec.battleship.game.util.Player_Type;

public class Remote_Player_Server extends Player {
    Game_Manager gm;

    public Remote_Player_Server(Game_Manager gm) {
        player_type = Player_Type.REMOTE;
        this.gm = gm;
    }

    @Override
    public void setUIHook(Battleship_Display ui) {
        // Do nothing. Ser
    }

    // Send message to remote gui to make a move (Transmit)
    @Override
    public void playersTurn() {
        // Todo: Transmit message to remote client indicating that it's the player's turn
    }

    // When received response from socket, passes move to game manager (Receive and pass back)
    @Override
    public boolean makeMove(byte x, byte y) {

        gm.makeMove(player_type, x, y);
        return false;
    }

    // Todo: Socket listening for moves. Implement runnable to prevent program from locking up while attempting to communicate
    // Todo: Translate incoming message into a move
    // Todo: Transmit message remote client
}
