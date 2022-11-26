import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * @invariant isWellFormed()
 */
public class DeliveryService extends Observable implements IdeliveryServiceProcessing {

    private List<MenuItem> menuItems;
    private HashMap<Order, List<MenuItem>> hashMap;
    private static int nrOrder;
    private static int nrClient;
    private LogInCredentials logInCredentials;

    protected boolean isWellFormed() {
        int n = 0;
        for (MenuItem item : menuItems) {
            n++;
            if (Objects.equals(item.getTitle(), ""))
                return false;
            if (item.getRating() < 0)
                return false;
            if (item.getCalories() < 0)
                return false;
            if (item.getProtein() < 0)
                return false;
            if (item.getFat() < 0)
                return false;
            if (item.getSodium() < 0)
                return false;
            if (item.computePrice() < 0)
                return false;
            if (item.getPrice() < 0)
                return false;
        }
        return n == menuItems.size();
    }


    public DeliveryService() {
        menuItems = new ArrayList<>();
        hashMap = new HashMap<>();

        File file = new File("src/main/resources/menu.txt");
        if (file.exists()) {
            try {
                deserializeAll();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            menuItems = collect(menuItems);
        }
    }


    public void makeCompositeProduct(List<BaseProduct> baseProductsComp, int price1, String title1, double rating1, int calories1, int protein1, int fat1, int sodium1) {
        CompositeProduct compositeProduct1 = new CompositeProduct(baseProductsComp, price1, title1, rating1, calories1, protein1, fat1, sodium1);
        addProduct(compositeProduct1);
    }

    public void deleteProduct(int index) {
        menuItems.remove(index);
    }

    public void updateProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price, int index) {
        if (menuItems.get(index) instanceof BaseProduct) {
            ((BaseProduct) menuItems.get(index)).setTitle(title);
            ((BaseProduct) menuItems.get(index)).setRating(rating);
            ((BaseProduct) menuItems.get(index)).setCalories(calories);
            ((BaseProduct) menuItems.get(index)).setPrice(price);
            ((BaseProduct) menuItems.get(index)).setProtein(protein);
            ((BaseProduct) menuItems.get(index)).setSodium(sodium);
            ((BaseProduct) menuItems.get(index)).setFat(fat);
        } else if (menuItems.get(index) instanceof CompositeProduct) {
            ((CompositeProduct) menuItems.get(index)).setTitle(title);
            ((CompositeProduct) menuItems.get(index)).setRating(rating);
            ((CompositeProduct) menuItems.get(index)).setCalories(calories);
            ((CompositeProduct) menuItems.get(index)).setPrice(price);
            ((CompositeProduct) menuItems.get(index)).setProtein(protein);
            ((CompositeProduct) menuItems.get(index)).setSodium(sodium);
            ((CompositeProduct) menuItems.get(index)).setFat(fat);

        }
    }

    public List<MenuItem> search(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        List<MenuItem> listSearch = menuItems;

        if (!title.equals("")) {
            listSearch = listSearch.stream().filter(prod -> prod.getTitle().contains(title)).collect(Collectors.toList());
        }
        if (rating != -1.0) {
            listSearch = listSearch.stream().filter(prod -> prod.getRating() == rating).collect(Collectors.toList());
        }
        if (protein != -1) {
            listSearch = listSearch.stream().filter(prod -> prod.getProtein() == protein).collect(Collectors.toList());
        }
        if (fat != -1) {
            listSearch = listSearch.stream().filter(prod -> prod.getFat() == fat).collect(Collectors.toList());
        }
        if (sodium != -1) {
            listSearch = listSearch.stream().filter(prod -> prod.getSodium() == sodium).collect(Collectors.toList());
        }
        if (price != -1) {
            listSearch = listSearch.stream().filter(prod -> prod.getPrice() == price).collect(Collectors.toList());
        }
        if (calories != -1) {
            listSearch = listSearch.stream().filter(prod -> prod.getCalories() == price).collect(Collectors.toList());
        }
        return listSearch;

    }

    public HashMap<Order, List<MenuItem>> makeOrder(List<MenuItem> menuItems, int id) {
//
//        for(MenuItem menuItem: menuItems){
//
//             menuItem.orderMade();
//        }

        Order order = new Order(hashMap.size(), id);
        assert id != 0 && menuItems.size() > 0;
        int preSize = hashMap.size();
        hashMap.put(order, menuItems);
        int postSize = hashMap.size();
        assert preSize + 1 == postSize;

        bill(id, menuItems, order);
        setChanged();
        notifyObservers(hashMap);
        return hashMap;


    }

