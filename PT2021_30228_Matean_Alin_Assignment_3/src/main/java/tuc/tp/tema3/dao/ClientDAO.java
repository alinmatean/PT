package tuc.tp.tema3.dao;

import tuc.tp.tema3.connection.ConnectionFactory;
import tuc.tp.tema3.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDAO extends AbstractDAO<Client>{

    private static final String selectName = "SELECT name FROM client WHERE idClient = ?";

    /**
     * extrage numele clientului corespunzator
     * @param idClient
     * @return
     */
    public String findName(int idClient){
        String toReturn = "";

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try{
            findStatement = dbConnection.prepareStatement(selectName);
            findStatement.setLong(1, idClient);
            rs = findStatement.executeQuery();
            rs.next();
            String name = rs.getString("name");
            toReturn = name;
            return toReturn;
        }
        catch (SQLException e){
            LOGGER.log(Level.WARNING, "ClientDAO: findName" + e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
}
