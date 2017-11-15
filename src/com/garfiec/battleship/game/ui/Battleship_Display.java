package com.garfiec.battleship.game.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.garfiec.battleship.game.Client;
import com.garfiec.battleship.game.Game_Manager;
import com.garfiec.battleship.game.Remote_Client;
import com.garfiec.battleship.game.board.Map;
import com.garfiec.battleship.game.board.ships.Ship_Orientation;
import com.garfiec.battleship.game.board.ships.Ships;
import com.garfiec.battleship.game.player.Player;
import com.garfiec.battleship.game.ui.etc.Connection_Settings_Display;
import com.garfiec.battleship.game.ui.etc.UI_About;
import com.garfiec.battleship.game.util.Game_Consts;
import com.garfiec.battleship.game.util.Game_Settings;
import com.garfiec.battleship.game.util.Game_Strings;

import java.awt.*;
import java.util.ArrayList;

public class Battleship_Display extends JFrame {
    // Settings object
    private Game_Settings settings;

    // Reference to client object controlling UI
    private Client game_client;

    // Reference to player object associated with GUI
    private Player player;

    // Text communication to user
    private JLabel statusLabel;

    // Game vars
    private Ship_Orientation    toAdd_ShipOrientation;
    private Ships               toAdd_Ship;

    public Battleship_Display()  {
        super(Game_Strings.GUI_TITLE);
        getContentPane().setLayout(new BorderLayout());

        // Initialization
        settings = new Game_Settings();

        createMenu();
        createUI();
        createStatusBar();

        this.setBackground(UI_Constants.BG_COLOR);

        showClientPicker();

        this.setLocation(100, 100); // Temp fix to spawn issue
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(UI_Constants.WIDTH, UI_Constants.HEIGHT);
        setVisible(true);

    }

    private void createUI() {
        // Set panes
        JPanel boardPanel = new JPanel();
        BoxLayout layout = new BoxLayout(boardPanel, BoxLayout.LINE_AXIS);
        boardPanel.setLayout(layout);

        Board_UI defendPanel = new Board_UI(Map.BoardType.DEFEND_BOARD);
        Board_UI attackPanel = new Board_UI(Map.BoardType.ATTACK_BOARD);
        Controls_UI controls = new Controls_UI();

        boardPanel.add(defendPanel);
        boardPanel.add(attackPanel);
        boardPanel.add(controls);

        this.add(boardPanel, BorderLayout.CENTER);
    }

    private void createStatusBar() {
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(new BevelBorder(BevelBorder.RAISED));
        statusBar.setBackground(UI_Constants.STATUS_BAR_COLOR);

        JPanel padding = new JPanel(new BorderLayout());
        padding.setBorder(new EmptyBorder(8, 5, 8, 5));
        padding.setBackground(UI_Constants.STATUS_BAR_COLOR);
        statusBar.add(padding, BorderLayout.CENTER);

        statusLabel = new JLabel("test");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        statusLabel.setVerticalAlignment(JLabel.CENTER);
        statusLabel.setForeground(UI_Constants.STATUS_BAR_TEXT_COLOR);
        padding.add(statusLabel, BorderLayout.CENTER);

        this.add(statusBar, BorderLayout.SOUTH);
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
        this.setTitle(Game_Strings.GUI_TITLE + " | Player: " + player.getPlayerType().toString());

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

    public void setStatus(String status) {
        statusLabel.setText(status);
    }

    private void resetGUI() {
        // Todo: reset the gui to default starting state
        toAdd_ShipOrientation = Ship_Orientation.HORIZONTAL;
        toAdd_Ship = Ships.AIRCRAFT_CARRIER;
    }

    private class Controls_UI extends JPanel {
        private JPanel createShipsControls() {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setBorder(new EmptyBorder(5, 5, 5, 5));
            panel.setBackground(UI_Constants.CONTROLS_BG_COLOR);

            ArrayList<String> ship_names = new ArrayList<>();

            for (Ships s:Ships.values()) {
                if (s.getName() != "" && s.getName() != "Generic") {
                    ship_names.add(s.getName());
                }
            }

            JList ships_list = new JList(ship_names.toArray());
            ships_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);;
            ships_list.addListSelectionListener(e -> {
                if (e.getValueIsAdjusting()) {
                    if (ships_list.getSelectedIndex() != -1) {
                        toAdd_Ship = Ships.getShip(ships_list.getSelectedValue().toString());
                    }
                }
            });

            // Set default
            ships_list.setSelectedIndex(0);

            panel.add(ships_list, BorderLayout.CENTER);

            return panel;
        }

        private JPanel createOrientationsControls() {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setBorder(new EmptyBorder(5, 5, 5, 5));
            panel.setBackground(UI_Constants.CONTROLS_BG_COLOR);

            ArrayList<String> directions = new ArrayList<>();

            for (Ship_Orientation d:Ship_Orientation.values()) {
                directions.add(d.getDirectionName());
            }

            JList orientation_list = new JList(directions.toArray());
            orientation_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            orientation_list.addListSelectionListener(e -> {
                if (e.getValueIsAdjusting()) {
                    if (orientation_list.getSelectedIndex() != -1) {
                        toAdd_ShipOrientation = Ship_Orientation.getOrientation(orientation_list.getSelectedValue().toString());
                    }
                }
            });

            // Set default
            orientation_list.setSelectedIndex(0);

            panel.add(orientation_list, BorderLayout.CENTER);

            return panel;
        }

        public Controls_UI() {
            super();
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            this.setBorder(new EmptyBorder(5, 5, 5, 5));
            this.setBackground(UI_Constants.CONTROLS_BG_COLOR);

            add(createShipsControls());
            add(createOrientationsControls());
        }
    }

