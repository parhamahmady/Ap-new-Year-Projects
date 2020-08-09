/**
 * Card
 */
public class Card extends Tool {

    private int color;// 0=Black,1=Red,2=Green,3=Yellow,4=Blue
    public Card(int color) {
        this.color = color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * @return the color
     */
    public int getColor() {
        return color;
    }

    /**
     * it will print card by showwing there Color and Id
     * 
     * @param x
     * @param y
     * @param id is the sign of the card
     */
    public void visiblePrint(int x, int y, String id, int number) {

        y = y * 12;

        for (int i = 0; i <= 6; i++) {

            System.out.print(String.format("%c[%d;%df", escCode, x + i + 1, y + 1));
            if (i == 0 || i == 6) {
                if (number < 10) {
                    System.out.printf("%c[37m%c[%dm\u5350 ", escCode, escCode, 40 + color, number);
                    System.out.printf("%c[37m%c[%dm\u26710%d", escCode, escCode, 40 + color, number);
                    System.out.printf("%c[37m%c[%dm\u2671 ", escCode, escCode, 40 + color, number);
                    System.out.printf("%c[37m%c[%dm\u534d", escCode, escCode, 40 + color, number);

                    // System.out.printf("%c[37m%c[%dm:():0%d:():", escCode, escCode, 40 +
                    // color,number);
                    continue;
                }
                System.out.printf("%c[37m%c[%dm\u5350 ", escCode, escCode, 40 + color, number);
                System.out.printf("%c[37m%c[%dm\u2671%d", escCode, escCode, 40 + color, number);
                System.out.printf("%c[37m%c[%dm\u2671 ", escCode, escCode, 40 + color, number);
                System.out.printf("%c[37m%c[%dm\u534d", escCode, escCode, 40 + color, number);
                continue;
            }
            if (i == 3) {
                System.out.printf("  %c[37m%c[%dm%s  ", escCode, escCode, 40 + color, id);
                continue;
            }

            System.out.print(String.format("%c[%dm          ", escCode, 40 + color));
        }

    }

    /**
     * print the card With No information
     * 
     * @param x
     * @param y
     */
    public void hidePrint(int x, int y) {
        y = y * 7;
        int color = 0;
        if (y % 2 == 0 && y != 1) {
            color = -1;
        }
        for (int i = 0; i <= 3; i++) {
            System.out.print(String.format("%c[%d;%df", escCode, x + i + 1, y + 1));
            if (i == 0 || i == 3) {
                System.out.printf("%c[31m%c[%dm:()():", escCode, escCode, 46 + color);
                continue;
            }
            if (i == 1) {
                System.out.printf("%c[31m%c[%dm  \\/  ", escCode, escCode, 46 + color);
                continue;
            }
            if (i == 2) {
                System.out.printf("%c[31m%c[%dm  /\\  ", escCode, escCode, 46 + color);
                continue;
            }
            System.out.print(String.format("%c[%dm      ", escCode, 46 + color));
        }

    }

    public Player[] chooseNextPlayer(Player[] players, Player currentPlayer) {
        Player[] tempPlayers = new Player[players.length];
        int i = 0;
        for (; i < players.length; i++) {// find the current player index in players
            if (players[i].equals(currentPlayer)) {
                break;
            }
        }
        for (int j = 0; j < players.length; j++) {
            i++;
            if (i >= players.length) {
                i = 0;
            }
            tempPlayers[j] = players[i];
        }
        return tempPlayers;
    }

    protected int findPlayerIndex(Player[] players, Player currentPlayer) {
        int i = 0;
        for (Player player : players) {
            if (player.equals(currentPlayer)) {
                break;
            }
            i++;
        }
        return i;

    }
}
