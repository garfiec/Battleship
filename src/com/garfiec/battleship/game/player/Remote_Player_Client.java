package com.garfiec.battleship.game.player;

import com.garfiec.battleship.game.Remote_Client;
import com.garfiec.battleship.game.board.ships.Ship_Orientation;
import com.garfiec.battleship.game.board.ships.Ships;
import com.garfiec.battleship.game.ui.Battleship_Display;
import com.garfiec.battleship.game.util.Connection_Settings;
import com.garfiec.battleship.game.util.Player_Type;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Remote_Player_Client extends Player {
    private Remote_Client cli;

    public Remote_Player_Client(Remote_Client cli) {
        player_type = Player_Type.REMOTE;
        this.cli = cli;

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

    @Override
    public void setConnectionSettings(Connection_Settings settings_obj) {
        this.connection_settings = settings_obj;
    }

    // Todo: Transmit message to Remote_Player_Server the ship to add and where
    @Override
    public boolean addShip(Ships ship, Ship_Orientation direction, Point cord) {
        System.out.println("Remote player added ship. Todo: transmit data to server.");
        return false;
    }

    // Transmit message back via socket
    public boolean makeMove(Point location) {
        // Todo: read above comment
        System.out.println("Remote player made move. Todo: transmit move to server.");
        return false;
    }

    @Override
    public boolean setStatus(String status) {
        guiMessageBuffer = status;
        return true;
    }
}
