import java.util.*;

/**
 * ComputerPlayer
 */
public class ComputerPlayer extends Player {

    /**
     * Counstructor
     * 
     * @param name
     * @param plNumber
     */
    public ComputerPlayer(String name, int plNumber) {
        super(name, plNumber);
    }

    public void playCard(Board gameBoard, Player[] players) {
        for (int l = 0; l < 2; l++) { // maximum chance to choose is 2 time
            if (!canPlay(gameBoard) && l != 1) {
                delay();
                gameBoard.giveCard(this, 1);
                continue;
            }
            int n;
            while (true) {
                if (!canPlay(gameBoard)) {
                    break;
                }
                n = (int) Math.abs((Math.random() * 100) % getCardNumber());
                if (n < 0 || n > getCardNumber()) { // check the value of n
                    continue;
                }
                ArrayList<Card> tempMyCards = getMyCards();
                Card tempCard = tempMyCards.get(n);
                if (tempCard instanceof ColorChangeCard || tempCard instanceof WildDrawCard) { // if the card was Wild
                    if (!canPlayWithoutWild(gameBoard)) {
                        int k = (int) Math.abs((Math.random() * 100) % 4 + 1);
                        tempCard.setColor(k);
                        removeChosenCard(gameBoard, tempCard);
                        return;
                    }
                    continue;
                }
                if (canChoose(n, gameBoard)) {// check Can choose that Card or Not
                    removeChosenCard(gameBoard, getMyCards().get(n));
                    return;
                }
            }

        }
    }

    public void printMyCards() {
        System.out.print(String.format("%c[%d;%dfPlayer : %s", escCode, 29, 1, getName()));// print the name
        ArrayList<Card> myCards = getMyCards();
        Iterator<Card> it = myCards.iterator();

        for (int i = 1; it.hasNext(); i++) { // print the cards in order
            Card tempCard = it.next();
            int x = 30;
            if (i > 10) {
                x = 40;// to go to the next line
            }
            System.out.printf("%c[0m", escCode);
            tempCard.hidePrint(x, i);
            System.out.printf("%c[0m", escCode);
        }
    }

}
