package Java.com.garfiec.battleship.game.player;

import Java.com.garfiec.battleship.game.Remote_Client;
import Java.com.garfiec.battleship.game.ui.Battleship_Display;
import Java.com.garfiec.battleship.game.util.Player_Type;

public class Remote_Player_Client extends Player {
    Remote_Client cli;

    public Remote_Player_Client(Remote_Client cli) {
        player_type = Player_Type.REMOTE;
        this.cli = cli;
    }

    public void setUIHook(Battleship_Display ui) {
        this.ui = ui;
    }

    // Receive message via socket that it's player's turn. Tell player via UI to make move
    public void playersTurn() {
        // Todo: read above comment
    }

    // Transmit message back via socket
    public boolean makeMove(byte x, byte y) {
        // Todo: read above comment
        return false;
    }
}
