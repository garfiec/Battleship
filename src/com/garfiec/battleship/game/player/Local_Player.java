package com.garfiec.battleship.game.player;

import com.garfiec.battleship.game.Game_Manager;
import com.garfiec.battleship.game.board.ships.Ship_Orientation;
import com.garfiec.battleship.game.board.ships.Ships;
import com.garfiec.battleship.game.ui.Battleship_Display;
import com.garfiec.battleship.game.util.Player_Type;

import java.awt.*;

public class Local_Player extends Player {

    private Game_Manager gm;

    public Local_Player(Game_Manager gm) {
        player_type = Player_Type.LOCAL;
        this.gm = gm;
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

    // Send game_manager ship to add and where
    @Override
    public boolean addShip(Ships ship, Ship_Orientation direction, Point location) {
        System.out.println("Local player added ship (" + location.x + ", " + location.y + ") Direction: " + direction.direction_name + " Ship: " + ship.getName());
        return gm.addShip(this, ship, direction, location);
    }

    // Game manager's hook to notify turn.
    @Override
    public void playersTurn() {
        // Todo: Tell player via UI to make a move
    }

    // GUI calls makeMove and returns whether successful
    @Override
    public boolean makeMove(Point location) {
        // Forward move back to Game_Manager
        System.out.println("Local player made move (" + location.x + ", " + location.y + ")");
        return gm.makeMove(player_type, location);
    }

    // Tell GUI that a player has won
    @Override
    public void announceWin(Player_Type player) {
        // Todo
    }

    @Override
    public void setStatus(String status) {
        ui.setStatus(status);
    }
}
