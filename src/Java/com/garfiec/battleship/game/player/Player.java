package Java.com.garfiec.battleship.game.player;

import Java.com.garfiec.battleship.game.Game_Manager;
import Java.com.garfiec.battleship.game.util.Player_Type;

public abstract class Player {
    Game_Manager gm;
    protected Player_Type player_type;

    protected abstract void makeMove(byte x, byte y);
}
