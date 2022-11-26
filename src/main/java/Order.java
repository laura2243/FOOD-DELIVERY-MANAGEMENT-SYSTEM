import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Order implements Serializable {

    private int orderID = 0;
    private int clientID = 0;
    private Date orderDate;
    private int totalPrice = 0;


    public Order(int orderID, int clientID) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderDate = new Date();

    }

    public Order() {

    }

    public int hashCode() {
        return orderID + clientID + orderDate.hashCode();
    }

    @Override
    public String toString() {


        return "Order cu " +
                "orderID=" + orderID +
                ", clientID=" + clientID +
                " orderDate=" + orderDate + '\n';
    }

    public int getOrderID() {
        return orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public int getHour() {
        return orderDate.getHours();
    }

    public String getOrderDateTrunc() {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String data = df.format(orderDate);
        return data;
    }

    public Date getOrderDate() {
        return orderDate;
    }
}
