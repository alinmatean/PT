package tuc.tp.tema3.bll.validators;

import tuc.tp.tema3.model.Product;

/**
 * clasa care implementeaza interfata Validator
 */
public class ProductPriceValidator implements Validator<Product> {
    private static final double MIN = 0.1;
    private static final double MAX = 500;

    /**
     * verifica pretul sa fie intr-un anumit interval
     * @param product
     */
    @Override
    public void validate(Product product) {
        if(product.getPrice() < MIN || product.getPrice() > MAX){
            throw new IllegalArgumentException("Product limit is not respected!");
        }
    }
}
