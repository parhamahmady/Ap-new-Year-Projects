import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Point;

/**
 * EndCheck
 */
public class EndCheck extends Field {

    /**
     * 
     * @param rings
     * @param blocks
     * @param i is point 1 x
     * @param j is point 1 y
     * @param k is point 2 x
     * @param f is point 2 y
     * @return true if 5 rings in same color were behind eachOther
     */
    public int verticalCounter(Block[] blocks, int i, int j, int k, int f ,int counter,int cl) {
        Point point = new Point(i, j);
        Point point2 = new Point(k, f);
        int whichBlock = findBlock(point); // get the number of blocks
        int whichBlock2 = findBlock(point2);
        int color = blocks[whichBlock - 1].getRing(point).getColor(); // get the color
        int color2 = blocks[whichBlock2 - 1].getRing(point2).getColor();
        if (counter >= 5) { // if counter was more than 5 won't continue
            return counter;
        }
        if (color == color2 && color2 == cl && color == cl) {
            counter++;
        } else {
            counter = 1;
        }

        return counter;
    }
    
}