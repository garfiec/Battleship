package com.garfiec.battleship.game.util;

public class Connection_Settings {
    // Connection: Server
    public int server_port;

    // Connection: Remote
    public String server_host;

    public Connection_Settings() {
        // Initialize settings
        server_port = 9000;
        server_host = "localhost";
    }
}
