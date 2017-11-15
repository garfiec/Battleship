package com.garfiec.battleship.game.ui.etc;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MessageBox extends JFrame {
    public MessageBox(String title, String message, Dimension dimensions) {
        super(title);

        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(JLabel.LEFT);
        messageLabel.setVerticalAlignment(JLabel.TOP);
        panel.add(messageLabel);

        getContentPane().add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(dimensions);
        setVisible(true);
    }
}
