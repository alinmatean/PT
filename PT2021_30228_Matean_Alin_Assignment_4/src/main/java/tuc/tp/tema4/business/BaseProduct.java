package tuc.tp.tema4.business;

/**
 * BaseProduct
 */
public class BaseProduct extends MenuItem{
    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    public BaseProduct(String title, Double rating, Integer calories, Integer proteins, Integer fat, Integer sodium, Integer price) {
        super(title);
        this.rating = rating;
        this.calories = calories;
        this.protein = proteins;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public int computePrice(){
        return price;
    }

    @Override
    public double computeRating() {
        return rating;
    }

    @Override
    public int computeCalories() {
        return calories;
    }

    @Override
    public int computeProteins() {
        return protein;
    }

    @Override
    public int computeFat() {
        return fat;
    }

    @Override
    public int computeSodium() {
        return sodium;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setPrice(int price){this.price = price;}

}
