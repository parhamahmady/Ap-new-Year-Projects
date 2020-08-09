import java.util.*;
import java.awt.Point;

/**
 * player this class
 * 
 */
public class Player {
    private String name;
    private int pl_number;
    ArrayList<Disk> myDisks = new ArrayList<Disk>();

    /**
     * 
     * @param name      is the name of the player
     * @param pl_number the number of the player like player number 1
     * @param myDisks   is an arraylist of player's disks
     */

    public Player(String name, int pl_number, ArrayList<Disk> myDisks) {
        this.name = name;
        this.pl_number = pl_number;
        this.myDisks = myDisks;
    }

    /**
     * @return the myDisks
     */
    public ArrayList<Disk> getMyDisks() {
        return myDisks;
    }

    /**
     * @return the pl_number
     */
    public int getPl_number() {
        return pl_number;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param disk is the disk we want to add to mydisks
     */
    public void addDisk(Disk disk) {
        myDisks.add(disk);
    }
    /**
     *  remove a disk from arraylist
     * @param disk is the disk that we want to remove
     */
    public void removeDisk(Disk disk)
    {
        myDisks.remove(disk);
    }
    /**
     * 
     * @param board
     * @return the hashset of the possible move points 
     */
    public HashSet<Point> movePossible(Block[][] board) {
        HashSet<java.awt.Point> possiblPoints = new HashSet<Point>();
        Iterator<Disk> it = myDisks.iterator();
        while (it.hasNext()) {
            Point tempPoint = it.next().getPoint();
            int x = (int) tempPoint.getX();
            int y = (int) tempPoint.getY();
            HorizentalCheck horizentalChecker = new HorizentalCheck(board, pl_number, possiblPoints);
            VerticalCheck verticalChecker = new VerticalCheck(board, pl_number, possiblPoints);
            DiagonalCheck diagonalChecker=new DiagonalCheck(board, pl_number, possiblPoints);
            horizentalChecker.move(x, y);
            verticalChecker.move(x, y);
            diagonalChecker.move(x, y);

        }
        return possiblPoints;
    }
    
    /**
     * just clear the terminal;
     */
    public void clearScreen() {
        char escCode = 0x1B; // esc Ascii code
        System.out.print(String.format("%c[2J", escCode));
    }
    public Disk move(Block[][] board) 
    {
        return null;
    }
}