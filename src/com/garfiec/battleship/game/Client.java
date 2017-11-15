package com.garfiec.battleship.game;

import com.garfiec.battleship.game.board.ships.Ship_Orientation;
import com.garfiec.battleship.game.board.ships.Ships;
import com.garfiec.battleship.game.player.Player;
import com.garfiec.battleship.game.ui.Battleship_Display;
import com.garfiec.battleship.game.util.Player_Type;

import java.awt.*;

// Generic Client
public class Client {
    public enum Client_Type {
        GAME_MANAGER,
        REMOTE_CLIENT,
        NONE
    }

    public Client_Type client_type;

    public Client() {
        client_type = Client_Type.NONE;
    }

    public Client_Type getClientType() {
        return client_type;
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
            public void doAddships() {

            }

            @Override
            public boolean addShip(Ships ship, Ship_Orientation direction, Point cord) {
                return false;
            }

            @Override
            public void playersTurn() {

            }

            @Override
            public boolean makeMove(Point location) {
                return false;
            }

            @Override
            public void announceWin(Player_Type player) {

            }

            @Override
            public boolean setStatus(String status) {
                return false;
            }
        };
    }

}
