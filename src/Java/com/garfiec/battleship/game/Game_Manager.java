package Java.com.garfiec.battleship.game;

import Java.com.garfiec.battleship.game.board.Attack_Board;
import Java.com.garfiec.battleship.game.board.Defend_Board;
import Java.com.garfiec.battleship.game.board.Region;
import Java.com.garfiec.battleship.game.board.ships.Ship_Orientation;
import Java.com.garfiec.battleship.game.board.ships.Ships;
import Java.com.garfiec.battleship.game.player.Local_Player;
import Java.com.garfiec.battleship.game.player.Player;
import Java.com.garfiec.battleship.game.player.Remote_Player_Server;
import Java.com.garfiec.battleship.game.util.Game_Consts;
import Java.com.garfiec.battleship.game.util.Player_Type;

import java.awt.*;
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
        // Send set up board signal
    }

    public void startGame() {
        // Flag as started
        gameInProgress = true;

        // Tell all players to add their ships
        for (Player player: players) {
            player.doAddships();
        }

        // Choose random player to start and start
        int randomPlayer = ThreadLocalRandom.current().nextInt(0, Game_Consts.NUM_PLAYERS);
        players[randomPlayer].playersTurn();
    }

    @Override
    public Player getLocalPlayer() {
        return players[Player_Type.LOCAL.index];
    }

    // Adds players' ships to the board in setup mode. Returns whether successful
    public boolean addShip(Player player, Ships ship, Ship_Orientation direction, Point cord) {
        if (!setupMode) { return false; }

        return defend_boards[player.getPlayerType().index].addShip(ship, direction, cord);
    }

    // Processes move. Return whether move was successful.
    public boolean makeMove(Player_Type player, byte x, byte y) {
        Player_Type otherPlayer;
        if (player == Player_Type.LOCAL)
            otherPlayer = Player_Type.REMOTE;
        else
            otherPlayer = Player_Type.LOCAL;

        // Check if game is currently in progress
        if (!gameInProgress) { return false; }

        // Check if allowed (if not, return false)
        if (player != currentTurn) { return false; }

        // Make move
        boolean attackSuccess = defend_boards[otherPlayer.index].attack(new Point(x, y));
        attack_boards[player.index].attack(new Point(x, y), attackSuccess);

        // Check if won
        if (!defend_boards[otherPlayer.index].isAlive()) {
            declareWinner(player);
            return true;
        }

        // Not won yet, alternate player
        currentTurn = otherPlayer;

        // Tell other player to make move
        players[currentTurn.index].playersTurn();

        // Move successful
        return true;
    }

    private void declareWinner(Player_Type player) {
        gameInProgress = false;
        winner = player;

        for (Player p:players) {
            p.announceWin(winner);
        }
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
