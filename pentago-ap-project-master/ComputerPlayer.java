import java.awt.Point;

/**
 * ComputerPlayer
 */
public class ComputerPlayer extends Player {
    /**
     * Counstructor
     */
    public ComputerPlayer() {
        super("Computer", 2);
    }

    /**
     * choose a new ring acording to last ring
     */
    public Point chooseRing(Field board,Point lastPoint) {
        int x = (int) lastPoint.getX();
        int y = (int) lastPoint.getY();
        System.out.println(x+" , "+y);
        delay();
        for (int count = 0; count <1 ; count++) { // to add a choose a ring near lastRing
            for (int i = -1; i < 2; i++) {
                if (x + i > 5 || x + i < 0) { // to make sure it would be fit
                    continue;
                }
                for (int j = -1; j < 2; j++) {
                    if (y + j > 5 || y + j < 0) { // to make sure it would be fut
                        continue;
                    }
                    Point point = new Point(x + i, y + j);
                    boolean couldAdd = addRing(board, point);
                    if (couldAdd) {
                        return null;

                    }
                    
                }
                
            }
        
        }

        /// if near lastRing was full
        while (true) {
            x = (int)Math.abs((Math.random()*100)%6);
            y = (int)Math.abs((Math.random()*100)%6);
            Point point = new Point(x, y);
            boolean couldAdd = addRing(board, point);
            if (couldAdd) {
                return null;
            }
        }
    } 
    
    /**
     * @param board       is the game board
     * @param blockNumber is just for inheritance
     */
    public void rotate(Field board, int blockNumber) {
        int i = 0;// the number of the block to rotate
        delay();
        while (true) { // get the order
            i = (int)Math.abs((Math.random()*100)%5);
            if ((i >= 0 && i <= 4)) {
                if (i == 0) { // check the empty block
                    if (board.emptyBlockCheck() == false) {

                        return;
                    }
                    continue;

                } else {

                    break;
                }
            } else {
            }
        }
        super.rotate(board, i);

    }
    /**
     * just make delay
     */
    private  void delay() {
        try {
            Thread.sleep(500);

        } catch (InterruptedException e) {

        }
    }
}