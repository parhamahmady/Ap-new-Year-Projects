import java.awt.Point;
import java.util.ArrayList;

/**
 * Field is the game board
 */
public class Field {
    private Block[] myBlocks;

    /**
     * the counstructor it make the fields Blocks and add players to game board
     */
    public Field() {
        myBlocks = new Block[4];
        for (int i = 1; i <= 4; i++) { // make the fields
            myBlocks[i - 1] = new Block(i);
        }
    }

    /**
     * @return the myBlocks
     */
    public Block[] getMyBlocks() {
        return myBlocks;
    }

    /**
     * print the game board
     */
    public void print() {
        char escCode = 0x1B; // esc Ascii code
        clearScreen();
        for (int i = 0; i < 6; i++) { // print the location numbers

            System.out.print(String.format("%c[%d;%dfY:%d", escCode, 0, (i + 1) * 7, i));
            System.out.print(String.format("%c[%d;%dfX:%d", escCode, (i + 1) * 4 + 1, 0, i));
        }
        for (int i = 0; i <= 3; i++) { // print the blocks
            myBlocks[i].print();
        }
        System.out.print(String.format("%c[0m", escCode));// reset color settings
        System.out.println("");// go to next line
    }

    /**
     * just clear the terminal screen !
     */
    private void clearScreen() {
        char escCode = 0x1B; // esc Ascii code
        System.out.print(String.format("%c[2J", escCode));
    }

    /**
     * 
     * @param i is the number of the player who want to choose
     */
    public Ring choosedRing(Point point) {
        int whichBlock = findBlock(point);
        myBlocks[whichBlock - 1].setFull(true);
        return myBlocks[whichBlock - 1].getRing(point);
    }

    /**
     * 
     * @param point is the location of the ring that player want to add
     * @return false is not full, true is full
     */
    public boolean fullCheck(Point point) {
        int whichBlock = findBlock(point);
        return myBlocks[whichBlock - 1].fullCheck(point);
    }

    /**
     * 
     * @param point
     * @return the number of the block that contains point
     */
    public int findBlock(Point point) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        int whichBlock = 0;
        if (x <= 2) {
            if (y <= 2) {
                whichBlock = 1;
            } else {
                whichBlock = 3;
            }
            return whichBlock;
        } else {
            if (y <= 2) {
                whichBlock = 2;
            } else {
                whichBlock = 4;
            }
        }
        return whichBlock;
    }

    /**
     * 
     * @return false if one of the blocks was empty
     */
    public boolean emptyBlockCheck() {
        for (int i = 0; i <= 3; i++) {
            if (myBlocks[i].getFull() == false) {
                return false;
            }

        }

        return true;
    }

    /**
     * rotate a block
     * 
     * @param i is the number of the block that want to rotate
     */
    public void rotate(int i) {
        if (i == 0) {
            return;
        }
        if (i == 3) { // replace the 2 instead of 3
            i = 2;
            myBlocks[i - 1].rotate();
            return;
        }
        if (i == 2) { // replace 3 instead of 2
            i = 3;
            myBlocks[i - 1].rotate();
            return;
        }
        myBlocks[i - 1].rotate();
    }

    public boolean endCheck() {
        int winner = 0;
        VerticalCheck verticalChecker = new VerticalCheck();
        HorizentalCheck horizentalChecker = new HorizentalCheck();
        DiagonalCheck diagonalChecker = new DiagonalCheck();
        if (horizentalChecker.counter(myBlocks, 1) || verticalChecker.counter(myBlocks, 1)
                || diagonalChecker.counter(myBlocks, 1)) { // player 1
            winner++;
        }
        if (horizentalChecker.counter(myBlocks, 2) || verticalChecker.counter(myBlocks, 2)
                || diagonalChecker.counter(myBlocks, 2)) { // player 2
            // 2
            winner += 2;
        }
        switch (winner) {
            case 1:
                System.out.println("player one wins");
                return true;

            case 2:
                System.out.println("player two wins");
                return true;
            case 3:
                System.out.println("Equalss");
                break;
        }
        return false;
    }
}