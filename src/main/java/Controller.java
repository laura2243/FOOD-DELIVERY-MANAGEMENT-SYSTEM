import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Controller {


    public static DeliveryService deliveryService;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createGUI());
    }


    public static void createGUI() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    UIManager.put("nimbusBlueGrey", new Color(115, 164, 209));
                    break;
                }
            }
        } catch (Exception e) {
        }
        deliveryService = new DeliveryService();
        Login login = new Login();
        JPanel root = login.getMainRootPanel();
        JFrame frame = new JFrame();
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        List<MenuItem> menuItems = new ArrayList<>();
        List<BaseProduct> baseProducts = new ArrayList<>();
        List<BaseProduct> baseProductsComp = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();
        final int[] okUser = {0};
        final int[] okSign = {1};

        login.getCreateNewAccountButton().addActionListener(e -> {

            if (login.getTextField1().getText().equals("") || login.getPasswordField1().getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Empty fields!");
                okSign[0] = 0;
            } else {
                for (Credentials credentials1 : deliveryService.getLogInCredentials().getLogInCredentialsClient()) {
                    if (login.getTextField1().getText().equals(credentials1.getUsername()) && login.getPasswordField1().getText().equals(credentials1.getPassword())) {
                        JOptionPane.showMessageDialog(null, "Existent user!");
                        okSign[0] = 0;
                    }
                }
                for (Credentials credentials2 : deliveryService.getLogInCredentials().getLogInCredentialsAdmin()) {
                    if (login.getTextField1().getText().equals(credentials2.getUsername()) && login.getPasswordField1().getText().equals(credentials2.getPassword())) {
                        JOptionPane.showMessageDialog(null, "Existent user!");
                        okSign[0] = 0;
                    }
                }
                for (Credentials credentials3 : deliveryService.getLogInCredentials().getLogInCredentialsEmployee()) {
                    if (login.getTextField1().getText().equals(credentials3.getUsername()) && login.getPasswordField1().getText().equals(credentials3.getPassword())) {
                        JOptionPane.showMessageDialog(null, "Existent user!");
                        okSign[0] = 0;
                    }
                }

            }
            if (okSign[0] == 1) {

                Credentials credentials = new Credentials(login.getTextField1().getText(), login.getPasswordField1().getText(), deliveryService.getLogInCredentials().getLogInCredentialsClient().size());
                deliveryService.getLogInCredentials().getLogInCredentialsClient().add(credentials);
                System.out.println(deliveryService.getLogInCredentials().getLogInCredentialsClient().toString());
                deliveryService.serializeAll();
            }
        });

        login.getLoginButton().

                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (login.getTextField1().getText().equals("") || login.getPasswordField1().getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Empty fields!");
                        } else {
                            for (Credentials credentials1 : deliveryService.getLogInCredentials().getLogInCredentialsClient()) {
                                if (login.getTextField1().getText().equals(credentials1.getUsername()) && login.getPasswordField1().getText().equals(credentials1.getPassword())) {

                                    okUser[0] = 1;
                                    int id = credentials1.getId();
                                    ClientGUI clientGUI = Controller.createGUIClient();
                                    clientGUI.createMenuTable(deliveryService);


                                    clientGUI.getSearchButton().addActionListener(e1 -> {


                                        String title = "";
                                        double rating = -1;
                                        int price = -1;
                                        int fat = -1;
                                        int sodium = -1;
                                        int protein = -1;
                                        int calories = -1;

                                        if (!clientGUI.getTitleField().getText().equals("")) {
                                            title = clientGUI.getTitleField().getText();
                                        }

                                        if (!clientGUI.getRatingField().getText().equals("")) {
                                            rating = Double.parseDouble(clientGUI.getRatingField().getText());

                                        }
                                        if (!clientGUI.getCaloriesField().getText().equals("")) {
                                            calories = Integer.parseInt(clientGUI.getCaloriesField().getText());

                                        }

                                        if (!clientGUI.getProteinField().getText().equals("")) {
                                            protein = Integer.parseInt(clientGUI.getProteinField().getText());

                                        }

                                        if (!clientGUI.getFatField().getText().equals("")) {
                                            fat = Integer.parseInt(clientGUI.getFatField().getText());

                                        }
                                        if (!clientGUI.getSodiumField().getText().equals("")) {
                                            sodium = Integer.parseInt(clientGUI.getSodiumField().getText());

                                        }
                                        if (!clientGUI.getPriceField().getText().equals("")) {
                                            price = Integer.parseInt(clientGUI.getPriceField().getText());

                                        }
                                        List<MenuItem> listSearch = deliveryService.search(title, rating, calories, protein, fat, sodium, price);

                                        clientGUI.createMenuTableSearch(listSearch);


                                    });

                                    clientGUI.getMAKEORDERButton().addActionListener(e12 -> {

                                        List<MenuItem> orders = new ArrayList<>();
                                        try {
                                            deliveryService.deserializeAll();
                                        } catch (ClassNotFoundException ex) {
                                            ex.printStackTrace();
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }


                                        if (menuItems.size() > 0) {
                                            System.out.println(deliveryService.makeOrder(menuItems, id).toString());
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Order cannot be made! Choose an item!");
                                        }


                                        deliveryService.serializeAll();
                                    });


                                    clientGUI.getTable1().addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            super.mouseClicked(e);


                                            String title = clientGUI.getTable1().getValueAt(clientGUI.getTable1().getSelectedRow(), 0).toString();
                                            double rating = Double.parseDouble(String.valueOf(clientGUI.getTable1().getValueAt(clientGUI.getTable1().getSelectedRow(), 1)));
                                            int calories = Integer.parseInt(String.valueOf(clientGUI.getTable1().getValueAt(clientGUI.getTable1().getSelectedRow(), 2)));
                                            int protein = Integer.parseInt(String.valueOf(clientGUI.getTable1().getValueAt(clientGUI.getTable1().getSelectedRow(), 3)));
                                            int fat = Integer.parseInt(String.valueOf(clientGUI.getTable1().getValueAt(clientGUI.getTable1().getSelectedRow(), 4)));
                                            int sodium = Integer.parseInt(String.valueOf(clientGUI.getTable1().getValueAt(clientGUI.getTable1().getSelectedRow(), 5)));
                                            int price = Integer.parseInt(String.valueOf(clientGUI.getTable1().getValueAt(clientGUI.getTable1().getSelectedRow(), 6)));
                                            BaseProduct baseProduct = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
                                            menuItems.add(baseProduct);
                                            deliveryService.getMenuItems().get(clientGUI.getTable1().getSelectedRow()).orderMade();
                                            indexes.add(clientGUI.getTable1().getSelectedRow());
                                            clientGUI.createOrderTable(menuItems);
                                            deliveryService.serializeAll();

                                        }

                                    });
                                    menuItems.clear();

                                }
                            }
                        }
                        if (login.getTextField1().getText().equals("") || login.getPasswordField1().getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Empty fields!");
                        } else {
                            for (Credentials credentials1 : deliveryService.getLogInCredentials().getLogInCredentialsAdmin()) {
                                if (login.getTextField1().getText().equals(credentials1.getUsername()) && login.getPasswordField1().getText().equals(credentials1.getPassword())) {
                                    okUser[0] = 1;
                                    AdminGUI adminGUI = Controller.createGUIAdmin();
                                    adminGUI.createMenuTable(deliveryService);


                                    adminGUI.getGENERATEREPORTSButton().addActionListener(e13 -> {

                                        RaportGUI raportGUI = Controller.createGUIRaport();

                                        raportGUI.getComputehourButton().addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e13) {

                                                if (!raportGUI.getStartHourTextField().getText().equals("") && !raportGUI.getEndHourTextField().getText().equals("")) {
                                                    deliveryService.generateHourRaport(Integer.parseInt(raportGUI.getStartHourTextField().getText()), Integer.parseInt(raportGUI.getEndHourTextField().getText()));
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Empty fields!");
                                                }
                                            }
                                        });

                                        raportGUI.getComputeProductsbutton().addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e13) {

                                                if (!raportGUI.getNumberOfProductsTextField().getText().equals("")) {
                                                    deliveryService.generateProductsRaport(Integer.parseInt(raportGUI.getNumberOfProductsTextField().getText()));
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Empty fields!");
                                                }
                                            }
                                        });

                                        raportGUI.getComputeValueButton().addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e13) {

                                                if (!raportGUI.getNbOfOrdersTextField().getText().equals("") && !raportGUI.getValueTextField().getText().equals("")) {
                                                    deliveryService.generateordersRaport(Integer.parseInt(raportGUI.getNbOfOrdersTextField().getText()), Integer.parseInt(raportGUI.getValueTextField().getText()));
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Empty fields!");
                                                }
                                            }
                                        });

                                        raportGUI.getComputeDayButton().addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e13) {

                                                if (!raportGUI.getDayTextField6().getText().equals("")) {
                                                    deliveryService.generateDayRaport(raportGUI.getDayTextField6().getText());
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Empty fields!");
                                                }
                                            }
                                        });
                                    });


                                    adminGUI.getADDButton().addActionListener(e14 -> {

                                        if (adminGUI.getTitleField().getText().equals("") || adminGUI.getRatingField().getText().equals("") || adminGUI.getCaloriesField().getText().equals("") || adminGUI.getProteinField().getText().equals("") || adminGUI.getFatField().getText().equals("") || adminGUI.getSodiumField().getText().equals("") || adminGUI.getSodiumField().getText().equals("") || adminGUI.getPriceField().getText().equals("")) {

                                            JOptionPane.showMessageDialog(null, "Empty fields!");

                                        } else if (Double.parseDouble(adminGUI.getRatingField().getText()) < 0 ||
                                                Integer.parseInt(adminGUI.getCaloriesField().getText()) < 0 ||
                                                Integer.parseInt(adminGUI.getProteinField().getText()) < 0 ||
                                                Integer.parseInt(adminGUI.getFatField().getText()) < 0 ||
                                                Integer.parseInt(adminGUI.getSodiumField().getText()) < 0 ||
                                                Integer.parseInt(adminGUI.getPriceField().getText()) < 0) {
                                            JOptionPane.showMessageDialog(null, "Incorrect data!");

                                        } else {
                                            String title = adminGUI.getTitleField().getText();
                                            double rating = Double.parseDouble(adminGUI.getRatingField().getText());
                                            int calories = Integer.parseInt(adminGUI.getCaloriesField().getText());
                                            int protein = Integer.parseInt(adminGUI.getProteinField().getText());
                                            int fat = Integer.parseInt(adminGUI.getFatField().getText());
                                            int sodium = Integer.parseInt(adminGUI.getSodiumField().getText());
                                            int price = Integer.parseInt(adminGUI.getPriceField().getText());

                                            BaseProduct baseProduct = new BaseProduct(title, rating, calories, protein, fat, sodium, price);


                                            deliveryService.addProduct(baseProduct);
                                            adminGUI.createMenuTable(deliveryService);
                                            deliveryService.serializeAll();
                                        }
                                    });

                                    adminGUI.getButtonUpdate().addActionListener(e15 -> {

                                        if (Double.parseDouble(adminGUI.getRatingField().getText()) < 0 ||
                                                Integer.parseInt(adminGUI.getCaloriesField().getText()) < 0 ||
                                                Integer.parseInt(adminGUI.getProteinField().getText()) < 0 ||
                                                Integer.parseInt(adminGUI.getFatField().getText()) < 0 ||
                                                Integer.parseInt(adminGUI.getSodiumField().getText()) < 0 ||
                                                Integer.parseInt(adminGUI.getPriceField().getText()) < 0) {
                                            JOptionPane.showMessageDialog(null, "Incorrect data!");
                                        } else {
                                            String title = adminGUI.getTitleField().getText();
                                            double rating = Double.parseDouble(adminGUI.getRatingField().getText());
                                            int calories = Integer.parseInt(adminGUI.getCaloriesField().getText());
                                            int protein = Integer.parseInt(adminGUI.getProteinField().getText());
                                            int fat = Integer.parseInt(adminGUI.getFatField().getText());
                                            int sodium = Integer.parseInt(adminGUI.getSodiumField().getText());
                                            int price = Integer.parseInt(adminGUI.getPriceField().getText());
                                            int index = adminGUI.getTable1().getSelectedRow();
                                            deliveryService.updateProduct(title, rating, calories, protein, fat, sodium, price, index);

                                            adminGUI.createMenuTable(deliveryService);
                                            deliveryService.serializeAll();
                                        }
                                    });

                                    adminGUI.getDELETEButton().addActionListener(e16 -> {

                                        int index = adminGUI.getTable1().getSelectedRow();
                                        deliveryService.deleteProduct(index);
                                        adminGUI.createMenuTable(deliveryService);
                                        deliveryService.serializeAll();
                                    });


                                    adminGUI.getMAKECOMPOSITEPRODUCTButton().addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {

                                            baseProductsComp.clear();
                                            CompositeProductGUI compositeProductGUI = Controller.createGUIComposite();
                                            compositeProductGUI.getTable1().addMouseListener(new MouseAdapter() {
                                                @Override
                                                public void mouseClicked(MouseEvent e) {
                                                    super.mouseClicked(e);

                                                    String title = compositeProductGUI.getTable1().getValueAt(compositeProductGUI.getTable1().getSelectedRow(), 0).toString();
                                                    double rating = Double.parseDouble(String.valueOf(compositeProductGUI.getTable1().getValueAt(compositeProductGUI.getTable1().getSelectedRow(), 1)));
                                                    int calories = Integer.parseInt(String.valueOf(compositeProductGUI.getTable1().getValueAt(compositeProductGUI.getTable1().getSelectedRow(), 2)));
                                                    int protein = Integer.parseInt(String.valueOf(compositeProductGUI.getTable1().getValueAt(compositeProductGUI.getTable1().getSelectedRow(), 3)));
                                                    int fat = Integer.parseInt(String.valueOf(compositeProductGUI.getTable1().getValueAt(compositeProductGUI.getTable1().getSelectedRow(), 4)));
                                                    int sodium = Integer.parseInt(String.valueOf(compositeProductGUI.getTable1().getValueAt(compositeProductGUI.getTable1().getSelectedRow(), 5)));
                                                    int price = Integer.parseInt(String.valueOf(compositeProductGUI.getTable1().getValueAt(compositeProductGUI.getTable1().getSelectedRow(), 6)));
                                                    BaseProduct baseProduct = new BaseProduct(title, rating, calories, protein, fat, sodium, price);

                                                    baseProductsComp.add(baseProduct);
                                                    compositeProductGUI.createCompositeTable(baseProductsComp);
                                                    deliveryService.serializeAll();
                                                }

                                            });

                                            compositeProductGUI.getMAKECOMPOSITEPRODUCTButton().addActionListener(e17 -> {

                                                if (baseProductsComp.size() == 0) {

                                                    JOptionPane.showMessageDialog(null, "Empty list! Choose an item!");
                                                } else {
                                                    CompositeProduct compositeProduct = new CompositeProduct();
                                                    compositeProduct.setProducts(baseProductsComp);
                                                    int protein1 = compositeProduct.computeProteins();
                                                    int fat1 = compositeProduct.computeFat();
                                                    int calories1 = compositeProduct.computeCalories();
                                                    int price1 = compositeProduct.computePrice();
                                                    double rating1 = compositeProduct.computeRating();
                                                    int sodium1 = compositeProduct.computeSodium();
                                                    if (compositeProductGUI.getTitleField().getText().equals("") && baseProductsComp.size() > 0) {
                                                        JOptionPane.showMessageDialog(null, " Choose a title!");
                                                    } else {
                                                        compositeProduct.setTitle(compositeProductGUI.getTitleField().getText());

                                                        String title1 = compositeProduct.getTitle();
                                                        deliveryService.makeCompositeProduct(baseProductsComp, price1, title1, rating1, calories1, protein1, fat1, sodium1);
//                                                            CompositeProduct compositeProduct1 = new CompositeProduct(baseProductsComp, price1, title1, rating1, calories1, protein1, fat1, sodium1);
//                                                            deliveryService.addProduct(compositeProduct1);
                                                        compositeProductGUI.createMenuTable(deliveryService);
                                                        adminGUI.createMenuTable(deliveryService);
                                                        deliveryService.serializeAll();
                                                        baseProductsComp.clear();
                                                    }

                                                    baseProductsComp.clear();
                                                }
                                            });
                                            deliveryService.serializeAll();
                                        }
                                    });
                                    adminGUI.getTable1().addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            super.mouseClicked(e);


                                            String title = adminGUI.getTable1().getValueAt(adminGUI.getTable1().getSelectedRow(), 0).toString();
                                            double rating = Double.parseDouble(String.valueOf(adminGUI.getTable1().getValueAt(adminGUI.getTable1().getSelectedRow(), 1)));
                                            int calories = Integer.parseInt(String.valueOf(adminGUI.getTable1().getValueAt(adminGUI.getTable1().getSelectedRow(), 2)));
                                            int protein = Integer.parseInt(String.valueOf(adminGUI.getTable1().getValueAt(adminGUI.getTable1().getSelectedRow(), 3)));
                                            int fat = Integer.parseInt(String.valueOf(adminGUI.getTable1().getValueAt(adminGUI.getTable1().getSelectedRow(), 4)));
                                            int sodium = Integer.parseInt(String.valueOf(adminGUI.getTable1().getValueAt(adminGUI.getTable1().getSelectedRow(), 5)));
                                            int price = Integer.parseInt(String.valueOf(adminGUI.getTable1().getValueAt(adminGUI.getTable1().getSelectedRow(), 6)));

                                            adminGUI.getCaloriesField().setText(String.valueOf(calories));
                                            adminGUI.getProteinField().setText(String.valueOf(protein));
                                            adminGUI.getTitleField().setText(title);
                                            adminGUI.getRatingField().setText(String.valueOf(rating));
                                            adminGUI.getFatField().setText(String.valueOf(fat));
                                            adminGUI.getSodiumField().setText(String.valueOf(sodium));
                                            adminGUI.getPriceField().setText(String.valueOf(price));

                                            BaseProduct baseProduct = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
                                            baseProducts.add(baseProduct);

                                        }
                                    });
                                    deliveryService.serializeAll();
                                }
                            }
                        }
                        if (login.getTextField1().getText().equals("") || login.getPasswordField1().getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Empty fields!");
                        } else {
                            for (Credentials credentials1 : deliveryService.getLogInCredentials().getLogInCredentialsEmployee()) {
                                if (login.getTextField1().getText().equals(credentials1.getUsername()) && login.getPasswordField1().getText().equals(credentials1.getPassword())) {

                                    okUser[0] = 1;
                                    try {
                                        deliveryService.deserializeAll();
                                    } catch (ClassNotFoundException | IOException ex) {
                                        ex.printStackTrace();
                                    }
                                    EmployeeGUI employeeGUI = Controller.createGUIEmployee();
                                    deliveryService.addObserver(employeeGUI);
                                    //employeeGUI.createOrderTable(deliveryService.getHashMap());
                                    deliveryService.serializeAll();

                                }
                                deliveryService.serializeAll();
                            }
                            deliveryService.serializeAll();
                        }
                        if (okUser[0] == 0) {
                            JOptionPane.showMessageDialog(null, "User doesn't exist!");
                        }
                    }
                });
    }

    public static ClientGUI createGUIClient() {


        ClientGUI client = new ClientGUI();
        JPanel root = client.getPanel1();
        JFrame frame = new JFrame();
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        return client;
    }

    public static AdminGUI createGUIAdmin() {


        AdminGUI adminGUI = new AdminGUI();
        JPanel root = adminGUI.getPanel1();
        JFrame frame = new JFrame();
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        return adminGUI;
    }

    public static CompositeProductGUI createGUIComposite() {


        CompositeProductGUI compositeProductGUI = new CompositeProductGUI();
        JPanel root = compositeProductGUI.getPanel1();
        JFrame frame = new JFrame();
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        return compositeProductGUI;
    }

    public static EmployeeGUI createGUIEmployee() {


        EmployeeGUI employeeGUI = new EmployeeGUI();
        JPanel root = employeeGUI.getPanel1();
        JFrame frame = new JFrame();
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        return employeeGUI;
    }

    public static RaportGUI createGUIRaport() {


        RaportGUI raportGUI = new RaportGUI();
        JPanel root = raportGUI.getPanel1();
        JFrame frame = new JFrame();
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        return raportGUI;

    }


}
