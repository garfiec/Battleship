package Java.com.garfiec.battleship.game;

import Java.com.garfiec.battleship.game.board.Attack_Board;
import Java.com.garfiec.battleship.game.board.Defend_Board;
import Java.com.garfiec.battleship.game.board.Region;
import Java.com.garfiec.battleship.game.player.Local_Player;
import Java.com.garfiec.battleship.game.player.Player;
import Java.com.garfiec.battleship.game.player.Remote_Player_Server;
import Java.com.garfiec.battleship.game.util.Game_Consts;
import Java.com.garfiec.battleship.game.util.Player_Type;

import java.awt.*;

// Assumes to be the server (the local client)
public class Game_Manager extends Client {
    Player[] players = new Player[Game_Consts.NUM_PLAYERS];

    private Defend_Board[] playerBoards = new Defend_Board[Game_Consts.NUM_PLAYERS];
    private Attack_Board[] enemyBoards = new Attack_Board[Game_Consts.NUM_PLAYERS];

    boolean gameInProgress = false;
    Player_Type currentTurn;

    // Todo: boolean setup board mode

    public Game_Manager() {
        super();
        client_type = Client_Type.GAME_MANAGER;

        initializeGame();
        startGame();
    }

    public void initializeGame() {
        players[Player_Type.LOCAL.index]    = new Local_Player(this);
        players[Player_Type.REMOTE.index]   = new Remote_Player_Server(this);

        playerBoards[Player_Type.LOCAL.index] = new Defend_Board();
        playerBoards[Player_Type.REMOTE.index] = new Defend_Board();

        enemyBoards[Player_Type.LOCAL.index] = new Attack_Board();
        enemyBoards[Player_Type.REMOTE.index] = new Attack_Board();

        gameInProgress = false;
        // Send set up board signal
    }

    public void startGame() {
        // Set random start player

        // Flag as started
        gameInProgress = true;
    }

    @Override
    public Player getLocalPlayer() {
        return players[Player_Type.LOCAL.index];
    }

    // Processes move
    public boolean makeMove(Player_Type type, byte x, byte y) {
        // Check if allowed (if not, return false)
        if (type != currentTurn) { return false; }

        // Make move

        // Check if won

        // else Alternate player
        if (currentTurn == Player_Type.LOCAL) {
            currentTurn = Player_Type.REMOTE;
        }
        else {
            currentTurn = Player_Type.LOCAL;
        }

        // Tell other player to make move
        players[currentTurn.index].playersTurn();

        // Move successful
        return true;
    }

    public Player_Type getCurrentTurn() {
        return currentTurn;
    }

}
