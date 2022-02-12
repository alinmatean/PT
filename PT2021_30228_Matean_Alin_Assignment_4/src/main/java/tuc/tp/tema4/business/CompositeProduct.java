package tuc.tp.tema4.business;

import java.util.ArrayList;

/**
 * CompositeProduct
 */
public class CompositeProduct extends MenuItem{

    private ArrayList<MenuItem> items = new ArrayList<MenuItem>();
    private String name;

    public CompositeProduct(String title){
        super(title);
        this.items = new ArrayList<MenuItem>();
    }

    public int computePrice(){
        int price = 0;
        for (MenuItem item: items){
            price += item.computePrice();
        }
        return price;
    }

    @Override
    public double computeRating() {
        double rating = 0.0;
        for (MenuItem item: items){
            rating += item.computeRating();
        }
        return rating;
    }

    @Override
    public int computeCalories() {
        int calories = 0;
        for (MenuItem item: items){
            calories += item.computeCalories();
        }
        return calories;
    }

    @Override
    public int computeProteins() {
        int proteins = 0;
        for (MenuItem item: items){
            proteins += item.computeProteins();
        }
        return proteins;
    }

    @Override
    public int computeFat() {
        int fat = 0;
        for (MenuItem item: items){
            fat += item.computeFat();
        }
        return fat;
    }

    @Override
    public int computeSodium() {
        int sodium = 0;
        for (MenuItem item: items){
            sodium += item.computeSodium();
        }
        return sodium;
    }

    public void setItems(ArrayList<MenuItem> items){
        this.items = items;
    }

    public void addItem(MenuItem product){
        items.add(product);
    }
    public void removeItem(MenuItem product){
        for(MenuItem menuItem: items){
            if(menuItem.getTitle().equals(product.getTitle())) {
                items.remove(product);
            }
        }
    }
    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    public String toString(){
        String result = new String();
        result += super.toString();
        for(MenuItem item: items)
            result += item.toString() + " ";
        return result;
    }

}
