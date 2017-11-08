package Java.com.garfiec.battleship.game.board;

import java.awt.*;

public class Attack_Board extends Map {
    // Map to track hits and and attack on enemy

    public Attack_Board() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                regions[y][x] = new Region();
            }
        }
    }
}
