package com.garfiec.battleship.game;

import com.garfiec.battleship.game.board.Attack_Board;
import com.garfiec.battleship.game.board.Defend_Board;
import com.garfiec.battleship.game.board.ships.Ship_Orientation;
import com.garfiec.battleship.game.board.ships.Ships;
import com.garfiec.battleship.game.player.Local_Player;
import com.garfiec.battleship.game.player.Player;
import com.garfiec.battleship.game.player.Remote_Player_Server;
import com.garfiec.battleship.game.util.Connection_Settings;
import com.garfiec.battleship.game.util.Game_Consts;
import com.garfiec.battleship.game.util.Game_Strings;
import com.garfiec.battleship.game.util.Player_Type;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

// Assumes to be the server (the local client)
public class Game_Manager extends Client {

    private Player[] players = new Player[Game_Consts.NUM_PLAYERS];

    private Defend_Board[] defend_boards = new Defend_Board[Game_Consts.NUM_PLAYERS];
    private Attack_Board[] attack_boards = new Attack_Board[Game_Consts.NUM_PLAYERS];

    private boolean gameInProgress = false;
    private Player_Type currentTurn;
    private Player_Type winner;

    private boolean setupMode; // Allow users to add ships

    public Game_Manager() {
        super();
        client_type = Client_Type.GAME_MANAGER;

        initializeGame();
        startGame();
    }

    public void initializeGame() {
        players[Player_Type.LOCAL.index]    = new Local_Player(this);
        players[Player_Type.REMOTE.index]   = new Remote_Player_Server(this);

        defend_boards[Player_Type.LOCAL.index] = new Defend_Board();
        defend_boards[Player_Type.REMOTE.index] = new Defend_Board();

        attack_boards[Player_Type.LOCAL.index] = new Attack_Board();
        attack_boards[Player_Type.REMOTE.index] = new Attack_Board();

        gameInProgress = false;

        setupMode = true;

        // Let remote player server get the connection settings
        players[Player_Type.REMOTE.index].setConnectionSettings(connection_settings);
    }

    public void startGame() {
        // Flag as started
        gameInProgress = true;

        // Tell all players to add their ships
        announce(Game_Strings.STATUS_SETUP_BOARD);
    }

    @Override
    public Player getLocalPlayer() {
        return players[Player_Type.LOCAL.index];
    }

    // Adds players' ships to the board in setup mode. Returns whether successful
    public boolean addShip(Player player, Ships ship, Ship_Orientation direction, Point cord) {
        if (!setupMode) { return false; }

        // Try to add ship
        boolean isAdded = defend_boards[player.getPlayerType().index].addShip(ship, direction, cord);

        // Tell player ships remaining.
        ArrayList<String> ship_list = defend_boards[player.getPlayerType().index].listShipsToAddLeft();
        if (ship_list.size() == 0) {
            player.setStatus(Game_Strings.STATUS_SETUP_WAITING_OPPONENT);
        } else {
            player.setStatus(Game_Strings.STATUS_SETUP_WAITING_LOCAL + " | Ships left: " + String.join(", ", ship_list));
        }

        // Check if all ships are added. If so, proceed with game
        boolean everyoneCompleted = true;
        for (Defend_Board b:defend_boards) {
            if (b.listShipsToAddLeft().size() > 0) {
                // Someone has ships left to add
                everyoneCompleted = false;
            }
        }
        if (everyoneCompleted) {
            setupMode = false;

            // Choose random player to start and start game play
            int randomPlayer = ThreadLocalRandom.current().nextInt(0, Game_Consts.NUM_PLAYERS);
            notifyPlayerTurn(players[randomPlayer].getPlayerType());
        }

        return isAdded;
    }

    // Processes move. Return whether move was successful.
    public boolean makeMove(Player_Type player, Point location) {
        Player_Type otherPlayer = getOtherPlayer(player);

        // Check if game is currently in progress
        if (!gameInProgress) {
            tell(player, Game_Strings.MOVE_ERROR_GAME_NO_GAME);
            return false;
        }

        // Check if game is currently in setup mode
        if (setupMode) {
            tell(player, Game_Strings.MOVE_ERROR_GAME_NOT_STARTED);
            return false;
        }

        // Check if allowed (if not, return false)
        if (player != currentTurn) {
            tell(player, Game_Strings.MOVE_ERROR_NOT_TURN);
            return false;
        }

        // Make move
        boolean attackSuccess = defend_boards[otherPlayer.index].attack(location);
        attack_boards[player.index].attack(location, attackSuccess);

        // Check if won
        if (!defend_boards[otherPlayer.index].isAlive()) {
            declareWinner(player);
            return true;
        }

        // Not won yet, alternate player
        currentTurn = otherPlayer;

        // Notify new turn
        notifyPlayerTurn(currentTurn);

        // Move successful
        return true;
    }

    private void notifyPlayerTurn(Player_Type player) {
        // Tell other player to make move
        tell(player, Game_Strings.NOTIFY_PLAYER_TURN);

        // Tell other player it's the other player's turn now
        tell(getOtherPlayer(player), Game_Strings.NOTIFY_TURN_OTHER);
    }

    private Player_Type getOtherPlayer(Player_Type player) {
        if (player == Player_Type.LOCAL)
            return Player_Type.REMOTE;
        else
            return Player_Type.LOCAL;
    }

    // Sends private message to player
    private void tell(Player player, String message) {
        player.setStatus(message);
    }

    private void tell(Player_Type player_type, String message) {
        players[player_type.index].setStatus(message);
    }

    // Sends message to all players' status bar
    private void announce(String message) {
        for (Player p:players) {
            p.setStatus(message);
        }
    }

    // Todo: Sends message box to all GUIs
    private void broadcast(String message) {

    }

    private void declareWinner(Player_Type player) {
        gameInProgress = false;
        winner = player;

        tell(player, Game_Strings.STATUS_WIN);
        tell(getOtherPlayer(player), Game_Strings.STATUS_OTHER_WON);
    }

    public Player_Type getCurrentTurn() {
        return currentTurn;
    }

    public boolean getRegionStatus(Player_Type player, byte x, byte y) {
        // First check if the player actually made a hit to said position. (for security reasons)
        if (attack_boards[player.index].madeMoveAt(new Point(x, y))) {
            // Todo: Return whether or not region was attacked (to keep track of successfully hit areas)
            // Probably something like defend_boards[otherPlayer.index].isHit()

        }

        return false;
    }

}
