package tuc.tp.tema3.model;

/**
 * maparea tabelei Orders din baza de date
 */
public class Orders {
    private int idOrder;
    private int idClient;
    private int idProduct;
    private int quantity;
    private double total;


    public Orders(int idOrder, int id_client, int id_product, int quantity, double total){
        this.idOrder = idOrder;
        this.idClient = id_client;
        this.idProduct = id_product;
        this.quantity = quantity;
        this.total = total;
    }

    public Orders(int id_client, int id_product, int quantity, double total) {
        this.idClient = id_client;
        this.idProduct = id_product;
        this.quantity = quantity;
        this.total = total;
    }

    public Orders(){

    }

    public int getIdOrder() {
        return idOrder;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String toString(){
        return "Order [idOrder = " + idOrder + ", idClient = " + idClient + ", idProduct " + idProduct + ", quantity " + quantity + " total " + total + "]" ;
    }
}
