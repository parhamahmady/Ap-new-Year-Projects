import java.util.*;

import java.awt.Point;

/**
 * MoveCheck
 */
public class MoveCheck {
    private Block[][] board;
    private int pl_number;
    private HashSet<Point> possiblPoints;

    public MoveCheck(Block[][] board, int pl_number, HashSet<Point> possiblPoints) {
        this.board = board;
        this.possiblPoints = possiblPoints;
        this.pl_number = pl_number;
    }

    /**
     * 
     * @param i 
     * @param j
     * @param c to find out the side of the disk
     * @param k the  basis to check the side
     * @param m board x param on  before side
     * @param n board y param on before side
     * @param d board x param on after side
     * @param v board y param on after side
     * @return the possible move for the player
     */
    public HashSet<Point> possiblePoints(int i, int j, int c, int k, int m, int n, int d, int v) {

        if (board[i][j].getFull()) {
            if (c <= k) { // to find out the side of the disk
                if (board[i][j].getMyDisk().getOwner() != pl_number) {
                    if (m >= 0 && n >= 0 && !board[m][n].getFull()) {
                        possiblPoints.add(board[m][n].getPoint());

                    }
                }
            } else {
                if (board[i][j].getMyDisk().getOwner() != pl_number) {
                    if (d < 8 && v < 8 && !board[d][v].getFull()) {
                        possiblPoints.add(board[d][v].getPoint());

                    }
                }
            }
        }

        // }
        return possiblPoints;
    }

    public void printPossibleMoves() {
        Iterator<Point> it = possiblPoints.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }
}