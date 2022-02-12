package tuc.tp.tema1;

import tuc.tp.tema1.dataModels.Monomial;
import tuc.tp.tema1.dataModels.Polynomial;
import tuc.tp.tema1.operations.Operation;
import tuc.tp.tema1.userInterface.Controller;
import tuc.tp.tema1.userInterface.View;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        View calculator_view = new View();
        Polynomial p1 = null;
        Polynomial p2 = null;
        Controller control = new Controller(p1, p2, calculator_view);
    }
}
