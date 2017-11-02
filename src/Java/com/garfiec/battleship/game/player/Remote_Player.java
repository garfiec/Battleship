package Java.com.garfiec.battleship.game.player;

import Java.com.garfiec.battleship.game.Game_Manager;
import Java.com.garfiec.battleship.game.util.Player_Type;

public class Remote_Player extends Player {

    public Remote_Player(Game_Manager gm) {
        player_type = Player_Type.REMOTE;
        this.gm = gm;
    }

    protected void makeMove(byte x, byte y) {
        gm.makeMove(player_type, x, y);
    }

    // Todo: Socket listening for moves. Implement runnable to prevent program from locking up while attempting to communicate
    // Todo: Translate incoming message into a move
    // Todo: Transmit message remote client
}
