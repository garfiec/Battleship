package Java.com.garfiec.battleship.game;

public class Remote_Client extends Client {

    public Remote_Client() {
        super();
        client_type = Client_Type.REMOTE_CLIENT;
    }
    // Todo: Hook with UI
    // Todo: Socket connection to server (and connect to Remote_Player)
}
