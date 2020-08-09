/**
 * this card has a color and reverse the rotation of the game ReverseCard
 */
public class ReverseCard extends ActionCard {
    /**
     * this is the counstructor
     * 
     * @param color
     */
    public ReverseCard(int color) {
        super(color);
    }

    public void visiblePrint(int x, int y, String id, int number) {
        String iid = "->\u27f2 <-";
        super.visiblePrint(x, y, iid, number);
    }

    public Player[] action(Player[] players, Player currentPlayer,Board gamebBoard) {
        int i=findPlayerIndex(players, currentPlayer);
        Player[] tempPlayers=new Player[players.length];
        i--;//find the before currentPlayer index
        for (int j = 0; j < tempPlayers.length; j++,i--) {//reverse the players
            if (i<0) {
                i=players.length-1;
            }
            tempPlayers[j]=players[i];
        }
        return tempPlayers;
    }

}