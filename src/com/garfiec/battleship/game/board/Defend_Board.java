package com.garfiec.battleship.game.board;

import com.garfiec.battleship.game.board.ships.Ship_Orientation;
import com.garfiec.battleship.game.board.ships.Ships;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;

// Map of ship placements, list of occupied cords?, track enemy attacks on player
public class Defend_Board extends Map {
    // protected Region[][] regions = new Region[rows (y)][cols (x)];

    // Keeps track of # of ships existing on the board
    private HashMap<Ships, Integer> ship_roster = new HashMap<>();

    // Cache of ship regions not yet destroyed
    private HashSet<Point> active_regions = new HashSet<>();

    public Defend_Board() {
        type = BoardType.DEFEND_BOARD;

        // Initialize Regions
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                regions[y][x] = new Region();
            }
        }

        // Initialize list of ships
        for (Ships s:Ships.values()) {
            ship_roster.put(s, 0);
        }
    }

    public boolean addShip(Ships ship_type, Ship_Orientation direction, Point cord) {
        int currNumShips = ship_roster.get(ship_type);

        // Too many of this type of ship.
        if (currNumShips >= ship_type.quantity) {
            return false;
        }

        // Check if ship would go past map (cord is valid)
        if (cord.x < 0 || cord.x >= cols) { return false; }
        if (cord.y < 0 || cord.y >= rows) { return false; }
        if (direction == Ship_Orientation.HORIZONTAL) {
            if (cord.x + ship_type.size >= cols) { return false; }
        }
        else if (direction == Ship_Orientation.VERTICAL){
            if (cord.y + ship_type.size >= rows) { return false; }
        }
        else { return false; }

        // Check if ship overlaps another ship
        if (direction == Ship_Orientation.HORIZONTAL) {
            int tail = cord.x + ship_type.size;
            for (int x = cord.x; x < tail; x++) {
                if (regions[cord.y][x].isOccupied()) { return false; }
            }
        }
        else if (direction == Ship_Orientation.VERTICAL) {
            int tail = cord.y + ship_type.size;
            for (int y = cord.y; y < tail; y++) {
                if (regions[y][cord.x].isOccupied()) { return false; }
            }
        }

        // Error checking passed. Add ship
        ship_roster.put(ship_type, currNumShips+1);
        if (direction == Ship_Orientation.HORIZONTAL) {
            int tail = cord.x + ship_type.size;
            for (int x = cord.x; x < tail; x++) {
                regions[cord.y][x].setOccupancy(ship_type);
                active_regions.add(new Point(x, cord.y));
            }
        }
        else if (direction == Ship_Orientation.VERTICAL) {
            int tail = cord.y + ship_type.size;
            for (int y = cord.y; y < tail; y++) {
                regions[y][cord.x].setOccupancy(ship_type);
                active_regions.add(new Point(cord.x, y));
            }
        }

        return true;
    }

    // Attempt to make an attack at the region of player's board. If hit, flag ship as hit.
    // Returns whether the hit was successful
    public boolean attack(Point cord) {
        if (active_regions.remove(cord)) {
            regions[cord.y][cord.x].hit();
            return true;
        }

        return false;
    }

    public boolean isAlive() {
        return active_regions.size() > 0;
    }

    public Region getRegion(Point cord) {
        return regions[cord.y][cord.x];
    }

    public boolean isHit(Point cord) {
        return regions[cord.y][cord.x].isHit();
    }

    @Override
    public BoardType getType() {
        return type;
    }
}
