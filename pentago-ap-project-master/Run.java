import java.util.*;
import java.awt.Point;
/**
 * Run
 */
public class Run {
    private static Field board;
    private static Player[] players;

    public static void main(String[] args) {
        playerMaker(); // make the players
        board = new Field(); // make the game board;
        board.print();
        boolean end = false;
        Point lastPoint = new Point(10, 10);
        for (int i = 1; i > -1 && end == false; i++) {
            int counter = 0;
            if (i % 2 == 0 && i != 1) {// to change the player
                counter = 1;
            }
            lastPoint = players[counter].chooseRing(board,lastPoint);
            board.print();
            end = board.endCheck();
            if (end) {
                return;
            }
            players[counter].rotate(board, 0);// block number is nothing
            end = board.endCheck();
            board.print();

        }
    }

    /**
     * make the players multiplayer or single
     */
    private static void playerMaker() {
        Scanner fScanner = new Scanner(System.in);
        players = new Player[2];
        clearScreen();
        System.out.println("1)SinglePlayer\n2)Multiplayer");
        if (fScanner.nextInt() == 2) {
            for (int i = 1; i <= 2; i++) {
                System.out.println("Enter the name of player number : " + i);
                players[i - 1] = new HumanPlayer(fScanner.next(), i);

            }

        } else {
            System.out.println("Enter the name of HumanPlayer ");
            players[0] = new HumanPlayer(fScanner.next(), 1);
            players[1] = new ComputerPlayer();
        }
    }

    /**
     * just clear the terminal screen !
     */
    private static void clearScreen() {
        char escCode = 0x1B; // esc Ascii code
        System.out.print(String.format("%c[2J", escCode));
    }
}