/** 
 * this Cards has a color and Action to do
 * ActionCard
 */
public abstract class ActionCard extends Card {
    
    public ActionCard(int color)
    {
        super(color);
    }
    
    public abstract Player[] action(Player[] players,Player currentPlayer,Board gamebBoard);
}