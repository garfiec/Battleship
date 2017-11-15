package com.garfiec.battleship.game.player;

import com.garfiec.battleship.game.Remote_Client;
import com.garfiec.battleship.game.board.ships.Ship_Orientation;
import com.garfiec.battleship.game.board.ships.Ships;
import com.garfiec.battleship.game.ui.Battleship_Display;
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

    // Todo: Tell Gui it's time to set up the board
    @Override
    public void doAddships() {

    }

    // Todo: Transmit message to Remote_Player_Server the ship to add and where
    @Override
    public boolean addShip(Ships ship, Ship_Orientation direction, Point cord) {
        System.out.println("Remote player added ship. Todo: transmit data to server.");
        return false;
    }

    // Receive message via socket that it's player's turn. Tell player via UI to make move
    public void playersTurn() {
        // Todo: read above comment
    }

    // Transmit message back via socket
    public boolean makeMove(Point location) {
        // Todo: read above comment
        System.out.println("Remote player made move. Todo: transmit move to server.");
        return false;
    }

    // Tell GUI that a player has won
    @Override
    public void announceWin(Player_Type player) {
        // Todo
    }

    @Override
    public boolean setStatus(String status) {
        guiMessageBuffer = status;
        return true;
    }
}
