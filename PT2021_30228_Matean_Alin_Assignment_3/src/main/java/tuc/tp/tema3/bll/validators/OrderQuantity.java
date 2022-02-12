package tuc.tp.tema3.bll.validators;

import tuc.tp.tema3.model.Product;

/**
 * clasa care implementeaza interfata Validator
 */
public class OrderQuantity implements Validator<Product> {

    /**
     * clasa care verifica cantitatea sa nu fie negativa
     * @param product
     */
    @Override
    public void validate(Product product) {
        if(product.getQuantity() < 1){
            throw new IllegalArgumentException("Product quantity is not respected!");
        }
    }
}
