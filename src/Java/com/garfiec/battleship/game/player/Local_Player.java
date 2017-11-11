package Java.com.garfiec.battleship.game.player;

import Java.com.garfiec.battleship.game.Game_Manager;
import Java.com.garfiec.battleship.game.board.ships.Ships;
import Java.com.garfiec.battleship.game.ui.Battleship_Display;
import Java.com.garfiec.battleship.game.util.Player_Type;

import java.awt.*;

public class Local_Player extends Player {

    Game_Manager gm;

    public Local_Player(Game_Manager gm) {
        player_type = Player_Type.LOCAL;
        this.gm = gm;
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
    public boolean addShip(Ships ship, Point cord) {
        return false;
    }

    // Game manager's hook to notify turn.
    @Override
    public void playersTurn() {
        // Todo: Tell player via UI to make a move
    }

    // GUI calls makeMove and returns whether successful
    @Override
    public boolean makeMove(byte x, byte y) {
        // Try to make a move (in case it's not player's turn)

        // Forward move back to Game_Manager
        gm.makeMove(player_type, x, y);
        return false;
    }
}
