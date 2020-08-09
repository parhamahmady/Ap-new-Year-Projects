import java.util.*;

/**
 * Board
 */
public class Board extends Tool {
    private ArrayList<Card> storage;
    private Card groundCard;
    private int loopDraw;

    public Board() {
        storage = new ArrayList<Card>();
        loopDraw = 0;
        cardMaker();
        cardMixer();
    }

    /**
     * @param groundCard the groundCard to set
     */
    public void setGroundCard(Card groundCard) {
        this.groundCard = groundCard;
    }

    /**
     * @return the groundCard
     */
    public Card getGroundCard() {
        return groundCard;
    }

    /**
     * @param loopDraw the loopDraw to set
     */
    public void setLoopDraw(int loopDraw) {
        this.loopDraw += loopDraw;
    }

    /**
     * @return the loopDraw
     */
    public int getLoopDraw() {
        return loopDraw;
    }

    /**
     * reset the loopdraw ro 0
     */
    public void resetDraw() {
        loopDraw = 0;
    }

    /**
     * add a card to Storage
     * 
     * @param card
     */
    public void addToStorage(Card card) {
        storage.add(card);
    }

    /**
     * run the groundCard's action
     * 
     * @param players
     * @param currentPlayer
     * @return the new array of players
     */
    public Player[] thinker(Player[] players, Player currentPlayer) {

        if (groundCard instanceof ActionCard) {
            ActionCard tempActionCard = (ActionCard) groundCard;
            return tempActionCard.action(players, currentPlayer, this);
        } else {
            return groundCard.chooseNextPlayer(players, currentPlayer);
        }

    }

    /**
     * new all kind of cards and set the first groundCard randomly
     */
    public void cardMaker() {
        for (int i = 1; i <= 4; i++) { // make all 4 coloric cards
            for (int j = 0; j < 10; j++) { // make numeric cards
                storage.add(new NumericCard(i, j));
            }
            storage.add(new SkipCard(i));// make skip
            storage.add(new ReverseCard(i));// make reverse
            storage.add(new DrawCard(i));// make draw +2
            storage.add(new ColorChangeCard());// make colorChanger
            storage.add(new WildDrawCard());// make wildDraw +4
        }
        while (true) {// choos the first groundCard randomly

            int rand = (int) Math.abs((Math.random() * 100) % storage.size());
            if (!(storage.get(rand) instanceof WildDrawCard) && !(storage.get(rand) instanceof ColorChangeCard)) {
                setGroundCard(storage.get(rand));
                storage.remove(rand);
                break;
            }

        }

    }

    /**
     * mix all storage cards
     */
    private void cardMixer() {
        for (int j = 0; j < 3; j++) { // mix three times
            int rand = (int) Math.abs((Math.random() * 100) % (storage.size() / 2));
            int rand2 = (int) Math.abs((Math.random() * 100) % storage.size());
            for (int i = rand; i <= rand2 && i + 5 <= rand2; i = i + 2) {
                Collections.swap(storage, i, i + 5);
            }
        }

    }

    /**
     * 
     * @param player is the player that gives the cards
     * @param count  is How many card ?1
     */
    public void giveCard(Player player, int count) {
        cardMixer();

        for (int i = 0; i < count; i++) {
            int rand = (int) Math.abs((Math.random() * 100) % storage.size());
            player.addCard(storage.get(rand));
            storage.remove(rand);
        }
    }

    /**
     * print the Information of the ground
     */
    public void print(Player[] players) {
        int x = 5;
        int y = 40;
        System.out.printf("%c[0m", escCode);// reset all settings
        clearScreen();
        groundCard.visiblePrint(13, 4, "", 99);
        System.out.printf("%c[0m", escCode);// reset all settings
        System.out.print(String.format("%c[%d;%dfNext Players :", escCode, 12, 60));// print the players information
        for (int i = 1; i < players.length; i++) {
            System.out.print(String.format("%c[%d;%df%s (Cards : %d)", escCode, 12 + i + 1, 60, players[i].getName(),
                    players[i].getCardNumber()));
        }
        for (int i = 0; i <= 20; i++) { // print the shape of ground
            System.out.print(String.format("%c[%d;%df", escCode, x + i + 1, y + 1));
            System.out.printf("%c[0m%c[33m**", escCode, escCode);
            if (i == 0 || i == 20) {
                for (int j = 0; j < 7; j++) {

                    System.out.printf("%c[0m%c[31m:()():", escCode, escCode, escCode);
                }
            }
            System.out.print(String.format("%c[%d;%df", escCode, x + i + 1, y + 1 + 43));
            System.out.printf("%c[0m%c[36m**", escCode, escCode);
            System.out.print(String.format("%c[%d;%df", escCode, x + i + 2, y + 1));
        }
        System.out.printf("%c[0m", escCode);// reset
        System.out.println(loopDraw);
    }

    /**
     * 
     * @param players
     * @return true if some one has no Card
     */
    public boolean endCheck(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            if (players[i].getCardNumber() == 0) {
                return true;
            }
        }
        return false;
    }

}