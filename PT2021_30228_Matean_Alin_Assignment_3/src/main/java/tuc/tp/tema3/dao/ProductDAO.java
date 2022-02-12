package tuc.tp.tema3.dao;

import tuc.tp.tema3.connection.ConnectionFactory;
import tuc.tp.tema3.model.Client;
import tuc.tp.tema3.model.Product;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO extends AbstractDAO<Product>{
    private static final String selectPrice = "SELECT price FROM product WHERE idProduct = ?";
    private static final String selectQuantity = "SELECT quantity FROM product WHERE idProduct = ?";
    private static final String selectName = "SELECT name FROM product WHERE idProduct = ?";


    /**
     * extrage pretul corespunzator
     * @param idProduct
     * @return pretul
     */
    public double findPrice(int idProduct){
        double toReturn = 0;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try{
            findStatement = dbConnection.prepareStatement(selectPrice);
            findStatement.setLong(1, idProduct);
            rs = findStatement.executeQuery();
            rs.next();
            double price = rs.getDouble("price");
            toReturn = price;
            return toReturn;
        }
        catch (SQLException e){
            LOGGER.log(Level.WARNING, "ProductDAO: findPrice" + e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;

    }

    /**
     * extrage cantitatea corespunzatoare
     * @param idProduct
     * @return cantitatea
     */
    public int findQuantity(int idProduct){
        int toReturn = 0;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try{
            findStatement = dbConnection.prepareStatement(selectQuantity);
            findStatement.setLong(1, idProduct);
            rs = findStatement.executeQuery();
            rs.next();
            int quantity = rs.getInt("quantity");
            toReturn = quantity;
            return toReturn;
        }
        catch (SQLException e){
            LOGGER.log(Level.WARNING, "ProductDAO: findQuantity" + e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    /**
     * extrage numele corespunzator
     * @param idProduct
     * @return nume
     */
    public String findName(int idProduct){
        String toReturn = "";

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try{
            findStatement = dbConnection.prepareStatement(selectName);
            findStatement.setLong(1, idProduct);
            rs = findStatement.executeQuery();
            rs.next();
            String name = rs.getString("name");
            toReturn = name;
            return toReturn;
        }
        catch (SQLException e){
            LOGGER.log(Level.WARNING, "ProductDAO: findName" + e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
}
