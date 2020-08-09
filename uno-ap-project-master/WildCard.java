/**
 * WildCard has two kind : Color Changer and WildDraw
 */
public class WildCard extends ActionCard {

    private int changedColor;

    /**
     * Counstructor
     */
    public WildCard() {
        super(0);// its black by default
    }

    /**
     * @param changedColor the changedColor to set
     */
    public void setChangedColor(int changedColor) {
        this.changedColor = changedColor;
    }

    /**
     * @return the changedColor
     */
    public int getChangedColor() {
        return changedColor;
    }

    public Player[] action(Player[] players, Player currentPlayer, Board gamebBoard) {
        return null;
    }
}