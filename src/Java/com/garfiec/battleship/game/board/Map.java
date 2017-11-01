package Java.com.garfiec.battleship.game.board;

import Java.com.garfiec.battleship.game.util.Game_Constants;

public class Map {
    protected static final byte rows = Game_Constants.ROWS;
    protected static final byte cols = Game_Constants.COLUMNS;

    protected Region[][] regions = new Region[rows][cols];
}
