/**
 * DiagonalCheck
 */
public class DiagonalCheck extends EndCheck {
    /**
     * trace from left side to right side
     * 
     * @param blocks
     * @param cl
     * @return
     */
    public boolean counter(Block[] blocks,int color) {
        if (leftToright(blocks, color )|| rightToleft(blocks, color)) {
            return true;
        }
        return false;
    }
    private boolean leftToright(Block[] blocks, int cl) {
        for (int c = 0; c <= 1; c++) { // up side
            int counter = 1;
            int j = c;
            for (int i = 0; i + 1 < 6 && j + 1 < 6; i++, j++) {
                counter = verticalCounter(blocks, i, j, i + 1, j + 1, counter, cl);
                if (counter >= 5) {
                    return true;
                }
            }
        }
        int i = 1;
        int counter = 1;
        for (int j = 0; j + 1 < 6 && i + 1 < 6; j++, i++) { // downside
            counter = verticalCounter(blocks, i, j, i + 1, j + 1, counter, cl);
            if (counter >= 5) {
                return true;
            }
        }

        return false;
    }

    /**
     * trace from right side to left side
     * 
     * @param blocks
     * @param cl
     * @return
     */
    private boolean rightToleft(Block[] blocks, int cl) {
        for (int c = 0; c <= 1; c++) { // up side
            int j = 5 - c;
            int counter = 1;
            for (int i = 0; j - 1 >= 0 && i + 1 < 6; j--, i++) {
                counter = verticalCounter(blocks, i, j, i + 1, j - 1, counter, cl);
                if (counter >= 5) {
                    return true;
                }
            }

        }
        int i = 1;
        int counter = 1;
        for (int j = 5; j - 1 >= 0 && i + 1 < 6; j--, i++) {
            counter = verticalCounter(blocks, i, j, i + 1, j - 1, counter, cl);
            if (counter >= 5) {
                return true;
            }
        }
        return false;
    }
}