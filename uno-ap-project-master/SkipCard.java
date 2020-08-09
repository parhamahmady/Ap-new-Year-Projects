/**
 * has a color and skip from the next player SkipCard
 */
public class SkipCard extends ActionCard {

    public SkipCard(int color) {
        super(color);
    }

    public void visiblePrint(int x, int y, String id, int number) {
        String iid = "->\u26d4<-";
        super.visiblePrint(x, y, iid, number);
    }

    public Player[] action(Player[] players, Player currentPlayer,Board gamebBoard) {
        int i = findPlayerIndex(players, currentPlayer);
        if (i+1>=players.length) {
            i=0; 
        }
        return chooseNextPlayer(players, players[i + 1]);
    }

}