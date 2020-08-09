import java.util.ArrayList;

/**
 * 
 * WildDrawCard
 */
public class WildDrawCard extends WildCard {
    private int drawNumber;

    /**
     * the counstructor
     */
    public WildDrawCard() {
        super();// its Black by default
        drawNumber = 4;
    }

    /**
     * @return the drawNumber
     */
    public int getDrawNumber() {
        return drawNumber;
    }

    /**
     * print the card with special logo
     */
    public void visiblePrint(int x, int y, String id, int number) {
        String iid = "\uD83C\uDCCF+4\uD83C\uDCCF";
        super.visiblePrint(x, y, iid, number);
    }

    public Player[] action(Player[] players, Player currentPlayer, Board gamebBoard) {
        int i = findPlayerIndex(players, currentPlayer) + 1;
        if (i >= players.length) { // check the index range
            i = 0;
        }
        if (gamebBoard.getLoopDraw() != 0) {
            ArrayList<Card> tempCards = currentPlayer.getMyCards();
            for (int j = 0; j < gamebBoard.getLoopDraw(); j++) {
                players[i].addCard(tempCards.get(tempCards.size()-j-1));
                tempCards.remove(tempCards.size()-j-1);
            }
        }
        gamebBoard.giveCard(players[i], 4);
        return chooseNextPlayer(players, currentPlayer);
    }
}