import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public interface IdeliveryServiceProcessing {

    /**
     * @pre id > 0 && menuItems.size()
     * @post hashMap.size() = hashMap.size()@pre + 1
     */
    HashMap<Order, List<MenuItem>> makeOrder(List<MenuItem> menuItems, int id);

    /**
     * @pre true
     * @post menuItems.size() >0
     */
    List<MenuItem> collect(List<MenuItem> menuItems);


    /**
     * @pre  !Objects.equals(menuItem.getTitle(), "") && menuItem.getRating() >= 0 && menuItem.getCalories() >= 0 && menuItem.getProtein() >= 0 && menuItem.getFat() >= 0 && menuItem.getSodium() >= 0 && menuItem.getPrice() >= 0;
     * @post menuItems.size() = menuItems.size()@pre + 1
     */

    List<MenuItem> addProduct(MenuItem menuItem);

    /**
     * @pre startHour >= 0 && endHour <= 23 && startHour <= endHour
     * @post @nochange
     */
    void generateHourRaport(int startHour, int endHour);
    /**
     * @pre nr > 0
     * @post @nochange
     */
    void generateProductsRaport(int nr);

    /**
     * @pre nr > 0 && value > 0
     * @post @nochange
     */
    void generateordersRaport(int nr, int value);
    /**
     * @pre dateGiven != null
     * @post @nochange
     */
    void generateDayRaport(String dateGiven);





}
