package Java.com.garfiec.battleship.game.player;

import Java.com.garfiec.battleship.game.board.ships.Ships;
import Java.com.garfiec.battleship.game.ui.Battleship_Display;
import Java.com.garfiec.battleship.game.util.Player_Type;

import java.awt.*;

public abstract class Player {
    protected Battleship_Display ui;

    protected Player_Type player_type;

    public abstract void setUIHook(Battleship_Display ui);
    public abstract void doAddships();
    public abstract boolean addShip(Ships ship, Point cord);
    public abstract void playersTurn();
    public abstract boolean makeMove(byte x, byte y);
}
