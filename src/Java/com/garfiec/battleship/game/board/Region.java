package Java.com.garfiec.battleship.game.board;

import Java.com.garfiec.battleship.game.board.ships.Ships;

public class Region {
    // Todo: Idk what needs to be stored here yet.
    // This is the individual cells on the map

    Ships   ship;
    boolean occupancyStatus;
    boolean hitStatus;

    public Region() {
        this.occupancyStatus = false;
        this.hitStatus = false;
    }

    public void setOccupancy(boolean status) {
        this.occupancyStatus = status;
    }

    public void setOccupancy(Ships ship_type) {
        // Implied to be occupied
        this.occupancyStatus = true;
        this.ship = ship_type;
    }

    public void setShip(Ships s) {
        this.ship = s;
    }

    public boolean hit() {
        if (hitStatus) return false; // Already hit

        hitStatus = true;
        return true;
    }

    public boolean isOccupied() {
        return this.occupancyStatus;
    }

    public boolean isHit() {
        return this.hitStatus;
    }
}
