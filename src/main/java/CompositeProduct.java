import java.util.List;

public class CompositeProduct extends MenuItem {

    List<BaseProduct> products;
    private int price;

    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int nrOfOrders;



    public void orderMade(){
        nrOfOrders++;

    }
    public CompositeProduct(List<BaseProduct> products, int price, String title, double rating, int calories, int protein, int fat, int sodium) {
        this.products = products;
        this.price = price;
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
    }

    public CompositeProduct() {
    }

    public int getNrOrders() {
        return nrOfOrders;
    }


    public int computePrice() {

        for (BaseProduct baseProduct : products) {
            price += baseProduct.computePrice();
        }
        return price;


    }

    public void setProducts(List<BaseProduct> products) {
        this.products = products;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double computeRating() {
        for (BaseProduct baseProduct : products) {
            this.rating += baseProduct.getRating();
        }
        this.rating /= products.size();
        return this.rating;
    }

    public int computeCalories() {
        for (BaseProduct baseProduct : products) {
            this.calories += baseProduct.getCalories();
        }
        return calories;
    }

    public int computeProteins() {
        for (BaseProduct baseProduct : products) {
            this.protein += baseProduct.getProtein();
        }
        return this.protein;
    }

    public int computeFat() {
        for (BaseProduct baseProduct : products) {
            this.fat += baseProduct.getFat();
        }
        return this.protein;
    }

    public int computeSodium() {
        for (BaseProduct baseProduct : products) {
            this.sodium += baseProduct.getSodium();
        }
        return this.sodium;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "CompositeProduct{" +

                ", price=" + price +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", sodium=" + sodium +
                "} ";
    }

    public List<BaseProduct> getProducts() {
        return products;
    }

    public int getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

}
