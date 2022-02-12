package tuc.tp.tema3.model;

/**
 * maparea tabelei Product din baza de date
 */
public class Product {
    private int idProduct;
    private String name;
    private int quantity;
    private double price;

    public Product(){

    }

    public Product(int idProduct, String name, int quantity, double price){
        this.idProduct = idProduct;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(String name, int quantity, double price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString(){
        return "Product [id = " + idProduct + ", name = " + name + ", quantity = " + quantity + ", price = " + price + "]";
    }
}
