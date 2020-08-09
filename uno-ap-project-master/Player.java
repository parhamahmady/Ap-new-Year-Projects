import java.util.*;

/**
 * Player
 */
public class Player extends Tool {

    private String name;
    private int plNumber;// the number of the Player
    private ArrayList<Card> myCards;
    private int cardNumber;

    /**
     * the Counstructor
     * 
     * @param name     is the name of the player
     * @param plNumber is the number of the player
     */
    public Player(String name, int plNumber) {
        this.name = name;
        this.plNumber = plNumber;
        myCards = new ArrayList<Card>();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the cardNumber
     */
    public int getCardNumber() {
        return cardNumber;
    }

    /**
     * @return the plNumber
     */
    public int getPlNumber() {
        return plNumber;
    }

    /**
     * @param plNumber the plNumber to set
     */
    public void setPlNumber(int plNumber) {
        this.plNumber = plNumber;
    }

    /**
     * @return the myCards
     */
    public ArrayList<Card> getMyCards() {
        return myCards;
    }

    /**
     * add a card to mycards
     * 
     * @param card
     */
    public void addCard(Card card) {
        myCards.add(card);
        cardNumber = myCards.size();// reset the number of cards
    }

    /**
     * print all player's cards visibly
     */
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
            tempCard.visiblePrint(x, i, "", i);
            System.out.printf("%c[0m", escCode);
        }
    }

    /**
     * 
     * @param gameBoard
     * @param players
     * @return true if the player played
     */
    public void playCard(Board gameBoard, Player[] players) {
    }

    /**
     * remove the chosen card and set it as GroundCard
     * 
     * @param gameBoard
     * @param chosenCard the card want to remove
     */
    protected void removeChosenCard(Board gameBoard, Card chosenCard) {
        Iterator<Card> it = myCards.iterator();
        Card tempgroundCard= gameBoard.getGroundCard();
        while (it.hasNext()) {
            Card tempCard = it.next();
            if (tempCard.equals(chosenCard)) {
                it.remove();
                // Card tempgroundCard=gameBoard.getGroundCard;
                if (tempgroundCard instanceof WildDrawCard
                        || tempgroundCard instanceof ColorChangeCard) {// if the last ground card was Wild set
                                                                                // it black again
                    gameBoard.getGroundCard().setColor(0);
                }
                gameBoard.setGroundCard(tempCard);
                gameBoard.addToStorage(tempCard);
                if (!(tempCard instanceof DrawCard) && !(tempCard instanceof WildDrawCard)) { // if the Card was't Draw
                    gameBoard.resetDraw();
                }
                cardNumber = myCards.size();
                break;
            }
        }
    }

    /**
     * check we can play chosen Card or not
     * 
     * @param n         the index +1 of the chosen card
     * @param gameBoard
     * @return
     */
    protected boolean canChoose(int n, Board gameBoard) {
        Card chosenCard = myCards.get(n);// get the chosenCard
        Card groundCard = gameBoard.getGroundCard();
        if (chosenCard.getColor() == groundCard.getColor()) { // if they had same color
            return true;
        }
        if (chosenCard instanceof NumericCard && groundCard instanceof NumericCard) {// if they had same number
            NumericCard tempChosen = (NumericCard) chosenCard; // change the static type
            NumericCard tempground = (NumericCard) groundCard; // change the static type
            if (tempChosen.getNumber() == tempground.getNumber()) {
                return true;
            }
        }

        if (groundCard.getClass().equals(chosenCard.getClass()) && !(groundCard instanceof NumericCard)
                && !(chosenCard instanceof NumericCard)) {// if they were sameInstance
            return true;
        }
        return false;
    }

    /**
     * Check the player can use its cards or not according to groundCard
     * 
     * @param gameBoard
     * @return
     */
    protected boolean canPlay(Board gameBoard) {
        if (canPlayWithoutWild(gameBoard)) {
            return true;
        }
        Iterator<Card> it = myCards.iterator();
        while (it.hasNext()) { // check if the player has wild card
            Card temp = it.next();
            if (temp instanceof WildDrawCard || temp instanceof ColorChangeCard) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @return true if player can play witout using wild card
     */
    protected Boolean canPlayWithoutWild(Board gameBoard) {
        Iterator<Card> it = myCards.iterator();
        int i = 0;// count the indexes
        while (it.hasNext()) {
            it.next();
            if (canChoose(i, gameBoard)) {
                return true;
            }
            i++;
        }
        return false;
    }

    public int getMyScore() {
        int myScore = 0;
        Iterator<Card> it = myCards.iterator();
        while (it.hasNext()) { // iterate throw myCards
            Card tempCard = it.next();
            if (tempCard instanceof NumericCard) { // if the card was Numberic
                NumericCard temp2 = (NumericCard) tempCard;
                myScore += temp2.getNumber();
                continue;
            }
            if ((tempCard instanceof WildDrawCard) || (tempCard instanceof ColorChangeCard)) {
                myScore += 50;
                continue;
            }
            myScore += 20;
        }
        return myScore;
    }
}