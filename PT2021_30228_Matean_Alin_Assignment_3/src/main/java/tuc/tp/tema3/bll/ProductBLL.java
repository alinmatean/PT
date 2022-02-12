package tuc.tp.tema3.bll;

import tuc.tp.tema3.bll.validators.OrderQuantity;
import tuc.tp.tema3.bll.validators.ProductPriceValidator;
import tuc.tp.tema3.bll.validators.Validator;
import tuc.tp.tema3.dao.ClientDAO;
import tuc.tp.tema3.dao.ProductDAO;
import tuc.tp.tema3.model.Client;
import tuc.tp.tema3.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Business logic class
 * incapsuleaza operatiile care se fac asupra produselor
 */
public class ProductBLL {
    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new ProductPriceValidator());
        validators.add((new OrderQuantity()));

        productDAO = new ProductDAO();
    }

    /**
     * apeleaza metoda care genereaza tot tabelul product din baza de date
     * @return o lista cu tabelul produselor
     */
    public List<Product> findAll(){
        return productDAO.findAll();
    }

    /**
     * apeleaza metoda care insereaza un produs
     * @param product
     * @throws IllegalAccessException
     */
    public void insertProduct(Product product) throws IllegalAccessException{
        for (Validator<Product> productValidator : validators) {
            productValidator.validate(product);
        }
        productDAO.insert(product);
    }

    /**
     * apeleaza metoda care sterge un produs
     * @param idProduct
     */
    public void deleteProduct(int idProduct) {
        if (idProduct == 0) {
            throw new NoSuchElementException("This client does not exists!");
        }
        productDAO.delete(idProduct);
    }
    public void updateProduct(Product product) throws IllegalAccessException{
        productDAO.update(product);
    }

    /**
     * apeleaza metoda care extrage pretul unui produs
     * @param id
     * @return pretul produsului
     */
    public double getPrice(int id){
        return productDAO.findPrice(id);
    }

    /**
     * apeleaza metoda care extrage canttatea unui produs
     * @param id
     * @return cantitatea produsului
     */
    public int getQuantity(int id){
        return productDAO.findQuantity(id);
    }

    /**
     * apeleaza metoda care extrage numele produsului
     * @param id
     * @return numele produsului
     */
    public String getName(int id){
        return productDAO.findName(id);
    }
}
