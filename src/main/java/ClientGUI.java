import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ClientGUI {
    private JPanel panel1;
    private JTable table1;
    private JTable table2;
    private JButton MAKEORDERButton;
    private JTextField priceField;
    private JTextField sodiumField;
    private JTextField proteinField;
    private JTextField caloriesField;
    private JLabel RatingTextBox;
    private JTextField ratingField;
    private JTextField titleField;
    private JTextField fatField;
    private JButton SearchButton;
    private JTextField TotaltextField1;

    public ClientGUI() {
    }


    public JPanel getPanel1() {
        return panel1;
    }

    public JTable getTable1() {
        return table1;
    }

    public JTable getTable2() {
        return table2;
    }

    public JButton getMAKEORDERButton() {
        return MAKEORDERButton;
    }

    public void createMenuTable(DeliveryService deliveryService) {

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems = deliveryService.getMenuItems();
        String[][] matrix = new String[menuItems.size()][7];

        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i) instanceof BaseProduct) {
                matrix[i][0] = ((BaseProduct) menuItems.get(i)).getTitle();
                matrix[i][1] = String.valueOf(((BaseProduct) menuItems.get(i)).getRating());
                matrix[i][2] = String.valueOf(((BaseProduct) menuItems.get(i)).getCalories());
                matrix[i][3] = String.valueOf(((BaseProduct) menuItems.get(i)).getProtein());
                matrix[i][4] = String.valueOf(((BaseProduct) menuItems.get(i)).getFat());
                matrix[i][5] = String.valueOf(((BaseProduct) menuItems.get(i)).getSodium());
                matrix[i][6] = String.valueOf(((BaseProduct) menuItems.get(i)).getPrice());


            } else if (menuItems.get(i) instanceof CompositeProduct) {
                matrix[i][0] = ((CompositeProduct) menuItems.get(i)).getTitle();
                matrix[i][1] = String.valueOf(((CompositeProduct) menuItems.get(i)).getRating());
                matrix[i][2] = String.valueOf(((CompositeProduct) menuItems.get(i)).getCalories());
                matrix[i][3] = String.valueOf(((CompositeProduct) menuItems.get(i)).getProtein());
                matrix[i][4] = String.valueOf(((CompositeProduct) menuItems.get(i)).getFat());
                matrix[i][5] = String.valueOf(((CompositeProduct) menuItems.get(i)).getSodium());
                matrix[i][6] = String.valueOf(((CompositeProduct) menuItems.get(i)).getPrice());


            }
        }
        table1.setModel(new DefaultTableModel(
                matrix, new String[]{"Title", "Rating", "Clories", "Protein", "Fat", "Sodium", "Price"}));
    }

    public void createMenuTableSearch(List<MenuItem> menuItems) {
        String[][] matrix = new String[menuItems.size()][7];

        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i) instanceof BaseProduct) {
                matrix[i][0] = ((BaseProduct) menuItems.get(i)).getTitle();
                matrix[i][1] = String.valueOf(((BaseProduct) menuItems.get(i)).getRating());
                matrix[i][2] = String.valueOf(((BaseProduct) menuItems.get(i)).getCalories());
                matrix[i][3] = String.valueOf(((BaseProduct) menuItems.get(i)).getProtein());
                matrix[i][4] = String.valueOf(((BaseProduct) menuItems.get(i)).getFat());
                matrix[i][5] = String.valueOf(((BaseProduct) menuItems.get(i)).getSodium());
                matrix[i][6] = String.valueOf(((BaseProduct) menuItems.get(i)).getPrice());


            } else if (menuItems.get(i) instanceof CompositeProduct) {
                matrix[i][0] = ((CompositeProduct) menuItems.get(i)).getTitle();
                matrix[i][1] = String.valueOf(((CompositeProduct) menuItems.get(i)).getRating());
                matrix[i][2] = String.valueOf(((CompositeProduct) menuItems.get(i)).getCalories());
                matrix[i][3] = String.valueOf(((CompositeProduct) menuItems.get(i)).getProtein());
                matrix[i][4] = String.valueOf(((CompositeProduct) menuItems.get(i)).getFat());
                matrix[i][5] = String.valueOf(((CompositeProduct) menuItems.get(i)).getSodium());
                matrix[i][6] = String.valueOf(((CompositeProduct) menuItems.get(i)).getPrice());


            }
        }
        table1.setModel(new DefaultTableModel(
                matrix, new String[]{"Title", "Rating", "Clories", "Protein", "Fat", "Sodium", "Price"}));
        TotaltextField1.setText("");
    }


    public void createOrderTable(List<MenuItem> menuItems) {


        String[][] matrix = new String[menuItems.size()][7];

        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i) instanceof BaseProduct) {
                matrix[i][0] = ((BaseProduct) menuItems.get(i)).getTitle();
                matrix[i][1] = String.valueOf(((BaseProduct) menuItems.get(i)).getRating());
                matrix[i][2] = String.valueOf(((BaseProduct) menuItems.get(i)).getCalories());
                matrix[i][3] = String.valueOf(((BaseProduct) menuItems.get(i)).getProtein());
                matrix[i][4] = String.valueOf(((BaseProduct) menuItems.get(i)).getFat());
                matrix[i][5] = String.valueOf(((BaseProduct) menuItems.get(i)).getSodium());
                matrix[i][6] = String.valueOf(((BaseProduct) menuItems.get(i)).getPrice());


            } else if (menuItems.get(i) instanceof CompositeProduct) {
                matrix[i][0] = ((CompositeProduct) menuItems.get(i)).getTitle();
                matrix[i][1] = String.valueOf(((CompositeProduct) menuItems.get(i)).getRating());
                matrix[i][2] = String.valueOf(((CompositeProduct) menuItems.get(i)).getCalories());
                matrix[i][3] = String.valueOf(((CompositeProduct) menuItems.get(i)).getProtein());
                matrix[i][4] = String.valueOf(((CompositeProduct) menuItems.get(i)).getFat());
                matrix[i][5] = String.valueOf(((CompositeProduct) menuItems.get(i)).getSodium());
                matrix[i][6] = String.valueOf(((CompositeProduct) menuItems.get(i)).getPrice());


            }
        }
        table2.setModel(new DefaultTableModel(
                matrix, new String[]{"Title", "Rating", "Clories", "Protein", "Fat", "Sodium", "Price"}));
        int total = 0;
        for (MenuItem menuItem : menuItems) {
            total += menuItem.computePrice();
        }
        TotaltextField1.setText(String.valueOf(total));
    }

    public void setTable2(JTable table2) {
        this.table2 = table2;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public JTextField getSodiumField() {
        return sodiumField;
    }

    public JTextField getProteinField() {
        return proteinField;
    }

    public JTextField getCaloriesField() {
        return caloriesField;
    }

    public JTextField getRatingField() {
        return ratingField;
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JTextField getFatField() {
        return fatField;
    }

    public JButton getSearchButton() {
        return SearchButton;
    }

}
