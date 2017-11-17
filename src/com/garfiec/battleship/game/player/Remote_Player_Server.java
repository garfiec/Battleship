package com.garfiec.battleship.game.player;

import com.garfiec.battleship.game.Game_Manager;
import com.garfiec.battleship.game.board.ships.Ship_Orientation;
import com.garfiec.battleship.game.board.ships.Ships;
import com.garfiec.battleship.game.player.sockets.TransmissionObjects;
import com.garfiec.battleship.game.ui.Battleship_Display;
import com.garfiec.battleship.game.util.Connection_Settings;
import com.garfiec.battleship.game.util.Player_Type;

import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Remote_Player_Server extends Player {
    private Game_Manager gm;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    ObjectOutputStream outStream;
    ObjectInputStream inStream;

    Server_Thread server_thread;
    Thread thread;

    public Remote_Player_Server(Game_Manager gm) {
        player_type = Player_Type.REMOTE;
        this.gm = gm;

        // Create server
        server_thread = new Server_Thread();
        thread = new Thread(server_thread);
    }

    @Override
    public Player_Type getPlayerType() {
        return player_type;
    }

    @Override
    public void setUIHook(Battleship_Display ui) {
        // Do nothing. Server only
    }

    private void startNewConnection() {
        thread.start();
    }

    @Override
    public void setConnectionSettings(Connection_Settings settings_obj) {
        this.connection_settings = settings_obj;
        startNewConnection();
    }

    @Override
    public boolean addShip(Ships ship, Ship_Orientation direction, Point cord) {
        return gm.addShip(this, ship, direction, cord);
    }

    // When received response from socket, passes move to game manager (Receive and pass back)
    @Override
    public boolean makeMove(Point location) {
        return gm.makeMove(player_type, location);
    }

    // Transmit data to remote client
    @Override
    public boolean setStatus(String status) {
        // Construct object
        TransmissionObjects obj = new TransmissionObjects("setStatus");
        obj.message = status;

        // Send object
        sendObject(obj);
        return false;
    }

    private boolean sendObject(TransmissionObjects obj) {
        try {
            outStream.writeObject(obj);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    private class Server_Thread implements Runnable {
        private void getObject() {
            try {
                TransmissionObjects obj = (TransmissionObjects) inStream.readObject();
                switch (obj.targetMethod) {
                    case "addShip":
                        addShip(obj.ship, obj.orientation, obj.location);
                        break;
                    case "makeMove":
                        makeMove(obj.location);
                        break;
                }
            } catch (Exception ex) {
                // N/a
            }
        }

        @Override
        public void run() {
            // Close connection if exists
            try {
                serverSocket.close();
                System.out.println("Connection is closed");
            }
            catch (Exception ex) {
                System.out.println("Could not close connection");
            }

            // Create socket and establish connection
            try {
                serverSocket = new ServerSocket(connection_settings.server_port);
                clientSocket = serverSocket.accept();
                System.out.println("New connection established");

                // Set streams
                outStream = new ObjectOutputStream(clientSocket.getOutputStream());
                inStream = new ObjectInputStream(clientSocket.getInputStream());
            } catch (Exception ex) {
                System.out.println("Failed to create a server connection");
            }

            // Get object loop
            while (true) {
                getObject();
            }
        }
    }

    // Todo: Socket listening for moves. Implement runnable to prevent program from locking up while attempting to communicate
    // Todo: Translate incoming message into a move
    // Todo: Transmit message remote client
}
