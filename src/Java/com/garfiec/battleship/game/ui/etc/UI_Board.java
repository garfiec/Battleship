package Java.com.garfiec.battleship.game.ui.etc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI_Board extends JPanel {

    public UI_Board() { //*** TODO add attack/defense board as param TODO ***//
        super();
        setLayout(new GridLayout(9,9));
        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                add(new Cell(row,col));
            }
        } // end loop
    }

}
