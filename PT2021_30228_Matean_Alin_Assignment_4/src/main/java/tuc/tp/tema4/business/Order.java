package tuc.tp.tema4.business;

import java.io.Serializable;
import java.util.Date;

/**
 * Order
 */
public class Order implements Serializable{
    private Integer orderID;
    private Integer clientID;
    private Date orderDate;
    private Integer price;

    public Order(Integer orderID, Integer clientID, Date orderDate, Integer price) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderDate = orderDate;
        this.price = price;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderId(int id){
        this.orderID = id;
    }

    public Integer getClientID() {
        return clientID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public int hashCode(){
        int result = 7;
        result = 31 * result + orderID.hashCode();
        result = 31 * result + clientID.hashCode();
        result = 31 * result + orderDate.hashCode();
        result = 31 * result + price.hashCode();
        return  result;
    }

}

