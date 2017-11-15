package com.garfiec.battleship.game.ui;

public class UI_Strings {
    public static final String GUI_TITLE = "Battleship";

    // Help Text
    public static final String HOW_TO_PLAY_TITLE = "How to play " + GUI_TITLE;
    public static final String HOW_TO_PLAY  =
            "<html>" +
                "<h2>Game Overview</h2>" +
                    "<p>The goal of this game is to destroy all enemy ships before your enemy destroys all of your ships. It " +
                    "is advantageous for you to strategically organize your ships in a way that will be difficult for your enemy" +
                    " to guess its location. You may also want to make guesses that are most likely to hit your enemy's ships.</p>" +
                "<h2>Game Play</h2>" +
                "<p>The game follows a certain order:</p>" +
                "<ol>" +
                    "<li>Both players arrange their ships' locations</li>" +
                    "<li>Each player takes turn making attacks until one player has all their ships destroyed</li>" +
                "</ol>" +
                "<br><p>Gl&hf ;)</p>" +
            "</html>";
    public static final String HOW_TO_USE_TITLE = "How to use " + GUI_TITLE;
    public static final String HOW_TO_USE   =
            "<html>" +
                "<h2>Choosing a client</h2>" +
                    "<h3>Hosting a game</h3>" +
                    "<ol>" +
                        "<li>If you haven't already selected the server client, go to &quot;File &gt; Set as Server&quot;</li>" +
                        "<li>Go to &quot;File &gt; Connection Settings&quot; and share server details with your opponent</li>" +
                        "<li>Have your opponent connect to your server on their client</li>" +
                    "</ol>" +
                    "</h3>Connecting to a game</h3>" +
                    "<ol>" +
                        "<li>If you haven't already selected the remote client, go to &quot;File &gt; Set as Client&quot;</li>" +
                        "<li>Go too &quot;File &gt; Connection Settings&quot; and enter in the server details</li>" +
                        "<li>Click connect to server</li>" +
                    "</ol>" +
                "<h2>Adding ships</h2><p>Choose a ship type, and click on a region in defend board to add ship.</p>" +
                "<h2>Making moves</h2><p>Select a region in the attack board to attack your opponent's area.</p>" +
                "<h2>Starting a new game</h2>" +
                    "<p>In the event that you wish to request a new game, go to &quot;File &gt; New Game&quot;</p>" +
                    "<p><i>Note: When you first connect to a server or client, a game has already started.</i></p>" +
            "</html>";

    public static final String ABOUT        = "<html>Garfie Chiu: gchiu2 <br>" +
                                                "Cesar Lazcano: clazca2\n</html>";
    public static final String IMAGE_CRED   = "Image by Manu Cornet";
    public static final String IMAGE_SRC    = "http://bonkersworld.net/building-software";
}
