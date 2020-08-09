import java.awt.Point;
import java.util.ArrayList;

/**
 * Block
 */
public class Block {
    private int blockNumber;
    private Ring[][] myRings;
    private boolean isFull;

    public Block(int blockNumber) {
        isFull = false;
        this.blockNumber = blockNumber;
        myRings = new Ring[3][3];
        ringBuilder(blockNumber, myRings);
    }

    // make and set rings locations
    private void ringBuilder(int blockNumber, Ring[][] mRings) {
        // mRings = new Ring[3][3];
        for (int i = 0; i < 3; i++) { // trece throght myrings
            for (int j = 0; j < 3; j++) {
                int x = j;
                int y = i;
                if (blockNumber % 2 == 0 && blockNumber != 1) { // for block 2 and 4
                    x = 3 + j;
                }
                if (blockNumber == 3 || blockNumber == 4) { // for block 3 and 4
                    y = 3 + i;
                }
                mRings[j][i] = new Ring(x, y, 0);
            }
        }
    }

    /**
     * print the block
     */
    public void print() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                myRings[j][i].print(blockNumber);
            }
        }
    }

    public void setRing(int x, int y, int color) {
        myRings[x][y].setColor(color);
    }

    /**
     * 
     * @param point
     * @return the ring with same locaion as point
     */
    public Ring getRing(Point point) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (myRings[j][i].getLocation().equals(point)) {
                    return myRings[j][i];
                }
            }
        }
        return null;
    }

    /**
     * 
     * @param n is the number of the player
     * @return all player number i rings in this block
     */
    public ArrayList<Ring> getPlayerRings(int n, ArrayList<Ring> tempRings) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (myRings[i][j].getColor() == n) {
                    tempRings.add(myRings[i][j]);
                }
            }
        }
        return tempRings;
    }

    /**
     * 
     * @return isfull
     */
    public boolean getFull() {
        return isFull;
    }

    /**
     * @param isFull the isFull to set
     */
    public void setFull(boolean isFull) {
        this.isFull = isFull;
    }

    /**
     * 
     * @param point
     * @return false is not full , true is full
     */
    public boolean fullCheck(Point point) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (myRings[j][i].getLocation().equals(point)) {
                    if (myRings[j][i].getColor() == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * rotate whole myRings matrix 90 degree
     */
    public void rotate() {
        Ring[][] tempRings = new Ring[3][3];
        ringBuilder(blockNumber, tempRings); // make a new block rings
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tempRings[i][j].setColor(myRings[2 - j][i].getColor());// set the new rings color
            }
        }
        myRings = tempRings;// replace the rings
    }
}