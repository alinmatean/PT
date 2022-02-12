package tuc.tp.tema3.bll;

import tuc.tp.tema3.dao.OrderDAO;
import tuc.tp.tema3.model.Orders;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Business logic class
 * incapsuleaza operatiile care se fac asupra comenzilor
 */
public class OrderBLL {
    private OrderDAO orderDAO;

    public OrderBLL(){
        orderDAO = new OrderDAO();
    }

    /**
     * apeleaza metoda care insereaza o comanda noua
     * @param orders
     * @throws IllegalAccessException
     */
    public void insertOrder(Orders orders) throws IllegalAccessException{
        orderDAO.insert(orders);
    }

    /**
     * apeleaza metoda care gaseste fiecare linie din tabel
     * @return o lista cu fiecare linie din tabel
     */
    public List<Orders> findAll(){
        return orderDAO.findAll();
    }

    /**
     * apeleaza metoda care face update la total
     * @param id
     * @param p
     */
    public void updateTotal(int id, double p){
        orderDAO.updateTotal(id, p);
    }

    /**
     * apeleaza metoda care returneaza totalul
     * @param id
     * @return
     */
    public double getTotal(int id){
        return orderDAO.findTotal(id);
    }

}
