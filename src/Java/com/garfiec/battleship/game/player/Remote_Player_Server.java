package Java.com.garfiec.battleship.game.player;

import Java.com.garfiec.battleship.game.Game_Manager;
import Java.com.garfiec.battleship.game.board.ships.Ship_Orientation;
import Java.com.garfiec.battleship.game.board.ships.Ships;
import Java.com.garfiec.battleship.game.ui.Battleship_Display;
import Java.com.garfiec.battleship.game.util.Player_Type;

import java.awt.*;

public class Remote_Player_Server extends Player {
    private Game_Manager gm;

    public Remote_Player_Server(Game_Manager gm) {
        player_type = Player_Type.REMOTE;
        this.gm = gm;
    }

    @Override
    public Player_Type getPlayerType() {
        return player_type;
    }

    @Override
    public void setUIHook(Battleship_Display ui) {
        // Do nothing. Server only
    }

    // Todo: Transmit message to Remote_Player_Client that it's time to set up the board
    @Override
    public void doAddships() {

    }

    @Override
    public boolean addShip(Ships ship, Ship_Orientation direction, Point cord) {
        return gm.addShip(this, ship, direction, cord);
    }

    // Send message to remote gui to make a move (Transmit)
    @Override
    public void playersTurn() {
        // Todo: Transmit message to remote client indicating that it's the player's turn
    }

    // When received response from socket, passes move to game manager (Receive and pass back)
    @Override
    public boolean makeMove(byte x, byte y) {
        return gm.makeMove(player_type, x, y);
    }

    // Transmit message to client that a player has won
    @Override
    public void announceWin(Player_Type player) {
        // Todo
    }

    // Todo: Socket listening for moves. Implement runnable to prevent program from locking up while attempting to communicate
    // Todo: Translate incoming message into a move
    // Todo: Transmit message remote client
}
