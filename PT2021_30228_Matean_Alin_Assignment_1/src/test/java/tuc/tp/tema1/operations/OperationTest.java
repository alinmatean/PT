package tuc.tp.tema1.operations;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tuc.tp.tema1.dataModels.Monomial;
import tuc.tp.tema1.dataModels.Polynomial;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.params.provider.Arguments.of;

public class OperationTest {
    static Polynomial polynomial1;
    static Polynomial polynomial2;
    static Polynomial polynomial3;
    static Polynomial polynomial4;
    static Polynomial polynomial5;
    static Polynomial polynomial6;
    static Polynomial polynomial7;
    static Polynomial polynomial8;
    static Polynomial polynomial9;
    static Polynomial polynomial10;
    static Polynomial polynomial11;
    static Polynomial polynomial12;

    static {
        Monomial m1 = new Monomial(1,3);
        Monomial m2 = new Monomial(-2,2);
        Monomial m3 = new Monomial(6,1);
        Monomial m4 = new Monomial(-5,0);

        Monomial m5 = new Monomial(1,2);
        Monomial m6 = new Monomial(3,1);

        Monomial m7 = new Monomial(1,3);
        Monomial m8 = new Monomial(-2,2);
        Monomial m9 = new Monomial(-1,0);

        Monomial m10 = new Monomial(1,1);
        Monomial m11 = new Monomial(-3,0);

        Monomial m12 = new Monomial(2,3);
        Monomial m13 = new Monomial(-3,2);
        Monomial m14 = new Monomial(4,1);
        Monomial m15 = new Monomial(5,0);

        Monomial m16 = new Monomial(1,2);
        Monomial m17 = new Monomial(2,0);

        ArrayList<Monomial> list1 = new ArrayList<Monomial>();
        ArrayList<Monomial> list2 = new ArrayList<Monomial>();
        ArrayList<Monomial> list3 = new ArrayList<Monomial>();
        ArrayList<Monomial> list4 = new ArrayList<Monomial>();
        ArrayList<Monomial> list5 = new ArrayList<Monomial>();
        ArrayList<Monomial> list6 = new ArrayList<Monomial>();

        list1.add(m1);
        list1.add(m2);
        list1.add(m3);
        list1.add(m4);
        list2.add(m5);
        list2.add(m6);
        list3.add(m7);
        list3.add(m8);
        list3.add(m9);
        list4.add(m10);
        list4.add(m11);
        list5.add(m12);
        list5.add(m13);
        list5.add(m14);
        list5.add(m15);
        list6.add(m16);
        list6.add(m17);

        polynomial1 = new Polynomial(list1);
        polynomial2 = new Polynomial(list2);
        polynomial3 = new Polynomial(list3);
        polynomial4 = new Polynomial(list4);
        polynomial5 = new Polynomial(list5);
        polynomial6 = new Polynomial(list6);

//////////////////pt impartire
        Monomial m12t = new Monomial(2,3);
        Monomial m13t = new Monomial(-3,2);
        Monomial m14t = new Monomial(4,1);
        Monomial m15t = new Monomial(5,0);

        Monomial m16t = new Monomial(1,2);
        Monomial m17t = new Monomial(2,0);

        ArrayList<Monomial> list1t = new ArrayList<Monomial>();
        ArrayList<Monomial> list2t = new ArrayList<Monomial>();
        list1t.add(m12t);
        list1t.add(m13t);
        list1t.add(m14t);
        list1t.add(m15t);
        list2t.add(m16t);
        list2t.add(m17t);

        polynomial7 = new Polynomial(list1t);
        polynomial8 = new Polynomial(list2t);

///////////////pt integrare
        ArrayList<Monomial> list1i = new ArrayList<Monomial>();
        ArrayList<Monomial> list2i = new ArrayList<Monomial>();

        Monomial m7i = new Monomial(1,3);
        Monomial m8i = new Monomial(-2,2);
        Monomial m9i = new Monomial(-1,0);
        Monomial m12i = new Monomial(2,3);
        Monomial m13i = new Monomial(-3,2);
        Monomial m14i = new Monomial(4,1);
        Monomial m15i = new Monomial(5,0);
        list1i.add(m7i);
        list1i.add(m8i);
        list1i.add(m9i);
        list2i.add(m12i);
        list2i.add(m13i);
        list2i.add(m14i);
        list2i.add(m15i);
        polynomial9 = new Polynomial(list1i);
        polynomial10 = new Polynomial(list2i);

/////////////pt derivare
        Monomial m1d = new Monomial(1,3);
        Monomial m2d = new Monomial(-2,2);
        Monomial m3d = new Monomial(6,1);
        Monomial m4d = new Monomial(-5,0);
        Monomial m12d = new Monomial(2,3);
        Monomial m13d = new Monomial(-3,2);
        Monomial m14d = new Monomial(4,1);
        Monomial m15d = new Monomial(5,0);

        ArrayList<Monomial> list1d = new ArrayList<Monomial>();
        ArrayList<Monomial> list2d = new ArrayList<Monomial>();

        list1d.add(m1d);
        list1d.add(m2d);
        list1d.add(m3d);
        list1d.add(m4d);
        list2d.add(m12d);
        list2d.add(m13d);
        list2d.add(m14d);
        list2d.add(m15d);

        polynomial11 = new Polynomial(list1d);
        polynomial12 = new Polynomial(list2d);
    }

