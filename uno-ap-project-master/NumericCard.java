/**
 * this cards has color and number
 * NumericCard
 */
public class NumericCard extends Card{

    private int number;// the number of the numericCard
    public NumericCard (int color,int number)
    {
        super(color);
        this.number=number;
    }
    
    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    public void visiblePrint(int x, int y,String id,int number) {
        String iid="->0"+this.number+"<-";
        super.visiblePrint(x, y,iid,number);
    }

}