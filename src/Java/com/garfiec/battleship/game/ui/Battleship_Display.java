package Java.com.garfiec.battleship.game.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Java.com.garfiec.battleship.game.Client;
import Java.com.garfiec.battleship.game.Game_Manager;
import Java.com.garfiec.battleship.game.Remote_Client;
import Java.com.garfiec.battleship.game.board.Map;
import Java.com.garfiec.battleship.game.board.ships.Ship_Orientation;
import Java.com.garfiec.battleship.game.board.ships.Ships;
import Java.com.garfiec.battleship.game.player.Player;
import Java.com.garfiec.battleship.game.ui.etc.Connection_Settings_Display;
import Java.com.garfiec.battleship.game.ui.etc.UI_About;
import Java.com.garfiec.battleship.game.util.Game_Consts;
import Java.com.garfiec.battleship.game.util.Game_Settings;
import Java.com.garfiec.battleship.game.util.Game_Strings;

import java.awt.*;

public class Battleship_Display extends JFrame {
    // Settings object
    private Game_Settings settings;

    // Reference to client object controlling UI
    private Client game_client;

    // Reference to player object associated with GUI
    private Player player;

    // Game vars
    private Ship_Orientation    toAdd_ShipOrientation;
    private Ships               toAdd_Ship;

    public Battleship_Display()  {
        super(Game_Strings.GUI_TITLE);
        getContentPane().setLayout(new BorderLayout());

        // Set panes
        JPanel boardPanel = new JPanel(new BorderLayout());
        UI_Board defendPanel = new UI_Board(Map.BoardType.DEFEND_BOARD);
        UI_Board attackPanel = new UI_Board(Map.BoardType.ATTACK_BOARD);

        boardPanel.add(defendPanel, BorderLayout.LINE_START);
        boardPanel.add(attackPanel,BorderLayout.LINE_END);
        add(boardPanel, BorderLayout.CENTER);

        // Initialization
        settings = new Game_Settings();
        createMenu();
        showClientPicker();

        this.setLocation(100, 100); // Temp fix to spawn issue
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(UI_Constants.WIDTH, UI_Constants.HEIGHT);
        setVisible(true);

    }

    private boolean pickClient(Client.Client_Type type) {
        if (type == Client.Client_Type.NONE) {
            return false;
        }

        if (type == Client.Client_Type.GAME_MANAGER) {
            game_client = new Game_Manager();

        }
        else if (type == Client.Client_Type.REMOTE_CLIENT) {
            game_client = new Remote_Client();
        }

        player = game_client.getLocalPlayer();
        return true;
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
            pickClient(Client.Client_Type.GAME_MANAGER);
            resetGUI();
        }
        else {
            pickClient(Client.Client_Type.REMOTE_CLIENT);
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
                    pickClient(Client.Client_Type.GAME_MANAGER);
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
                    pickClient(Client.Client_Type.REMOTE_CLIENT);
                    resetGUI();
                }
            }
        });
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Connection Settings");
        menuItem.addActionListener(e -> {
            // TODO: Connection Settings (Show settings dialog)
            //JDialog dialog = new JDialog(this, true);
            //dialog.add(new Connection_Settings_Display(settings));
            JDialog dialog = new JDialog(new Connection_Settings_Display(settings));
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
        menuItem.addActionListener(e -> new JDialog(new UI_About()));
        menu.add(menuItem);

        this.setJMenuBar(menuBar);
    }

    private void resetGUI() {
        // Todo: reset the gui to default starting state
        toAdd_ShipOrientation = Ship_Orientation.HORIZONTAL;
        toAdd_Ship = Ships.NONE;
    }

    private class UI_Board extends JPanel {
        private JButton[][] regions = new JButton[Game_Consts.ROWS][Game_Consts.COLUMNS]; // [y][x]

        private Map.BoardType board_type;
        private String board_name = "None";

        private JPanel createBoard() {
            JPanel board_panel = new JPanel();
            board_panel.setLayout(new GridLayout(Game_Consts.ROWS, Game_Consts.COLUMNS, UI_Constants.TILE_GAP_H, UI_Constants.TILE_GAP_V));
            board_panel.setBorder(new EmptyBorder(5, 5, 0, 5));

            for(int col = 0; col < Game_Consts.COLUMNS; col++) {
                for(int row = 0; row < Game_Consts.ROWS; row++) {
                    final byte x = (byte) col;
                    final byte y = (byte) row;

                    JButton region = new JButton();
                    region.setBackground(new Color(214, 230, 255));

                    region.addActionListener(e -> {
                        // Respond to click
                        if (board_type == Map.BoardType.ATTACK_BOARD) {
                            // Make Move
                            player.makeMove(x, y);
                        } else if (board_type == Map.BoardType.DEFEND_BOARD) {
                            // Add ship
                            player.addShip(toAdd_Ship, toAdd_ShipOrientation, new Point(x, y));
                        }

                        // Update logic
                        // Todo: Update graphics for cell
                    });
                    regions[y][x] = region;
                    board_panel.add(region);
                }
            } // end loop
            return board_panel;
        }

        private JPanel createBoardLabel() {
            JPanel label_panel = new JPanel();
            label_panel.setLayout(new BorderLayout());
            label_panel.setBorder(new EmptyBorder(5, 5, 5, 5));

            JLabel label = new JLabel(board_name);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            label_panel.add(label, BorderLayout.CENTER);

            return label_panel;
        }

        private void createUI() {
            this.setLayout(new BorderLayout());

            JPanel board = createBoard();
            this.add(board, BorderLayout.CENTER);

            JPanel board_label = createBoardLabel();
            this.add(board_label, BorderLayout.SOUTH);
        }

        public UI_Board(Map.BoardType board_type) {
            super();
            this.board_type = board_type;
            if (board_type == Map.BoardType.ATTACK_BOARD) {
                board_name = "Attack Board";
            }
            else if (board_type == Map.BoardType.DEFEND_BOARD) {
                board_name = "Defend Board";
            }

            createUI();
        }

    }
}

