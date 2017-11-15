package com.garfiec.battleship.game.player;

import com.garfiec.battleship.game.Game_Manager;
import com.garfiec.battleship.game.board.ships.Ship_Orientation;
import com.garfiec.battleship.game.board.ships.Ships;
import com.garfiec.battleship.game.ui.Battleship_Display;
import com.garfiec.battleship.game.util.Player_Type;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Local_Player extends Player {

    private Game_Manager gm;

    public Local_Player(Game_Manager gm) {
        player_type = Player_Type.LOCAL;
        this.gm = gm;

        // Send status messages to GUI
        TimerTask sendStatus = new TimerTask() {
            @Override
            public void run() {
                if (ui != null && guiMessageBuffer != null) {
                    ui.setStatus(guiMessageBuffer);
                    guiMessageBuffer = null;
                }
            }
        };
        Timer statusSender = new Timer("StatusSender");
        statusSender.schedule(sendStatus, 0, 100);
    }

    @Override
    public Player_Type getPlayerType() {
        return player_type;
    }

    public void setUIHook(Battleship_Display ui) {
        this.ui = ui;
    }

    // Send game_manager ship to add and where
    @Override
    public boolean addShip(Ships ship, Ship_Orientation direction, Point location) {
        return gm.addShip(this, ship, direction, location);
    }

    // GUI calls makeMove and returns whether successful
    @Override
    public boolean makeMove(Point location) {
        // Forward move back to Game_Manager
        System.out.println("Local player made move (" + location.x + ", " + location.y + ")");
        return gm.makeMove(player_type, location);
    }

    @Override
    public boolean setStatus(String status) {
        guiMessageBuffer = status;
        return true;
    }
}
