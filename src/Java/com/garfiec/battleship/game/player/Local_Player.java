package Java.com.garfiec.battleship.game.player;

import Java.com.garfiec.battleship.game.Game_Manager;
import Java.com.garfiec.battleship.game.util.Player_Type;

public class Local_Player extends Player {

    public Local_Player(Game_Manager gm) {
        player_type = Player_Type.LOCAL;
        this.gm = gm;
    }

    protected void makeMove(byte x, byte y) {
        gm.makeMove(player_type, x, y);
    }
}
