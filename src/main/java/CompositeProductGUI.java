import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class CompositeProductGUI {
    private JPanel panel1;
    private JTable table1;
    private JTextField titleField;
    private JButton MAKECOMPOSITEPRODUCTButton;
    private JTable tableComposite;

    public CompositeProductGUI() {
        DeliveryService deliveryService = new DeliveryService();
        createMenuTable(deliveryService);
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


            }
            else  if (menuItems.get(i) instanceof CompositeProduct) {
                matrix[i][0] = ((CompositeProduct) menuItems.get(i)).getTitle();
                matrix[i][1] = String.valueOf(((CompositeProduct)menuItems.get(i)).getRating());
                matrix[i][2] = String.valueOf(((CompositeProduct) menuItems.get(i)).getCalories());
                matrix[i][3] = String.valueOf(((CompositeProduct) menuItems.get(i)).getProtein());
                matrix[i][4] = String.valueOf(((CompositeProduct) menuItems.get(i)).getFat());
                matrix[i][5] = String.valueOf(((CompositeProduct) menuItems.get(i)).getSodium());
                matrix[i][6] = String.valueOf(((CompositeProduct) menuItems.get(i)).getPrice());


            }
        }
        table1.setModel(new DefaultTableModel(
                matrix, new String[]{"Title", "Rating", "Clories","Protein","Fat","Sodium","Price"}));
    }


    public void createCompositeTable(List<BaseProduct> menuItems) {



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


            }

        }

        tableComposite.setModel(new DefaultTableModel(
                matrix, new String[]{"Title", "Rating", "Clories","Protein","Fat","Sodium","Price"}));
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JTable getTable1() {
        return table1;
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JButton getMAKECOMPOSITEPRODUCTButton() {
        return MAKECOMPOSITEPRODUCTButton;
    }

    public JTable getTableComposite() {
        return tableComposite;
    }
}
