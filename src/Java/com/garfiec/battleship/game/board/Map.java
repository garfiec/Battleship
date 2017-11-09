package Java.com.garfiec.battleship.game.board;

import Java.com.garfiec.battleship.game.util.Game_Consts;

import java.awt.*;

public abstract class Map {
    // Generic Map
    public enum BoardType {
        ATTACK_BOARD,
        DEFEND_BOARD,
        GENERIC
    }

    protected BoardType type = BoardType.GENERIC;

    protected static final byte rows = Game_Consts.ROWS;
    protected static final byte cols = Game_Consts.COLUMNS;

    protected Region[][] regions = new Region[rows][cols];

    public abstract BoardType getType();
}
