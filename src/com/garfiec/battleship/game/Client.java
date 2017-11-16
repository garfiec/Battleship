package com.garfiec.battleship.game;

import com.garfiec.battleship.game.board.ships.Ship_Orientation;
import com.garfiec.battleship.game.board.ships.Ships;
import com.garfiec.battleship.game.player.Player;
import com.garfiec.battleship.game.ui.Battleship_Display;
import com.garfiec.battleship.game.util.Connection_Settings;
import com.garfiec.battleship.game.util.Player_Type;

import java.awt.*;

// Generic Client
public class Client {
    protected Connection_Settings connection_settings;

    public enum Client_Type {
        GAME_MANAGER,
        REMOTE_CLIENT,
        NONE
    }

    public Client_Type client_type;

    public Client() {
        client_type = Client_Type.NONE;
        connection_settings = new Connection_Settings();
    }

    public Client_Type getClientType() {
        return client_type;
    }

    public Connection_Settings getConnectionSettings() {
        return connection_settings;
    }

    // Todo: Abstract methods to hook to UI
    public Player getLocalPlayer() {
        return new Player() {
            @Override
            public Player_Type getPlayerType() {
                return null;
            }

            @Override
            public void setUIHook(Battleship_Display ui) {

            }

            @Override
            public void setConnectionSettings(Connection_Settings settings_obj) {

            }

            @Override
            public boolean addShip(Ships ship, Ship_Orientation direction, Point cord) {
                return false;
            }

            @Override
            public boolean makeMove(Point location) {
                return false;
            }

            @Override
            public boolean setStatus(String status) {
                return false;
            }
        };
    }

}
