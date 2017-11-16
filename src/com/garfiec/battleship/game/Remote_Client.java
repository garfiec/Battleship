package com.garfiec.battleship.game;

import com.garfiec.battleship.game.player.Player;
import com.garfiec.battleship.game.player.Remote_Player_Client;
import com.garfiec.battleship.game.util.Connection_Settings;

public class Remote_Client extends Client {
    private Player remote_player;

    public Remote_Client() {
        super();
        client_type = Client_Type.REMOTE_CLIENT;
        connection_settings = new Connection_Settings();

        initializeClient();
    }

    public void initializeClient() {
        remote_player = new Remote_Player_Client(this);
        remote_player.setConnectionSettings(connection_settings);
    }

    @Override
    public Player getLocalPlayer() {
        return remote_player;
    }
}
