package com.garfiec.battleship.game.player;

import com.garfiec.battleship.game.board.ships.Ship_Orientation;
import com.garfiec.battleship.game.board.ships.Ships;
import com.garfiec.battleship.game.ui.Battleship_Display;
import com.garfiec.battleship.game.util.Connection_Settings;
import com.garfiec.battleship.game.util.Player_Type;

import java.awt.*;

public abstract class Player {
    protected Battleship_Display ui;
    protected Connection_Settings connection_settings;

    protected Player_Type player_type;
    protected String guiMessageBuffer = null;

    public abstract Player_Type getPlayerType();
    public abstract void setUIHook(Battleship_Display ui);
    public abstract void setConnectionSettings(Connection_Settings settings_obj);
    public abstract boolean addShip(Ships ship, Ship_Orientation direction, Point cord);
    public abstract boolean makeMove(Point location);
    public abstract boolean setStatus(String status);
}
