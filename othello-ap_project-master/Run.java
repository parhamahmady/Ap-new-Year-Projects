import java.util.*;

/**
 * Run
 */
public class Run {
    private static Player[] players = new Player[2];
    private static Block[][] board;
    private static int[] scores;

    public static void main(String[] args) {
        int ending = 0; // if became 2 game will end
        makePlayer();
        makeBoard();
        print();
        for (int i = 2; ending != 2; i++) {
            print();
            if (i % 2 == 0) {
                delay();
                Disk lastDisk = players[1].move(board);
                if (lastDisk == null) {
                    ending++;
                    continue;
                } else {
                    ending = 0;
                }
                print();
                Reverse reverser = new Reverse(board, players, lastDisk);
                reverser.horizentalReverse();
                reverser.verticalReverse();
                reverser.diagonalReverse();
                print();
            } else {
                delay();
                Disk lastDisk = players[0].move(board);
                if (lastDisk == null) {
                    ending++;
                    continue;
                } else {
                    ending = 0;
                }
                print();
                Reverse reverser = new Reverse(board, players, lastDisk);
                reverser.horizentalReverse();
                reverser.verticalReverse();
                reverser.diagonalReverse();
                print();
            }
        }
        winnerCheck();

    }

    /**
     * scan each player's name and new each player
     */
    public static void makePlayer() {
        Scanner fScanner = new Scanner(System.in);
        clearScreen();
        System.out.println("1)SinglePlayer\n2)MultiPlayer");
        if (fScanner.nextInt() == 1) {
            clearScreen();
            System.out.println("Please enter your name for HumanPlayer :");
            String name = fScanner.next();
            players[0] = new HumanPlayer(name, 1, new ArrayList<Disk>());
            players[1] = new ComputerPlayer("Pc", 2, new ArrayList<Disk>());

        } else {

            for (int i = 1; i <= 2; i++) {
                clearScreen();
                System.out.println("Please enter your name for player number : " + i);
                String name = fScanner.next();
                players[i - 1] = new HumanPlayer(name, i, new ArrayList<Disk>());
            }
        }
    }

    /**
     * make the board blocks and set the first disks for the first round
     */
    private static void makeBoard() {
        board = new Block[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                board[i][j] = new Block(i, j); // give x , y of each blocks of the board;
            }
        }
        /// set the first disks in the middle of the board
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                Disk tempDisk;
                if (i == 0) {
                    tempDisk = new Disk(board[3 + i][3 + j].getPoint(), j + 1);
                } else {
                    tempDisk = new Disk(board[3 + i][3 + j].getPoint(), 2 - j);
                }
                board[3 + i][3 + j].setFull(true);
                board[3 + i][3 + j].setMyDisk(tempDisk);
                if (i == 1) {
                    players[1 - j].addDisk(tempDisk);
                    continue;
                }
                players[j].addDisk(tempDisk);
            }
        }

    }

    /**
     * Print the game board in terminal
     */
    private static void print() {
        char escCode = 0x1B; // esc Ascii code ,, use it to move the mouse cursor
        clearScreen();
        for (int i = 1; i <= 8; i++) { // to print the I C format;
            int c = 64 + i;
            char temp = (char) c;
            System.out.print(String.format("%c[%d;%df%c", escCode, 0, i * 7, temp));
            System.out.print(String.format("%c[%d;%df%d", escCode, i * 4 + 1, 0, i));

        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].print();
            }
        }
        System.out.print(String.format("%c[0m", escCode));
        System.out.println(""); // go to next line
    }

    /**
     * just clear the terminal screen !
     */
    private static void clearScreen() {
        char escCode = 0x1B; // esc Ascii code
        System.out.print(String.format("%c[2J", escCode));
    }

    private static void winnerCheck() {
        scores = new int[2];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getFull()) {
                    scores[board[i][j].getMyDisk().getOwner() - 1]++;
                }
            }
        }
        clearScreen();
        if (scores[0] > scores[1]) {
            System.out.println("Player :" + players[0].getName() + " WINS !!!!");
            return;
        } else {
            if (scores[0] < scores[1]) {
                System.out.println("Player :" + players[1].getName() + " WINS !!!!");
                return;
            }
        }
        System.out.println("<<< :( Equal  ): >>>");
    }

    /**
     * just wait 0.5 sec
     */
    private static void delay() {
        try {
            Thread.sleep(500);

        } catch (InterruptedException e) {

        }
    }
}