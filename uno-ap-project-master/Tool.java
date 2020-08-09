/**
 * has some methods that almost all other class use them Tool
 */
public class Tool {
    public char escCode = 0x1B; // esc Ascii code
    /**
     * just clear the screen
     */
    public void clearScreen() {
        char escCode = 0x1B; // esc Ascii code
        System.out.print(String.format("%c[2J", escCode));
    }

    /**
     * just make delay
     */
    public void delay() {
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {

        }
    }

}