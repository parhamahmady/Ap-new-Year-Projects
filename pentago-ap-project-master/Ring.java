import java.awt.Point;

/**
 * Ring
 */
public class Ring {
    private Point location;
    private int color;// 0=no color ; 1= black; 2=Red;

    /**
     * the counstructor
     * 
     * @param location
     * @param color
     */
    public Ring(int x, int y, int color) {
        location = new Point(x, y);
        this.color = color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    /**
     * @return the color
     */
    public int getColor() {
        return color;
    }

    /**
     * @return the location
     */
    public Point getLocation() {
        return location;
    }

    /**
     * 
     * @param colorCode is the background color
     */
    public void print(int colorCode) {
        char escCode = 0x1B; // esc Ascii code
        int x = (int) location.getX() * 4 + 3;
        int y = (int) location.getY() * 7 + 4;
        for (int i = 0; i < 3; i++) {
            System.out.print(String.format("%c[%d;%df", escCode, x + i + 1, y + 1));
            if (i == 1) {
                if (color == 1) {
                    System.out.printf("%c[30m  \u25CF%c[%dm   ", escCode, escCode, 42 + colorCode);
                    continue;
                }
                if (color == 0) {
                    System.out.printf("%c[30m  \u25CB%c[%dm   ", escCode, escCode, 42 + colorCode);
                    continue;
                }
                if (color == 2) {
                    System.out.printf("%c[30m  \uD83D\uDD34  ", escCode);
                    continue;
                }

            }

            System.out.print(String.format("%c[%dm      ", escCode, 42 + colorCode));
        }

    }

}