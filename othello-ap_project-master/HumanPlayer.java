import java.util.*;
import java.awt.Point;

/**
 * HumanPlayer
 */
public class HumanPlayer extends Player{

    public HumanPlayer(String name,int pl_number,ArrayList<Disk> myDisks)
    {
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
        int x;
        String temp;
        int y;
        while (true) {
            System.out.println("Player : " + getName());
            System.out.println("enter where you want to add Disk?"); // scan the x and y;
            x = fScanner.nextInt();
            temp = fScanner.next();
            temp = temp.toUpperCase();
            y = (int) temp.charAt(0);
            if (y - 65 < 8 && y - 65 >= 0 && x >= 1 && x <= 8
                    && possiblPoints.contains(board[x - 1][y - 65].getPoint())) { // check the location
                break;
            }
            System.out.println("You can't !!!");
        }
        Disk tempDisk = new Disk(board[x - 1][y - 65].getPoint(),getPl_number()); // add the disk to the board and player
        board[x - 1][y - 65].setFull(true);
        board[x - 1][y - 65].setMyDisk(tempDisk);
        addDisk(tempDisk);
        return tempDisk; 
    }
}