import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    public int computePrice(){
        return 0;
    }
    public int getNrOrders(){ return 0;}
    public String getTitle(){return null;}
    public double getRating(){ return 0;} ;
    public int getCalories(){ return 0;};
    public int getProtein(){ return 0;};
    public int getFat(){ return 0;};
    public int getSodium(){ return 0;};
    public int getPrice(){ return 0;};
    public void orderMade(){return;}


    public MenuItem()  {
    }


}