    private class Board_UI extends JPanel {
        private JButton[][] regions = new JButton[Game_Consts.ROWS][Game_Consts.COLUMNS]; // [y][x]

        private Map.BoardType board_type;
        private String board_name = "None";

        private void drawShips(Ships ship, Ship_Orientation orientation, Point location) {
            // Draw ships using what we know locally rather than ask server
            if (orientation == Ship_Orientation.HORIZONTAL) {
                int tail = location.x + ship.size;
                for (int x = location.x; x < tail; x++) {
                    regions[location.y][x].setBackground(UI_Constants.SHIP_COLOR);
                }
            }
            else if (orientation == Ship_Orientation.VERTICAL) {
                int tail = location.y + ship.size;
                for (int y = location.y; y < tail; y++) {
                    regions[y][location.x].setBackground(UI_Constants.SHIP_COLOR);
                }
            }
        }

        private JPanel createBoard() {
            JPanel board_panel = new JPanel();
            board_panel.setLayout(new GridLayout(Game_Consts.ROWS, Game_Consts.COLUMNS, UI_Constants.TILE_GAP_H, UI_Constants.TILE_GAP_V));
            board_panel.setBorder(new EmptyBorder(5, 5, 0, 5));
            board_panel.setBackground(UI_Constants.BG_COLOR);

            for(int row = 0; row < Game_Consts.ROWS; row++) {
                for(int col = 0; col < Game_Consts.COLUMNS; col++) {
                    final Point location = new Point(col, row);

                    JButton region = new JButton();
                    region.setBackground(UI_Constants.BLANK_COLOR);

                    region.addActionListener(e -> {
                        // Respond to click
                        if (board_type == Map.BoardType.ATTACK_BOARD) {
                            // Make Move
                            player.makeMove(location);
                        } else if (board_type == Map.BoardType.DEFEND_BOARD) {
                            // Add ship
                            if (player.addShip(toAdd_Ship, toAdd_ShipOrientation, location)) {
                                // Add ship successful. Add to board
                                drawShips(toAdd_Ship, toAdd_ShipOrientation, location);
                            }
                        }

                        // Update logic
                        // Todo: Update graphics for cell
                    });
                    regions[location.y][location.x] = region;
                    board_panel.add(region);
                }
            } // end loop
            return board_panel;
        }

        private JPanel createBoardLabel() {
            JPanel label_panel = new JPanel();
            label_panel.setLayout(new BorderLayout());
            label_panel.setBorder(new EmptyBorder(7, 5, 10, 5));
            label_panel.setBackground(UI_Constants.BG_COLOR);

            JLabel label = new JLabel(board_name);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            label_panel.add(label, BorderLayout.CENTER);
            label.setForeground(UI_Constants.BOARD_LABEL_COLOR);

            return label_panel;
        }

        private void createUI() {
            this.setLayout(new BorderLayout());

            JPanel board = createBoard();
            this.add(board, BorderLayout.CENTER);

            JPanel board_label = createBoardLabel();
            this.add(board_label, BorderLayout.SOUTH);
        }

        public Board_UI(Map.BoardType board_type) {
            super();
            this.board_type = board_type;
            this.setBackground(UI_Constants.BG_COLOR);

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

