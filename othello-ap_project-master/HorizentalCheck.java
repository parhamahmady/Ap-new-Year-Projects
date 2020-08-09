import java.util.ArrayList;
import java.util.HashSet;

import java.awt.Point;


/**
 * HorizentalCheck
 */
public class HorizentalCheck extends MoveCheck {
    private HashSet<Point>possPoints ;
    public HorizentalCheck(Block[][] board,int pl_number,HashSet<Point>possiblPoints ) {
        super(board,pl_number,possiblPoints);

    }

    public HashSet<Point> move(int x, int y) {

        for (int i = 0; i < 8; i++) {
            possPoints=possiblePoints(x,i,i,y,x,i-1,x,i+1);
        }
        return possPoints;
    }
}