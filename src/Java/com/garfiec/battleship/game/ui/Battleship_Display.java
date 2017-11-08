package Java.com.garfiec.battleship.game.ui;

import javax.swing.*;

import Java.com.garfiec.battleship.game.Client;
import Java.com.garfiec.battleship.game.Game_Manager;
import Java.com.garfiec.battleship.game.Remote_Client;
import Java.com.garfiec.battleship.game.util.Game_Consts;
import Java.com.garfiec.battleship.game.util.Game_Strings;

import java.awt.*;

public class Battleship_Display extends JFrame {
    // Todo: Reference to client object controlling UI
    // Ex. Client game_client = new Game_Manager() or Remote_Client();
    Client game_client;

    public Battleship_Display()  {
        super(Game_Strings.GUI_TITLE);
        getContentPane().setLayout(new BorderLayout());

        createMenu();
        showClientPicker();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(UI_Constants.WIDTH, UI_Constants.HEIGHT);
        setVisible(true);

    }

    private void showClientPicker() {
        Object[] options = {"Game Server", "Remote Client"};
        Object result = JOptionPane.showInputDialog(this,
                "Please choose a client to use",
                "Select client",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );
        if (result == options[0]) {
            game_client = new Game_Manager();
            resetGUI();
        }
        else {
            game_client = new Remote_Client();
            resetGUI();
        }
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();

        // Vars
        JMenu menu;
        JMenuItem menuItem;

        // File menu
        menu = new JMenu("File");
        menuBar.add(menu);

        menuItem = new JMenuItem("New Game");
        menuItem.addActionListener(e -> {
            // Todo: Request new game
            System.out.println("TODO: Request New Game");
        });
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Set as Server");
        menuItem.addActionListener(e -> {
            if (game_client.client_type == Client.Client_Type.GAME_MANAGER) {
                JOptionPane.showMessageDialog(this, "You're already set up as the server!");
            }
            else {
                int result = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to switch to server mode?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION
                );
                if (result == 0) {
                    game_client = new Game_Manager();
                    resetGUI();
                }
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Set as Remote Client");
        menuItem.addActionListener(e -> {
            if (game_client.client_type == Client.Client_Type.REMOTE_CLIENT) {
                JOptionPane.showMessageDialog(this, "You're already the remote client!");
            }
            else {
                int result = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to switch to remote client mode?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION
                );
                if (result == 0) {
                    game_client = new Remote_Client();
                    resetGUI();
                }
            }
        });
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Connection Settings");
        menuItem.addActionListener(e -> {
            // TODO: Connection Settings
        });
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(e -> System.exit(0));
        menu.add(menuItem);

        // Game menu
        menu = new JMenu("Game");
        menuBar.add(menu);

        menuItem = new JMenuItem("Statistics");
        menuItem.addActionListener(e -> {
            // TODO: Report statistics
        });
        menu.add(menuItem);

        // Help menu
        menu = new JMenu("Help");
        menuBar.add(menu);

        menuItem = new JMenuItem("How to play");
        menuItem.addActionListener(e -> JOptionPane.showMessageDialog(this, UI_Strings.HOW_TO_PLAY));
        menu.add(menuItem);

        menuItem = new JMenuItem("How to use");
        menuItem.addActionListener(e -> JOptionPane.showMessageDialog(this, UI_Strings.HOW_TO_USE));
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("About");
        menuItem.addActionListener(e -> JOptionPane.showMessageDialog(this, UI_Strings.ABOUT));
        menu.add(menuItem);

        this.setJMenuBar(menuBar);
    }

    private void resetGUI() {
        // Todo: reset the gui to starting state
    }

    // Todo: Create view for player's attack board and defend board.
    // Todo: onRegionClick pass attack coordinate to client -> player.makeMove (client passes coordinates to player, which makes a move)
}
