package com.garfiec.battleship.game;

import com.garfiec.battleship.game.player.Player;
import com.garfiec.battleship.game.player.Remote_Player_Client;

public class Remote_Client extends Client {

    private Player remote_player;

    public Remote_Client() {
        super();
        client_type = Client_Type.REMOTE_CLIENT;

        initializeClient();
    }

    public void initializeClient() {
        remote_player = new Remote_Player_Client(this);
    }

    @Override
    public Player getLocalPlayer() {
        return remote_player;
    }
}
