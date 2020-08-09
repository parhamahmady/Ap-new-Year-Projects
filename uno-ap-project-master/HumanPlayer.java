import java.util.*;

/**
 * HumanPlayer
 */
public class HumanPlayer extends Player {
    /**
     * Counstructor
     * 
     * @param name
     * @param plNumber
     */
    public HumanPlayer(String name, int plNumber) {
        super(name, plNumber);
    }

    /**
     * select a Card to play
     */
    public void playCard(Board gameBoard, Player[] players) {
        for (int l = 0; l < 2; l++) { // maximum chance to choose is 2 time
            if (!canPlay(gameBoard) && l != 1) {
                System.out.print(String.format("%c[%d;%df", escCode, 20, 90));
                System.out.println("You Can't play , Get a Card from Storage");
                delay();
                gameBoard.giveCard(this, 1);
                gameBoard.print(players);
                printMyCards();
                continue;
            }
            Scanner fScanner = new Scanner(System.in);
            int n;
            int i = 0;// line number
            while (true) {
                if (!canPlay(gameBoard)) {
                    System.out.print(String.format("%c[%d;%df", escCode, 20, 90));
                    System.out.println("You Can't play ");
                    break;
                }
                System.out.print(String.format("%c[%d;%df", escCode, 20 + i, 90));
                System.out.printf("Choose one of your cards : ");
                n = fScanner.nextInt();
                if (n < 0 || n > getCardNumber()) { // check the value of n
                    System.out.println("You Can't !!");
                    continue;
                }
                ArrayList<Card> tempMyCards = getMyCards();
                Card tempCard = tempMyCards.get(n - 1);
                if (tempCard instanceof ColorChangeCard || tempCard instanceof WildDrawCard) { // if the card was Wild
                    if (!canPlayWithoutWild(gameBoard)) {
                        System.out.println("Which Color do you want to change ? \n1)Red 2)Green 3)Yellow 4)Blue ");    
                        int k=fScanner.nextInt();
                        while (true) {//check the input number
                            if (i>0 || i<=4) {
                                break;
                            }
                            System.out.println("You Cant't");
                            System.out.println("Which Color do you want to change ? \n1)Red 2)Green 3)Yellow 4)Blue ");    
                            k=fScanner.nextInt();
                        }
                        tempCard.setColor(k);
                        removeChosenCard(gameBoard,tempCard);
                        return;
                    }
                    System.out.println("You Can't");
                    continue;
                }
                if (canChoose(n - 1, gameBoard)) {// check Can choose that Card or Not
                    removeChosenCard(gameBoard, getMyCards().get(n - 1));
                    return;
                }
                System.out.print(String.format("%c[%d;%df", escCode, 20 + i + 1, 90));
                System.out.printf("You can't Choose This one");
                delay();
                i++;
            }
        }
    }
}
