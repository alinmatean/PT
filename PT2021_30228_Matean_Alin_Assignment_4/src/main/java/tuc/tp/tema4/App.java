package tuc.tp.tema4;

import tuc.tp.tema4.business.DeliveryService;
import tuc.tp.tema4.business.MenuItem;
import tuc.tp.tema4.data.Serializator;
import tuc.tp.tema4.presentation.Login;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Serializator.deserialize( "delivery.ser");
        Login loginView = new Login();
        /*DeliveryService deliveryService = new DeliveryService();
        deliveryService.importProducts();
        for(MenuItem menuItem: deliveryService.getMenuItems().values())
        {
            System.out.println(menuItem.getTitle());
            System.out.println(String.valueOf(menuItem.computeRating()));
        }*/
    }
}
