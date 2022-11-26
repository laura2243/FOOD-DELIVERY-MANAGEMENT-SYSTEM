import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class EmployeeGUI implements Observer {
    private JPanel panel1;
    private JTable table1;


    public EmployeeGUI() {

        createOrderTable(Controller.deliveryService.getHashMap());
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JTable getTable1() {
        return table1;
    }

    public void createOrderTable(HashMap<Order, List<MenuItem>> hashMap) {
        int size = 0;
        for (List<MenuItem> list : hashMap.values()) {
            size += list.size();
        }
        String[][] matrix = new String[size][9];


        int i = 0;
        for (Order order : hashMap.keySet()) {


            for (MenuItem menuItems : hashMap.get(order)) {

                if (menuItems instanceof BaseProduct) {
                    matrix[i][0] = String.valueOf(order.getOrderID());
                    matrix[i][1] = ((BaseProduct) menuItems).getTitle();
                    matrix[i][2] = String.valueOf(((BaseProduct) menuItems).getRating());
                    matrix[i][3] = String.valueOf(((BaseProduct) menuItems).getCalories());
                    matrix[i][4] = String.valueOf(((BaseProduct) menuItems).getProtein());
                    matrix[i][5] = String.valueOf(((BaseProduct) menuItems).getFat());
                    matrix[i][6] = String.valueOf(((BaseProduct) menuItems).getSodium());
                    matrix[i][7] = String.valueOf(((BaseProduct) menuItems).getPrice());
                    matrix[i][8] = String.valueOf(order.getOrderDate());
                    i++;
                } else if (menuItems instanceof CompositeProduct) {
                    matrix[i][0] = ((CompositeProduct) menuItems).getTitle();
                    matrix[i][1] = String.valueOf(((CompositeProduct) menuItems).getRating());
                    matrix[i][2] = String.valueOf(((CompositeProduct) menuItems).getCalories());
                    matrix[i][3] = String.valueOf(((CompositeProduct) menuItems).getProtein());
                    matrix[i][4] = String.valueOf(((CompositeProduct) menuItems).getFat());
                    matrix[i][5] = String.valueOf(((CompositeProduct) menuItems).getSodium());
                    matrix[i][6] = String.valueOf(((CompositeProduct) menuItems).getPrice());
                    i++;

                }
            }
        }

        table1.setModel(new

                DefaultTableModel(
                matrix, new String[]{
                "Order nr", "Title", "Rating", "Clories", "Protein", "Fat", "Sodium", "Price", "Date"
        }));
    }

    @Override
    public void update(Observable o, Object arg) {
        HashMap<Order, List<MenuItem>> hashMap = (HashMap<Order, List<MenuItem>>) arg;
        createOrderTable(hashMap);
        JOptionPane.showMessageDialog(null, "An order was made");
    }
}
