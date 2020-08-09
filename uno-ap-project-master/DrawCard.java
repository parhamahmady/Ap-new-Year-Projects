import java.util.*;
/**
 * DrawCard
 */
public class DrawCard extends ActionCard {
    private int drawNumber;

    public DrawCard(int color) {
        super(color);
        drawNumber = 2;
    }

    /**
     * @return the drawNumber
     */
    public int getDrawNumber() {
        return drawNumber;
    }

    public void visiblePrint(int x, int y, String id, int number) {
        String iid = " \u2734+2\u2734 ";
        super.visiblePrint(x, y, iid, number);
    }

    public Player[] action(Player[] players, Player currentPlayer, Board gamebBoard) {
        int i = findPlayerIndex(players, currentPlayer) + 1;
        if (i >= players.length) {// check the index range
            i = 0;
        }
        if (gamebBoard.getLoopDraw() != 0) {
            ArrayList<Card> tempCards = currentPlayer.getMyCards();
            for (int j = 0; j < gamebBoard.getLoopDraw(); j++) {
                players[i].addCard(tempCards.get(tempCards.size()-j-1));
                tempCards.remove(tempCards.size()-j-1);
            }
        }
        gamebBoard.giveCard(players[i], 2);
        return chooseNextPlayer(players, currentPlayer);
    }
}