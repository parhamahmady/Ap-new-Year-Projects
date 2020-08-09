/**
 * this is black card and change the Ground card's color ColorChangeCard
 */
public class ColorChangeCard extends WildCard {
    public ColorChangeCard() {
        super();// it's color is black by default
    }

    public void visiblePrint(int x, int y, String id, int number) {
        String iid=" \uD83C\uDCCF\uD83C\uDCCF ";
        super.visiblePrint(x, y, iid, number);
    }

    public Player[] action(Player[] players,Player currentPlayer,Board gamebBoard){
        return chooseNextPlayer(players, currentPlayer);
    }
}