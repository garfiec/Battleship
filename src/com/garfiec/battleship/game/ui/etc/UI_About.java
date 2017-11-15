package com.garfiec.battleship.game.ui.etc;

import com.garfiec.battleship.game.ui.UI_Strings;
import com.garfiec.battleship.game.util.Game_Strings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import java.net.URL;

public class UI_About extends JFrame {
    public UI_About() {
        super("About " + UI_Strings.GUI_TITLE);
        getContentPane().setLayout(new BorderLayout());

        JPanel pane = new JPanel();
        BoxLayout bl = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(bl);
        pane.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel contributor_label = new JLabel(UI_Strings.ABOUT);
        pane.add(contributor_label);

        URL image_url = this.getClass().getResource("/com/garfiec/battleship/game/ui/assets/2011.11.15_building_software.png");
        ImageIcon easterEgg = new ImageIcon(image_url);
        JLabel img = new JLabel(easterEgg);
        img.setPreferredSize(new Dimension(100, 100));
        img.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(UI_Strings.IMAGE_SRC));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, UI_Strings.IMAGE_SRC);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        pane.add(img);

        JLabel img_credit = new JLabel(UI_Strings.IMAGE_CRED);
        pane.add(img_credit);

        getContentPane().add(pane, BorderLayout.CENTER);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(550, 500);
        setVisible(true);
    }
}
