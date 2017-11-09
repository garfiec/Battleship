package Java.com.garfiec.battleship.game;

import Java.com.garfiec.battleship.game.player.Player;
import Java.com.garfiec.battleship.game.ui.Battleship_Display;

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
    public Player getLocalPlayer(Player player) {
        return new Player() {
            @Override
            public void setUIHook(Battleship_Display ui) {

            }

            @Override
            public void playersTurn() {

            }

            @Override
            public boolean makeMove(byte x, byte y) {
                return false;
            }
        };
    }

}
