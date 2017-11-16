package com.garfiec.battleship.game.ui.etc;

import com.garfiec.battleship.game.util.Connection_Settings;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Connection_Settings_Display extends JFrame{

    private Connection_Settings settings;

    private JPanel createServerSettings() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("Server Settings"));

        JPanel contentPanel = new JPanel();
        BoxLayout layout = new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS);
        contentPanel.setLayout(layout);
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(contentPanel);


        JLabel serverPortLabel = new JLabel("Server port: ");
        contentPanel.add(serverPortLabel, LEFT_ALIGNMENT);

        JTextField serverPortText = new JTextField(Integer.toString(settings.server_port));
        serverPortText.setColumns(1);
        contentPanel.add(serverPortText, LEFT_ALIGNMENT);

        JButton saveButton = new JButton("Save Setting");
        saveButton.addActionListener(e -> {
            try {
                int port = Integer.parseInt(serverPortText.getText());
                if (port < 1000 || port > 65535) {
                    JOptionPane.showMessageDialog(this, "Please enter a port number greater than 1000 but less than 65535");
                    serverPortText.setText(Integer.toString(settings.server_port));
                }
                else {
                    settings.server_port = port;
                }
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error. Please enter a valid port number");
                serverPortText.setText(Integer.toString(settings.server_port));
            }
        });
        contentPanel.add(saveButton, LEFT_ALIGNMENT);

        return panel;
    }

    private JPanel createClientSettings() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("Client Settings"));

        JPanel contentPanel = new JPanel();
        BoxLayout layout = new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS);
        contentPanel.setLayout(layout);
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(contentPanel, BorderLayout.CENTER);

        JButton connectButton = new JButton("Connect to Server");
        contentPanel.add(connectButton, LEFT_ALIGNMENT);

        JLabel clientHelpLabel = new JLabel("<html><p>If you are a remote client and want to connect to a Battleship server: </p><p style=\"margin-left:10px;margin-top:5px;\">First enter the server's port on the left then click &quot;Connect to Server&quot;");
        contentPanel.add(clientHelpLabel, LEFT_ALIGNMENT);

        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel statusLabel = new JLabel("Not connected");
        statusLabel.setHorizontalAlignment(JLabel.RIGHT);
        statusPanel.add(statusLabel);

        panel.add(statusPanel, BorderLayout.SOUTH);

        return panel;
    }

    public Connection_Settings_Display (Connection_Settings settings) {
        super("Connection Settings");
        this.settings = settings;
        getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        BoxLayout layout = new BoxLayout(panel, BoxLayout.LINE_AXIS);
        panel.setLayout(layout);

        panel.add(createServerSettings());
        panel.add(createClientSettings());


        // Todo: Load current settings on to UI
        // Todo: On update value on gui, change the corresponding values on settings object

        add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 250);
        setVisible(true);
    }
}
