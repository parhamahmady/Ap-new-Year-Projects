import java.util.*;
import java.awt.Point;

/**
 * HumanPlayer
 */
public class ComputerPlayer extends Player {

    public ComputerPlayer(String name, int pl_number, ArrayList<Disk> myDisks) {
        super(name, pl_number, myDisks);
    }

    /**
     * 
     * @param board
     * @return will return the new disk ;; that is for reversing ;
     */
    public Disk move(Block[][] board) {
        HashSet<Point> possiblPoints = movePossible(board);
        Iterator<Point> it = possiblPoints.iterator();
        Scanner fScanner = new Scanner(System.in);
        if (possiblPoints.size() == 0) {
            clearScreen();
            System.out.println("Pass");
            return null;
        }
        int x = 0;
        int y = 0;
        int random = (int) Math.abs((Math.random() * 100) % possiblPoints.size()); // make a random number less than
                                                                                   // possible

        Iterator<Point> ranPoint = possiblPoints.iterator();
        // while (ranPoint.hasNext()) {
        //     System.out.println(ranPoint.next());
        // }
        for (int i = 0; i <= random && ranPoint.hasNext(); i++) {
            Point temp=ranPoint.next();
            x = (int) temp.getX();
            y = (int) temp.getY();
        }
        // System.out.println("x: " + x + " y: " + y + " rand: " + random);
        Disk tempDisk = new Disk(board[x][y].getPoint(), getPl_number()); // add the disk to the board and player
        board[x][y].setFull(true);
        board[x][y].setMyDisk(tempDisk);
        addDisk(tempDisk);
        // Disk tempDisk = null;
        return tempDisk;
    }
}