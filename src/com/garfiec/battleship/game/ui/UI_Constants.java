package com.garfiec.battleship.game.ui;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.awt.*;

public class UI_Constants {
    public static final int WIDTH   = 900;
    public static final int HEIGHT  = 500;

    public static final int TILE_GAP_H = 3;
    public static final int TILE_GAP_V = 3;

    public static final Color BOARD_LABEL_COLOR = new Color(255, 255, 255);

    public static final Color BG_COLOR          = new Color(66, 71, 79);
    public static final Color CONTROLS_BG_COLOR = new Color(44, 46, 52);

    // Tile Colors
    public static final Color BLANK_COLOR   = new Color(6, 61, 163);
    public static final Color SHIP_COLOR    = new Color(91, 91, 91);

    // Hit Colors
    public static final Color HIT_COLOR     = new Color(255, 0, 0);
    public static final Color MISS_COLOR    = new Color(255, 255, 255);
}
