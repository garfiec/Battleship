package Java.com.garfiec.battleship.game.util;

public enum Player_Type {
    LOCAL(0),
    REMOTE(1);

    public final int index;

    Player_Type(int index) {
        this.index = index;
    }
}
