package tuc.tp.tema3.dao;

import tuc.tp.tema3.connection.ConnectionFactory;
import tuc.tp.tema3.model.Orders;

import java.sql.*;
import java.util.logging.Level;

public class OrderDAO extends AbstractDAO<Orders>{
    private static final String updateQuery = "update orders SET total = ? where idOrder = ?";
    private static final String findTotal = "SELECT total FROM orders where idOrder = ?";

    /**
     * face update la coloana total din orders
     * @param idOrder
     * @param total
     */
    public void updateTotal(int idOrder, double total){

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement(updateQuery);
            statement.setDouble(1, total);
            statement.setInt(2, idOrder);
            statement.executeUpdate();
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, "OrderDAO: update total" + e.getMessage());
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * extrage totalul din tabela orders
     * @param idOrder
     * @return
     */
    public double findTotal(int idOrder){
        double toReturn = 0;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try{
            findStatement = dbConnection.prepareStatement(findTotal);
            findStatement.setLong(1, idOrder);
            rs = findStatement.executeQuery();
            rs.next();
            double price = rs.getDouble("total");
            toReturn = price;
            return toReturn;
        }
        catch (SQLException e){
            LOGGER.log(Level.WARNING, "OrderDAO: findTotal" + e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;

    }
}
