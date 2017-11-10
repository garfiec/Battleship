package Java.com.garfiec.battleship.game.ui.etc;

import Java.com.garfiec.battleship.game.board.Region;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cell extends JButton {
    private int x, y;
    private Region region;

    Cell( /*Region reg,*/ int _x, int _y) {
        //region = reg;   delete comment once we can reference our regions
        x = _x;
        y = _y;
        //addActionListener(new cellListener());
        //addActionListener(e -> player.makeMove(x, y));
    }

}
