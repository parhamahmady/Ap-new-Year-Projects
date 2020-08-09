import java.util.ArrayList;

/**
 * VerticalECheck
 */
public class HorizentalCheck extends EndCheck {

    public boolean counter(Block[] blocks,int color) {
        int counter = 1;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j + 1 < 6; j++) {
                counter = verticalCounter(blocks, i, j, i, j+1, counter, color);
                if (counter >= 5) {
                    System.out.println(counter);
                    return true;
                }
            }
        }
        return false;
    }
}