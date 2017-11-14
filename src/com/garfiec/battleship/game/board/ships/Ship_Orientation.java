package com.garfiec.battleship.game.board.ships;

public enum Ship_Orientation {
    HORIZONTAL("Horizontal"),
    VERTICAL("Vertical");

    public final String direction_name;

    Ship_Orientation(String direction_name) {
        this.direction_name = direction_name;
    }

    public static Ship_Orientation getOrientation(String direction) {
        for (Ship_Orientation s:Ship_Orientation.values()) {
            if (s.direction_name.equals(direction)) {
                return s;
            }
        }

        // Ship type does not exist
        return null;
    }

    public String getDirectionName() {
        return direction_name;
    }
}
