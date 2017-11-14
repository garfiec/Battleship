package com.garfiec.battleship.game.board.ships;

public enum Ship_Orientation {
    HORIZONTAL(0),
    VERTICAL(1);

    public final int index;

    Ship_Orientation(int index) {
        this.index = index;
    }
}
