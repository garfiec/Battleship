package Java.com.garfiec.battleship.game.ui;

import javax.swing.*;
import Java.com.garfiec.battleship.game.util.Game_Strings;

import java.awt.*;

public class Battleship_Display extends JFrame {
    public Battleship_Display()  {
        super(Game_Strings.GUI_TITLE);
        getContentPane().setLayout(new BorderLayout());

        createMenu();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(UI_Constants.WIDTH, UI_Constants.HEIGHT);
        setVisible(true);
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
            // TODO: Set as Server

        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Set as Client");
        menuItem.addActionListener(e -> {
            // TODO: Set as Client

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
}
