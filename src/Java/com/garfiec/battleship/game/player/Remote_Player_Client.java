package Java.com.garfiec.battleship.game.player;

import Java.com.garfiec.battleship.game.Remote_Client;
import Java.com.garfiec.battleship.game.board.ships.Ship_Orientation;
import Java.com.garfiec.battleship.game.board.ships.Ships;
import Java.com.garfiec.battleship.game.ui.Battleship_Display;
import Java.com.garfiec.battleship.game.util.Player_Type;

import java.awt.*;

public class Remote_Player_Client extends Player {
    private Remote_Client cli;

    public Remote_Player_Client(Remote_Client cli) {
        player_type = Player_Type.REMOTE;
        this.cli = cli;
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
        return false;
    }

    // Receive message via socket that it's player's turn. Tell player via UI to make move
    public void playersTurn() {
        // Todo: read above comment
    }

    // Transmit message back via socket
    public boolean makeMove(byte x, byte y) {
        // Todo: read above comment
        return false;
    }
}
