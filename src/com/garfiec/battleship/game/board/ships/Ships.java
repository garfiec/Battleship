package com.garfiec.battleship.game.board.ships;

public enum Ships {
    AIRCRAFT_CARRIER(Ship_Consts.AIRCRAFT_CARRIER_NAME, Ship_Consts.AIRCRAFT_CARRIER_SIZE, Ship_Consts.AIRCRAFT_CARRIER_QUANTITY),
    BATTLESHIP(Ship_Consts.BATTLESHIP_NAME, Ship_Consts.BATTLESHIP_SIZE, Ship_Consts.BATTLESHIP_QUANTITY),
    DESTORYER(Ship_Consts.DESTORYER_NAME, Ship_Consts.DESTORYER_SIZE, Ship_Consts.DESTORYER_QUANTITY),
    SUBMARINE(Ship_Consts.SUBMARINE_NAME, Ship_Consts.SUBMARINE_SIZE, Ship_Consts.SUBMARINE_QUANTITY),
    PATROL_BOAT(Ship_Consts.PATROL_BOAT_NAME, Ship_Consts.PATROL_BOAT_SIZE, Ship_Consts.PATROL_BOAT_QUANTITY),
    GENERIC("Generic", (byte) 1, (byte) -1),
    NONE("", (byte) 0, (byte) -1);

    public final String type;
    public final byte size;
    public final byte quantity;

    Ships(String type, byte size, byte quantity) {
        this.type = type;
        this.size = size;
        this.quantity = quantity;
    }

    public Ships getShip(String type) {
        for (Ships s:Ships.values()) {
            if (s.type.equals(type)) {
                return s;
            }
        }

        // Ship type does not exist
        return null;
    }
}
