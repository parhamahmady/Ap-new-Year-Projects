import java.util.ArrayList;
import java.util.HashSet;

import java.awt.Point;

/**
 * HorizentalCheck
 */
public class VerticalCheck extends MoveCheck {

    private HashSet<Point>possPoints ;
    public VerticalCheck(Block[][] board,int pl_number,HashSet<Point>possiblPoints) {
        super(board,pl_number,possiblPoints);
    }

    public HashSet<Point> move(int x, int y) {

        for (int i = 0; i < 8; i++) {
            possPoints=possiblePoints(i,y,i,x,i-1,y,i+1,y);
        }
        return possPoints;
    }
}