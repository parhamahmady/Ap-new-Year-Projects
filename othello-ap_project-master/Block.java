import java.awt.Point;



/**
 * Block
 */
public class Block {
    private Point point; // x and y of the block
    private Disk myDisk; // is the disk that will fill this block;
    private boolean isFull; // to find out if the block was full;

    public Block(int x, int y) {
        point = new Point(x, y);
    }

    /**
     * @param isFull the isFull to set
     */
    public void setFull(boolean isFull) {
        this.isFull = isFull;
    }

    /**
     * @param myDisk the myDisk to set
     */
    public void setMyDisk(Disk myDisk) {
        this.myDisk = myDisk;
    }

    /**
     * @return the myDisk
     */
    public Disk getMyDisk() {
        return myDisk;
    }

    /**
     * @return the point
     */
    public Point getPoint() {
        return point;
    }

    /**
     * 
     * @return true if the block was full;
     */
    public boolean getFull() {
        return isFull;
    }

    /**
     * it will print this block
     */
    public void print() {
        char escCode = 0x1B; // esc Ascii code
        int x = (int) point.getX() * 4 + 3;
        int y = (int) point.getY() * 7 + 3;
        for (int i = 0; i < 3; i++) {
            System.out.print(String.format("%c[%d;%df", escCode, x + i + 1, y + 1));
            if (isFull) {
                if (i == 1) {
                    if (myDisk.getColor() == 1) {
                        System.out.printf("%c[30m  \u25CF%c[47m   ", escCode, escCode);
                        continue;
                    }
                    System.out.printf("%c[30m  \u25CB%c[47m   ", escCode, escCode);
                    continue;
                }
            }

            System.out.print(String.format("%c[47m      ", escCode));
        }

    }
}