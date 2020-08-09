import java.util.ArrayList;
import java.util.HashSet;

import java.awt.Point;

/**
 * DiagonalCheck
 */
public class DiagonalCheck extends MoveCheck {
    private HashSet<Point> possPoints;

    public DiagonalCheck(Block[][] board, int pl_number, HashSet<Point> possiblPoints) {
        super(board, pl_number, possiblPoints);

    }

    public HashSet<Point> move(int x, int y) {

        leftToright(x, y);
        rightToleft(x, y);
        return possPoints;
    }

    /**
     * trace from left to right side
     * 
     * @param x
     * @param y
     */
    private void leftToright(int x, int y) {
        int i = x;
        int j = y;
        for (; i != 0 && j != 0; i--, j--) { // to find the corner place in the left side

        }

        for (; i < 8 && j < 8; i++, j++) {
            possiblePoints(i, j, i, x, i - 1, j - 1, i + 1, j + 1);
        }
    }

    /**
     * trace from right to left side
     * 
     * @param x
     * @param y
     */
    private void rightToleft(int x, int y) {
        int i = x;
        int j = y;
        for (; i != 0 && j != 7; j++, i--) { // to find the corner place in the right side

        }
        for (; i < 8 && j >= 0; i++, j--) {
            possiblePoints(i, j, i, x, i-1, j+1, i+1, j-1);
        }
    }
}