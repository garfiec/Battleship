package com.garfiec.battleship.game.player;

import com.garfiec.battleship.game.Remote_Client;
import com.garfiec.battleship.game.board.ships.Ship_Orientation;
import com.garfiec.battleship.game.board.ships.Ships;
import com.garfiec.battleship.game.player.sockets.TransmissionObjects;
import com.garfiec.battleship.game.ui.Battleship_Display;
import com.garfiec.battleship.game.util.Connection_Settings;
import com.garfiec.battleship.game.util.Player_Type;

import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Remote_Player_Client extends Player {
    private Remote_Client cli;

    Socket clientSocket;

    ObjectOutputStream outStream;
    ObjectInputStream inStream;

    Client_Thread client_thread;
    Thread thread;

    public Remote_Player_Client(Remote_Client cli) {
        player_type = Player_Type.REMOTE;
        this.cli = cli;


        // Send status messages to GUI
        TimerTask sendStatus = new TimerTask() {
            @Override
            public void run() {
                if (ui != null && guiMessageBuffer != null) {
                    ui.setStatus(guiMessageBuffer);
                    guiMessageBuffer = null;
                }
            }
        };
        Timer statusSender = new Timer("StatusSender");
        statusSender.schedule(sendStatus, 0, 100);

        // Create client
        client_thread = new Client_Thread();
        thread = new Thread(client_thread);
    }

    @Override
    public Player_Type getPlayerType() {
        return player_type;
    }

    public void setUIHook(Battleship_Display ui) {
        this.ui = ui;
    }

    @Override
    public void setConnectionSettings(Connection_Settings settings_obj) {
        this.connection_settings = settings_obj;
    }

    @Override
    public boolean addShip(Ships ship, Ship_Orientation direction, Point cord) {
        TransmissionObjects obj = new TransmissionObjects("addShip");
        obj.ship = ship;
        obj.orientation = direction;
        obj.location = cord;

        sendObject(obj);
        return false;
    }

    // Transmit message back via socket
    public boolean makeMove(Point location) {
        TransmissionObjects obj = new TransmissionObjects("makeMove");
        obj.location = location;

        sendObject(obj);
        return false;
    }

    @Override
    public boolean setStatus(String status) {
        guiMessageBuffer = status;
        return true;
    }

    private boolean sendObject(TransmissionObjects obj) {
        try {
            outStream.writeObject(obj);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    private class Client_Thread implements Runnable {
        private void getObject() {
            try {
                TransmissionObjects obj = (TransmissionObjects) inStream.readObject();
                switch (obj.targetMethod) {
                    case "setStatus":
                        setStatus(obj.message);
                        break;
                }
                System.out.println("Received object");
            } catch (Exception ex) {
                // N/a
            }
        }

        @Override
        public void run() {
            try {
                clientSocket = new Socket(connection_settings.server_host, connection_settings.server_port);

                // Set streams
                outStream = new ObjectOutputStream(clientSocket.getOutputStream());
                inStream = new ObjectInputStream(clientSocket.getInputStream());
            } catch (Exception ex) {
                System.out.println("Failed to connect");
            }

            // Get object loop
            while (true) {
                getObject();
            }
        }
    }
}
