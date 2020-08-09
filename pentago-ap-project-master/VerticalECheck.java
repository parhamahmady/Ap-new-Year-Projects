import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Point;

/**
 * VerticalECheck
 */
public class VerticalECheck extends EndCheck {
    private Block[] myBlocks;
    // 
    public boolean verticalCounter(Field board, ArrayList<Ring> rings) {
        int counter = 0;
        for (int i = 0; i < rings.size() && counter < 5; i++) { // to compare one by one
            Iterator<Ring> it = rings.iterator();// to trace throw the array list
            Ring temp = rings.get(i);
            while (it.hasNext() && counter < 5) {
                counter = 0;
                Ring currentRing = it.next();
                if (currentRing.getLocation().getX() == temp.getLocation().getX()) { // if has same x
                    int start = (int) temp.getLocation().getY() + 1;
                    int end = (int) currentRing.getLocation().getY();
                    if (start > end) { // if the start was after end
                        int t = start;
                        start = end;
                        end = t;
                    }
                    for (int j = start; j < end; j++) {
                        Point point = new Point((int) temp.getLocation().getX(), j);
                        myBlocks = getMyBlocks();
                        int whichBlock = findBlock(point);
                        if (myBlocks[whichBlock - 1].getRing(point).getColor() == temp.getColor()) {
                            counter++;
                        } else {
                            break;
                        }
                    }
                }
            }

        }

        if (counter == 5) {
            return true;
        }
        return false;
    }

}