    private static List<Arguments> addInput(){
        List<Arguments> arguments = new ArrayList<>();
        arguments.add(of(polynomial1, polynomial2, "X^3 - X^2 + 9X - 5"));
        arguments.add(Arguments.of(polynomial3, polynomial5, "3X^3 - 5X^2 + 4X + 4"));
        arguments.add(Arguments.of(polynomial4, polynomial6, "X^2 + X - 1"));
        return arguments;
    }

    private static List<Arguments> substractInput(){
        List<Arguments> arguments = new ArrayList<>();
        arguments.add(of(polynomial1, polynomial2, "X^3 - 2X^2 + 6X - 5"));
        arguments.add(Arguments.of(polynomial3, polynomial5, "X^3 - 2X^2 - 4X - 1"));
        arguments.add(Arguments.of(polynomial4, polynomial6, " - X^2 + X - 3"));
        return arguments;
    }

    private static List<Arguments> multiplyInput(){
        List<Arguments> arguments = new ArrayList<>();
        arguments.add(of(polynomial1, polynomial2, "X^5 + X^4 - 6X^3 + 6X^3 + 13X^2 - 15X"));
        arguments.add(Arguments.of(polynomial3, polynomial4, "X^4 - 5X^3 + 6X^2 - X + 3"));
        arguments.add(Arguments.of(polynomial5, polynomial6, "2X^5 - 3X^4 + 8X^3 - X^2 + 8X + 10"));
        return arguments;
    }

    private static List<Arguments> divideInput(){
        List<Arguments> arguments = new ArrayList<>();
        arguments.add(of(polynomial7, polynomial8, "2X - 3 REST: 11"));
        return arguments;
    }

    private static List<Arguments> integrateInput(){
        List<Arguments> arguments = new ArrayList<>();
        arguments.add(of(polynomial9, "0.25X^4 - 0.6666666666666666X^3 - X"));
        arguments.add(of(polynomial10, "0.5X^4 - X^3 + 2X^2 + 5X"));
        return arguments;
    }

    private static List<Arguments> deriveInput(){
        List<Arguments> arguments = new ArrayList<>();
        arguments.add(of(polynomial11, "3X^2 - 4X + 6"));
        arguments.add(of(polynomial12, "6X^2 - 6X + 4"));
        return arguments;
    }

    @ParameterizedTest
    @MethodSource("addInput")
    public void addOperation(Polynomial polynomial1, Polynomial polynomial2, String expectedResult){
        assertEquals(expectedResult, Operation.addOperation(polynomial1, polynomial2).toString());
    }

    @ParameterizedTest
    @MethodSource("substractInput")
    public void substractOperation(Polynomial polynomial1, Polynomial polynomial2, String expectedResult){
        assertEquals(expectedResult, Operation.substractOperation(polynomial1, polynomial2).toString());
    }

    @ParameterizedTest
    @MethodSource("multiplyInput")
    public void multiplyOperation(Polynomial polynomial1, Polynomial polynomial2, String expectedResult){
        assertEquals(expectedResult, Operation.multiplyOperation(polynomial1, polynomial2).toString());
    }

    @ParameterizedTest
    @MethodSource("divideInput")
    public void divideOperation(Polynomial polynomial1, Polynomial polynomial2, String expectedResult){
        Polynomial result = Operation.divideOperation(polynomial1, polynomial2);
        assertEquals(expectedResult, result.toString() + " REST: " + polynomial1.toString());
    }

    @ParameterizedTest
    @MethodSource("integrateInput")
    public void integrateOperation(Polynomial polynomial1, String expectedResult){
        Operation.integrateOperation(polynomial1);
        assertEquals(expectedResult, polynomial1.toString());
    }

    @ParameterizedTest
    @MethodSource("deriveInput")
    public void deriveOperation(Polynomial polynomial1, String expectedResult){
        Operation.deriveOperation(polynomial1);
        assertEquals(expectedResult, polynomial1.toString());
    }


    /*@Test
    public void addOperation() {
        setUp();
        String result = "X^3 - X^2 + 6X - 6";
        Polynomial resultPolynomial = Operation.addOperation(polynomial1, polynomial2);
        String myResult = resultPolynomial.toString();
        assertTrue(myResult.equals(result));
    }

    @Test
    public void substractOperation() {
        setUp();
        String result = "X^3 - 3X^2 + 6X - 4";
        Polynomial resultPolynomial = Operation.substractOperation(polynomial1, polynomial2);
        String myResult = resultPolynomial.toString();
        assertTrue(myResult.equals(result));
    }

    @Test
    public void multiplyOperation() {
        setUp();
        String result = "X^5 - 2X^4 + 5X^3 - 3X^2 - 6X + 5";
        Polynomial resultPolynomial = Operation.multiplyOperation(polynomial1, polynomial2);
        String myResult = resultPolynomial.toString();
        assertTrue(myResult.equals(result));
    }

    @Test
    public void divideOperation() {
        setUp();
        String result = "X - 2 REST 7X - 7";
        Polynomial resultPolynomial = Operation.divideOperation(polynomial1, polynomial2);
        String myResult = resultPolynomial.toString() + " REST " + polynomial1.toString();
        assertTrue(myResult.equals(result));
    }

    @Test
    public void integrateOperation() {
        setUp();
        String result = "0.25X^4 - 0.6666666666666666X^3 + 3X^2 - 5X";
        Operation.integrateOperation(polynomial1);
        String myResult = polynomial1.toString();
        assertTrue(myResult.equals(result));
    }

    @Test
    public void deriveOperation() {
        setUp();
        String result = "3X^2 - 4X + 6";
        Operation.deriveOperation(polynomial1);
        String myResult = polynomial1.toString();
        assertTrue(myResult.equals(result));
    }*/
}