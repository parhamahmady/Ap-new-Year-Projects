import java.util.*;
import java.awt.Point;

/**
 * HumanPlayer
 */
public class HumanPlayer extends Player {

    public HumanPlayer(String name, int plNumber) {
        super(name, plNumber);
    }

    public Point chooseRing(Field board,Point lastPoint){
        Scanner fScanner = new Scanner(System.in);
        while (true) {

            int x, y;
            System.out.println(getName() + "\n" + "Which Ring do you want to choose ?" + " (X,Y)");
            x = fScanner.nextInt();
            y = fScanner.nextInt();
            Point point = new Point(x, y);
            boolean couldAdd = addRing(board, point);
            if (couldAdd) {
                return point;
            } else {
                System.out.println("You Can't !!!");

            }
        }
        
    }

    /**
     * @param board       is the game board
     * @param blockNumber is just for inheritance
     */
    public void rotate(Field board, int blockNumber) {
        Scanner fScanner = new Scanner(System.in);
        int i = 0;// the number of the block to rotate
        while (true) { // get the order
            System.out.println(getName() + "\n" + "Which Block do you want to rotate? (0 for no rotation)");
            i = fScanner.nextInt();
            if ((i >= 0 && i <= 4)) {
                if (i == 0) { // check the empty block
                    if (board.emptyBlockCheck() == false) {

                        return;
                    }
                    System.out.println("there's no empty Block");
                    continue;

                } else {

                    break;
                }
            } else {
                System.out.println("You can't do that");
            }
        }
        super.rotate(board, i);

    }
}