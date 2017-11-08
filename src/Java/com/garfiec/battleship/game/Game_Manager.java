package Java.com.garfiec.battleship.game;

import Java.com.garfiec.battleship.game.board.Attack_Board;
import Java.com.garfiec.battleship.game.board.Defend_Board;
import Java.com.garfiec.battleship.game.player.Local_Player;
import Java.com.garfiec.battleship.game.player.Player;
import Java.com.garfiec.battleship.game.player.Remote_Player;
import Java.com.garfiec.battleship.game.util.Game_Consts;
import Java.com.garfiec.battleship.game.util.Player_Type;

// Assumes to be the server (the local client)
public class Game_Manager extends Client {
    Player[] players = new Player[Game_Consts.NUM_PLAYERS];

    Defend_Board[] playerBoards = new Defend_Board[Game_Consts.NUM_PLAYERS];
    Attack_Board[] enemyBoards = new Attack_Board[Game_Consts.NUM_PLAYERS];

    public Game_Manager() {
        super();
        client_type = Client_Type.GAME_MANAGER;
    }

    public void initializeGame() {
        players[Player_Type.LOCAL.index]    = new Local_Player(this);
        players[Player_Type.REMOTE.index]   = new Remote_Player(this);

        playerBoards[Player_Type.LOCAL.index] = new Defend_Board();
        playerBoards[Player_Type.REMOTE.index] = new Defend_Board();

        enemyBoards[Player_Type.LOCAL.index] = new Attack_Board();
        enemyBoards[Player_Type.REMOTE.index] = new Attack_Board();
    }

    public void newGame() {

    }

    public boolean makeMove(Player_Type type, byte x, byte y) {
        return true;
    }
}
