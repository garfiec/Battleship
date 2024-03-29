package com.garfiec.battleship.game.board;

import java.awt.*;

public class Attack_Board extends Map {
    // Map to track hits and and attack on enemy

    public Attack_Board() {
        type = BoardType.ATTACK_BOARD;

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                regions[y][x] = new Region();
            }
        }
    }
    
    public Region getRegion(Point cord) {
        return regions[cord.y][cord.x];
    }

    public void attack(Point cord, boolean isOccupied) {
        regions[cord.y][cord.x].setOccupancy(isOccupied);
        regions[cord.y][cord.x].hit();
    }

    @Override
    public BoardType getType() {
        return type;
    }

    // Returns whether or not a move was made at position before
    public boolean madeMoveAt(Point cord) {
        return regions[cord.y][cord.x].isHit();
    }
}