    public void bill(int id, List<MenuItem> orders, Order order) {


        String toWrite = "Order number:" + hashMap.size() + "\n";
        toWrite += "Client with ID: " + id + "\n Products: \n";
        int number = 0;
        for (MenuItem menuItem : orders) {
            number++;
            toWrite += number + ". ";
            if (menuItem instanceof BaseProduct) {
                toWrite += ((BaseProduct) menuItem).getTitle() + " price: " + ((BaseProduct) menuItem).getPrice();
            }
            if (menuItem instanceof CompositeProduct) {
                toWrite += ((CompositeProduct) menuItem).getTitle() + " \n ( ";
                for (BaseProduct baseProduct : ((CompositeProduct) menuItem).getProducts()) {
                    toWrite += baseProduct.getTitle() + " ";
                }
                toWrite += ")";

                toWrite += " price: " + ((CompositeProduct) menuItem).getPrice();
            }
            toWrite += "\n";

        }
        int price = computeTotalPrice(order);
        toWrite += "\n Total price:" + price;
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter("order" + hashMap.size() + ".txt");
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            buffer.write(toWrite);
            buffer.close();
        } catch (IOException i) {
            System.out.println(i.getMessage());
        }
    }

    public static void orderInc() {
        nrOrder++;
    }


    public HashMap<Order, List<MenuItem>> getHashMap() {
        return hashMap;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void serializeAll() {
        Serializator<List<MenuItem>> menuItemsSerializator = new Serializator<>();
        Serializator<Map<Order, List<MenuItem>>> placeOrdersSerializator = new Serializator<>();
        Serializator<LogInCredentials> userSerializator = new Serializator<>();

        try {
            menuItemsSerializator.serializeObject("src/main/resources/menu.txt", menuItems);
            placeOrdersSerializator.serializeObject("src/main/resources/orders.txt", hashMap);
            userSerializator.serializeObject("src/main/resources/users.txt", logInCredentials);


        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deserializeAll() throws ClassNotFoundException, IOException {
        Serializator<List<MenuItem>> menuItemSerializator = new Serializator<>();
        Serializator<HashMap<Order, List<MenuItem>>> orderSerializator = new Serializator<>();
        Serializator<LogInCredentials> userSerializator = new Serializator<>();
        menuItems = menuItemSerializator.deserializeObject("src/main/resources/menu.txt");
        hashMap = orderSerializator.deserializeObject("src/main/resources/orders.txt");
        logInCredentials = userSerializator.deserializeObject("src/main/resources/users.txt");
    }


    public List<MenuItem> collect(List<MenuItem> menuItems) {


        try {

            File inputF = new File("src/main/resources/products.csv");
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

            // skip the header of the csv
            menuItems = br.lines().skip(1).map(mapToItem).distinct().collect(Collectors.toList());


            Credentials credentials = new Credentials("ana", "ana", 0);
            Credentials credentialsAdmin = new Credentials("admin", "admin", 0);
            Credentials credentialsEmployee = new Credentials("emp", "emp", 0);
            logInCredentials = new LogInCredentials();
            logInCredentials.addGetLogInCredentialsClient(credentials);
            logInCredentials.addGetLogInCredentialsAdmin(credentialsAdmin);
            logInCredentials.addGetLogInCredentialsEmployee(credentialsEmployee);

            br.close();

        } catch (IOException e) {

        }
        assert menuItems.size() > 0;
        assert isWellFormed();
        return menuItems;
    }


    public void specifiedItem(String title) {

        //   List<MenuItem> menuItems = new ArrayList<>();
        try {

            File inputF = new File("src/main/resources/products.csv");
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

            // skip the header of the csv
            BaseProduct baseProduct = new BaseProduct();
            baseProduct = br.lines().skip(1).filter(s -> s.contains(title)).findFirst().map(mapToItem).orElse(null);

            menuItems.add(baseProduct);
            System.out.println(baseProduct.toString());
            br.close();
        } catch (IOException e) {

        }
    }

    public List<MenuItem> addProduct(MenuItem menuItem) {

        assert !Objects.equals(menuItem.getTitle(), "") && menuItem.getRating() >= 0 && menuItem.getCalories() >= 0 && menuItem.getProtein() >= 0 && menuItem.getFat() >= 0 && menuItem.getSodium() >= 0 && menuItem.getPrice() >= 0;
        int sizePre = menuItems.size();
        menuItems.add(0, menuItem);
        int sizePost = menuItems.size();
        assert sizePost == sizePre + 1;
        assert isWellFormed();

        return menuItems;
    }


    private Function<String, BaseProduct> mapToItem = (line) -> {

        String[] p = line.split(",");

        BaseProduct item = new BaseProduct();

        item.setTitle(p[0]);
        item.setRating(Double.parseDouble(p[1]));
        item.setCalories(Integer.parseInt(p[2]));
        item.setProtein(Integer.parseInt(p[3]));
        item.setFat(Integer.parseInt(p[4]));
        item.setSodium(Integer.parseInt(p[5]));
        item.setPrice(Integer.parseInt(p[6]));


        return item;
    };

    public void generateHourRaport(int startHour, int endHour) {

        assert startHour >= 0 && endHour <= 23 && startHour <= endHour;
        List<Order> orderList = hashMap.keySet().stream().toList();
        orderList = orderList.stream().filter(order -> order.getHour() >= startHour).filter(order -> order.getHour() < endHour).collect(Collectors.toList());


        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter("raport1.txt");
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            buffer.write(orderList.toString());
            buffer.close();
        } catch (IOException i) {
            System.out.println(i.getMessage());
        }
        System.out.println(orderList);


    }

    public void generateProductsRaport(int nr) {

        assert nr > 0;
        List<MenuItem> menuItems = getMenuItems();
        menuItems = menuItems.stream().filter(prod -> prod.getNrOrders() > nr).collect(Collectors.toList());
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter("raport2.txt");
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            buffer.write(menuItems.toString());
            buffer.close();
        } catch (IOException i) {
            System.out.println(i.getMessage());
        }
        System.out.println(menuItems.toString());
    }

    public void generateordersRaport(int nr, int value) {

        assert nr > 0 && value > 0;
        List<Order> orderList = hashMap.keySet().stream().toList();


        int[] generated = orderList.stream()
                .filter(user -> hashMap.keySet().stream()
                        .filter(order -> order.getClientID() == user.getClientID()).distinct()
                        .filter(order -> hashMap.get(order).stream()
                                .flatMapToDouble(menuItem -> DoubleStream.of(menuItem.computePrice()))
                                .sum() >= value)
                        .count() >= nr)
                .collect(Collectors.toList()).stream().flatMapToInt(x -> IntStream.of(x.getClientID())).distinct().toArray();

        String s = "";

        System.out.println(orderList.toString());
        for (int i = 0; i < generated.length; i++) {
            s += "Client cu ID:" + generated[i] + "\n";
        }
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter("raport3.txt");
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            buffer.write(s);
            buffer.close();
        } catch (IOException i) {
            System.out.println(i.getMessage());
        }


    }

    public void generateDayRaport(String dateGiven) {

        assert dateGiven != null;
        List<Order> orderList = hashMap.keySet().stream().toList();
        List<Order> orders = orderList.stream().filter(x -> x.getOrderDateTrunc().equals(dateGiven)).toList();
        List<MenuItem> list = orders.stream().map(x -> hashMap.get(x)).flatMap(List::stream).collect(Collectors.toList());
        List<Integer> nrOrders = list.stream().map(x -> x.getNrOrders()).toList();
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            s += list.get(i) + "number of times ordered: " + nrOrders.get(i) + " \n";
        }
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter("raport4.txt");
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            buffer.write(s);
            buffer.close();
        } catch (IOException i) {
            System.out.println(i.getMessage());
        }

        // System.out.println(list);

    }


    public void setHashMap(HashMap<Order, List<MenuItem>> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public String toString() {
        return "DeliveryService: " +

                " hashMap contine: \n" + hashMap;
    }

    public LogInCredentials getLogInCredentials() {
        return logInCredentials;
    }

    public int computeTotalPrice(Order order) {
        int price = 0;
        for (MenuItem menuItem : hashMap.get(order)) {

            if (menuItem instanceof BaseProduct) {
                price += ((BaseProduct) menuItem).getPrice();
            }
            if (menuItem instanceof CompositeProduct) {
                price += ((CompositeProduct) menuItem).getPrice();
            }

        }
        return price;
    }
}
