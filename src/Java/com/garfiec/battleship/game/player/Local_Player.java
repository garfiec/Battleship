package Java.com.garfiec.battleship.game.player;

import Java.com.garfiec.battleship.game.Game_Manager;
import Java.com.garfiec.battleship.game.board.ships.Ship_Orientation;
import Java.com.garfiec.battleship.game.board.ships.Ships;
import Java.com.garfiec.battleship.game.ui.Battleship_Display;
import Java.com.garfiec.battleship.game.util.Player_Type;

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

    // Todo: send game_manager ship to add and where
    @Override
    public boolean addShip(Ships ship, Ship_Orientation direction, Point cord) {
        return gm.addShip(this, ship, direction, cord);
    }

    // Game manager's hook to notify turn.
    @Override
    public void playersTurn() {
        // Todo: Tell player via UI to make a move
    }

    // GUI calls makeMove and returns whether successful
    @Override
    public boolean makeMove(byte x, byte y) {
        // Forward move back to Game_Manager
        return gm.makeMove(player_type, x, y);
    }
}
