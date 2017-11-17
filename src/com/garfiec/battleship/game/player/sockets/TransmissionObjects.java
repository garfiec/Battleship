package com.garfiec.battleship.game.player.sockets;

import com.garfiec.battleship.game.board.ships.Ship_Orientation;
import com.garfiec.battleship.game.board.ships.Ships;

import java.awt.*;

public class TransmissionObjects {
    public String targetMethod;

    public Ships ship;
    public Ship_Orientation orientation;
    public Point location;
    public String message;

    public boolean returnStatus;

    public TransmissionObjects(String target) {
        this.targetMethod = target;
    }

}
