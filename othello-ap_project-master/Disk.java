import java.util.*;

import java.awt.Point;

/**
 * Disk
 */
public class Disk {
    private Point point;
    private int owner;
    private int color;

    /**
     * 
     * @param point is the x and y of the disk in the board
     * @param owner is the number of the player who is the owner of this disk
     * @param color 1=black , 0=white
     */
    public Disk(Point point, int owner) {
        this.point = point;
        this.owner = owner;
        color = owner - 1;
    }

    /**
     * @return the owner
     */
    public int getOwner() {
        return owner;
    }

    /**
     * @return the point
     */
    public Point getPoint() {
        return point;
    }

    /**
     * @return the color
     */
    public int getColor() {
        return color;
    }
    /**
     * @param owner the owner to set
     */
    public void setOwner(int owner) {
        this.owner = owner;
        color=owner-1;
    }
}