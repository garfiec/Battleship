package com.garfiec.battleship.game.ui.etc;

import com.garfiec.battleship.game.util.Game_Settings;

import javax.swing.*;
import java.awt.*;

public class Connection_Settings_Display extends JFrame{

    private Game_Settings settings;

    public Connection_Settings_Display (Game_Settings settings) {
        super("Connection Settings");
        this.settings = settings;
        getContentPane().setLayout(new BorderLayout());

        // Todo: Load current settings on to UI
        // Todo: Create connection settings UI
        // Todo: On update value on gui, change the corresponding values on settings object

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
    }
}
