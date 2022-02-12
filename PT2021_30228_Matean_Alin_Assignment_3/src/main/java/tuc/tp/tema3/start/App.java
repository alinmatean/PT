package tuc.tp.tema3.start;

import tuc.tp.tema3.bll.ClientBLL;
import tuc.tp.tema3.model.Client;
import tuc.tp.tema3.presentation.Controller;
import tuc.tp.tema3.presentation.View;

import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * clasa care porneste aplicatia
 *
 */
public class App 
{
    protected static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main ( String[] args )throws SQLException
    {
        ClientBLL clientBLL = new ClientBLL();
        List<Client> allClients = new ArrayList<Client>();

        try {
            allClients = clientBLL.findAll();
        }catch (Exception e){
            LOGGER.log(Level.INFO, e.getMessage());
        }
        for(Client client: allClients) {
            Reflection.retrieveProperties(client);
            System.out.println();
        }

        View view = new View();
        Controller controller = new Controller(view);
    }
}
