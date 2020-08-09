import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Point;

/**
 * Player is every kind of player in this game
 */
public class Player {
    private String name;
    private int plNumber;
    

    /**
     * the counstructor
     * 
     * @param name
     * @param plNumber
     */
    public Player(String name, int plNumber) {
        this.name = name;
        this.plNumber = plNumber;
        
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the plNumber
     */
    public int getPlNumber() {
        return plNumber;
    }

    /**
     * add point to array list and board
     * 
     * @param board is the game board
     * @param point is the choosed point
     * @return true if the ring was empty,false was full
     */
    public boolean addRing(Field board, Point point) {
        if (!board.fullCheck(point)) {
            Ring tempRing = board.choosedRing(point);
            tempRing.setColor(plNumber);
            return true;
        }

        return false;
    }

    /**
     * just for inheritance
     * 
     * @param board
     */
    public Point chooseRing(Field board,Point lastPoint) {
        return null;
    }

    /**
     * 
     * @param board
     * @param blockNumber is the number of the block want ro rotate
     */
    public void rotate(Field board, int blockNumber) {
        board.rotate(blockNumber);
    }

    
}