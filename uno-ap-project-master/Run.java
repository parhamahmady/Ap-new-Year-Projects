import java.util.*;

/**
 * To run this project Run
 */
public class Run {
    private static Player[] players;
    private static Board gameBoard;
    private static Player currentPlayer;
    private static char escCode = 0x1B; // esc Ascii code

    public static void main(String[] args) {
        makePlayer(); // make the players
        gameBoard = new Board(); // make the board
        cardPublisher();// publish the cards to players
        currentPlayer = players[0];// first current player
        gameBoard.print(players); // print the first possion of gameBoard
        players = firstGroundCheck();
        delay();
        while (true) {
            currentPlayer = players[0];
            gameBoard.print(players);
            currentPlayer.printMyCards();
            Card lastground = gameBoard.getGroundCard();
            currentPlayer.playCard(gameBoard, players);
            delay();
            if (!(playCheck(gameBoard.getGroundCard(), lastground))) {// check if the card was same just go to next
                                                                      // player
                if (gameBoard.endCheck(players)) {
                    break;
                }
                // System.out.println("salam");
                players = gameBoard.thinker(players, currentPlayer);
                setLoopDraw(gameBoard);
            } else {
                gameBoard.resetDraw();
                players = gameBoard.getGroundCard().chooseNextPlayer(players, currentPlayer);
            }
        }
        sortScore();
    }

    /**
     * just clear the screen
     */
    private static void clearScreen() {
        System.out.print(String.format("%c[2J", escCode));
    }

    private static void cardPublisher() {
        for (int i = 0; i < players.length; i++) {
            gameBoard.giveCard(players[i], 7);
        }
    }

    /**
     * Make the players of game
     */
    private static void makePlayer() {
        Scanner fScanner = new Scanner(System.in);
        int n;
        while (true) {// get the number of players
            clearScreen();
            System.out.println("How many Players would play ?? (3-5)"); // get the number of players;
            n = fScanner.nextInt();
            if (n >= 3 && n <= 5) {
                break;
            }

            System.out.println("Please enter a vaild Number !!");
            delay();
        }
        clearScreen();
        players = new Player[n];// new the players
        int humannumber = 0;
        while (true) {// get the number of human players
            clearScreen();
            System.out.println("How many Human player would Play ?");
            humannumber = fScanner.nextInt();
            if (humannumber >= 1 && humannumber <= n) {
                break;
            }
            System.out.println("That's invaild");
        }
        for (int i = 0; i < humannumber; i++) {
            System.out.println("Please enter your name :");
            String name = fScanner.next();
            while (true) {//make human players
                int ranNumber = (int) Math.abs((Math.random() * 100) % n);// to make players randomly
                if (!(players[ranNumber] instanceof HumanPlayer)) {
                    players[ranNumber] = new HumanPlayer(name, ranNumber);
                    break;
                }
            }

        }

        int computerNumber = 0;
        for (int j = 0; j < n; j++) { // make computer players
            
            if (players[j] instanceof HumanPlayer) {
                continue;
            }
            computerNumber++;
            String name = "Computer " + computerNumber;
            players[j] = new ComputerPlayer(name, j);
        }

    }

    /**
     * check if the first ground card wasnt numeric
     */
    private static Player[] firstGroundCheck() {
        if (!(gameBoard.getGroundCard() instanceof NumericCard)) {
            delay();
            return gameBoard.thinker(players, currentPlayer);
        }
        return players;
    }

    private static void sortScore() {
        HashMap<Integer, String> table = new HashMap<Integer, String>();
        int[] sortTable = new int[players.length];
        for (int i = 0; i < players.length; i++) {// get players Scores
            table.put(players[i].getMyScore(), players[i].getName());
            sortTable[i] = players[i].getMyScore();
        }
        Arrays.sort(sortTable);// sort scores
        clearScreen();
        System.out.println("Player :" + table.get(sortTable[0]) + " Wins !!!");
        for (int i = 1; i < sortTable.length; i++) {
            System.out.println("Player : " + table.get(sortTable[i]) + " Score : " + sortTable[i]);
        }
    }

    private static boolean playCheck(Card groundCard, Card lastground) {
        if (groundCard.equals(lastground)) {
            return true;
        }
        return false;
    }

    /**
     * just make delay
     */
    private static void delay() {
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {

        }
    }

    private static void setLoopDraw(Board gameBoard) {
       Card tempCard=gameBoard.getGroundCard();
       
        if (tempCard instanceof DrawCard) { // if the Card was Draw
            DrawCard tempDrawCard = (DrawCard) tempCard;
            gameBoard.setLoopDraw(tempDrawCard.getDrawNumber());
            return;
        }
        if (tempCard instanceof WildDrawCard) { // if the Card was WildDraw
            WildDrawCard tempDrawCard = (WildDrawCard) tempCard;
            gameBoard.setLoopDraw(tempDrawCard.getDrawNumber());
            return;
        }
        gameBoard.resetDraw();
    }
}