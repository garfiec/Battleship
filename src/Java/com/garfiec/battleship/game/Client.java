package Java.com.garfiec.battleship.game;

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
}
