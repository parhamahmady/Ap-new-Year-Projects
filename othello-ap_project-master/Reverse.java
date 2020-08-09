import java.awt.Point;
import java.util.*;

/**
 * Reverse
 */
public class Reverse {
    private Block[][] board;
    private Player[] players;
    private Disk lastDisk;
    private int owner;
    private ArrayList<Disk> myDisks;

    /**
     * the counstructor
     * 
     * @param board   is the game map
     * @param players the players of the game
     * @param lasDisk is the last disk that add to board
     */
    public Reverse(Block[][] board, Player[] players, Disk lasDisk) {
        this.board = board;
        this.players = players;
        this.lastDisk = lasDisk;
        owner = lastDisk.getOwner();
        myDisks = players[owner - 1].getMyDisks();
    }

    /**
     * reverse and change the owner of the Disk
     * 
     * @param shouldReverse
     */
    private void reverser(ArrayList<Disk> shouldReverse) {
        Iterator<Disk> it = shouldReverse.iterator();
        while (it.hasNext()) {
            Disk input = it.next();
            players[owner - 1].getMyDisks().add(input);
            players[input.getOwner() - 1].removeDisk(input);
            input.setOwner(owner);
        }

    }

    public void horizentalReverse() {
        ArrayList<Disk> shouldReverse = new ArrayList<Disk>();
        Iterator<Disk> it = myDisks.iterator();

        while (it.hasNext()) {
            Disk temp = it.next();
            if (temp.getPoint().getX() == lastDisk.getPoint().getX()) { // find another disk with same x
                if (temp.getPoint().getY() <= lastDisk.getPoint().getY()) { // chech the lastDisk side (left or right)
                    int x = (int) temp.getPoint().getX();
                    int y = (int) temp.getPoint().getY();
                    for (int i = y + 1; i < (int) lastDisk.getPoint().getY(); i++) {
                        if (board[x][i].getFull()) { // check if the block was full !!!
                            shouldReverse.add(board[x][i].getMyDisk());
                        }

                    }
                } else {
                    int x = (int) lastDisk.getPoint().getX();
                    int y = (int) lastDisk.getPoint().getY();
                    for (int i = y + 1; i < (int) temp.getPoint().getY(); i++) {
                        if (board[x][i].getFull()) { // check if the block was full !!!
                            shouldReverse.add(board[x][i].getMyDisk());
                        }
                    }
                }
            }

        }
        reverser(shouldReverse);
    }

    public void verticalReverse() {
        ArrayList<Disk> shouldReverse = new ArrayList<Disk>();
        Iterator<Disk> it = myDisks.iterator();

        while (it.hasNext()) {
            Disk temp = it.next();
            if (temp.getPoint().getY() == lastDisk.getPoint().getY()) { // find another disk with same Y
                if (temp.getPoint().getX() <= lastDisk.getPoint().getX()) { // chech the lastDisk side (up or down)
                    int x = (int) temp.getPoint().getX();
                    int y = (int) temp.getPoint().getY();
                    for (int i = x + 1; i < (int) lastDisk.getPoint().getX(); i++) {
                        if (board[i][y].getFull()) { // check if the block was full !!!
                            shouldReverse.add(board[i][y].getMyDisk());
                        }

                    }
                } else {
                    int x = (int) lastDisk.getPoint().getX();
                    int y = (int) lastDisk.getPoint().getY();
                    for (int i = x + 1; i < (int) temp.getPoint().getX(); i++) {
                        if (board[i][y].getFull()) { // check if the block was full !!!
                            shouldReverse.add(board[i][y].getMyDisk());
                        }
                    }
                }
            }

        }
        reverser(shouldReverse);
    }

    public void diagonalReverse() {
        ArrayList<Disk> shouldReverse = new ArrayList<Disk>();
        Iterator<Disk> it = myDisks.iterator();
        while (it.hasNext()) {
            Disk temp = it.next();
            int x = (int) Math.abs(temp.getPoint().getX() - lastDisk.getPoint().getX());
            int y = (int) Math.abs(temp.getPoint().getY() - lastDisk.getPoint().getY());
            if (x == y) { // check if there were diagonal;
                boolean xBig = temp.getPoint().getX() >= lastDisk.getPoint().getX();
                boolean xSmall = temp.getPoint().getX() <= lastDisk.getPoint().getX();
                boolean yBig = temp.getPoint().getY() >= lastDisk.getPoint().getY();
                boolean ySmall = temp.getPoint().getY() <= lastDisk.getPoint().getY();
                if ((xBig && yBig) || (xSmall && ySmall)) { // check right to left or left to right;
                    letfToright(shouldReverse, temp);
                } else {
                    rightToleft(shouldReverse, temp);
                }
            }
        }
        reverser(shouldReverse);

    }

    private void letfToright(ArrayList<Disk> shouldReverse, Disk temp) {
        if (lastDisk.getPoint().getX() < temp.getPoint().getX()) { // to check  up and down
            int i = (int) lastDisk.getPoint().getX() + 1;
            int j = (int) lastDisk.getPoint().getY() + 1;

            for (; i < (int) temp.getPoint().getX() && j < temp.getPoint().getY(); i++, j++) {
                if (board[i][j].getFull()) {
                    shouldReverse.add(board[i][j].getMyDisk());
                }
            }
        } else {
            
            int i = (int) lastDisk.getPoint().getX() - 1;
            int j = (int) lastDisk.getPoint().getY() - 1;
            for (; i > (int) temp.getPoint().getX() && j > temp.getPoint().getY(); i--, j--) {
                if (board[i][j].getFull()) {
                    shouldReverse.add(board[i][j].getMyDisk());
                }
            }
        }
    }

    private void rightToleft(ArrayList<Disk> shouldReverse, Disk temp) {
        if (lastDisk.getPoint().getX() < temp.getPoint().getX()) { // to check  up and down
            int i = (int) lastDisk.getPoint().getX() + 1;
            int j = (int) lastDisk.getPoint().getY() - 1;

            for (; i < (int) temp.getPoint().getX() && j > temp.getPoint().getY(); i++, j--) {
                if (board[i][j].getFull()) {
                    shouldReverse.add(board[i][j].getMyDisk());
                }
            }
        } else {
            
            int i = (int) lastDisk.getPoint().getX() - 1;
            int j = (int) lastDisk.getPoint().getY() + 1;
            for (; i > (int) temp.getPoint().getX() && j < temp.getPoint().getY(); i--, j++) {
                if (board[i][j].getFull()) {
                    shouldReverse.add(board[i][j].getMyDisk());
                }
            }
        }
    }
}
