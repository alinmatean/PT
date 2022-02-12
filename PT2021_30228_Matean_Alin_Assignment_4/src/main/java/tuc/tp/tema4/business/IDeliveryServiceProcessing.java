package tuc.tp.tema4.business;

import java.util.ArrayList;
import java.util.Map;

/**
 * IDeliveryServiceProcessing
 */
public interface IDeliveryServiceProcessing {
    /**
     *adauga un produs de baza
     * @param title
     * @param rating
     * @param calories
     * @param proteins
     * @param fat
     * @param sodium
     * @param price
     * @pre title != null
     * @pre price != 0
     */
    void addProduct(String title, Double rating, Integer calories, Integer proteins, Integer fat, Integer sodium, Integer price);

    /**
     *adauga un produs compus
     * @param title
     * @pre title != null
     */
    void addProduct(String title);

    /**
     * sterge un produs
     * @param menuItem
     * @invariant isWellFormed()
     * @pre menuItem != null
     */
    void deleteProduct(MenuItem menuItem);

    /**
     * @pre menuItem != null
     * @param menuItem
     * @param price
     * @post price != 0
     */
    void editProduct(MenuItem menuItem, int price);

    /**
     * editeaza un produs
     * @pre menuItem != null
     * @pre string != null
     * @param menuItem
     * @param string
     */
    void addToComposite(MenuItem menuItem, String string);

    /**
     *
     * @return true daca clasa e bine formata
     */
    boolean isWellFormed();
}
