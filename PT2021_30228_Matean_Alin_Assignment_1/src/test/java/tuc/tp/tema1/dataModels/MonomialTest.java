package tuc.tp.tema1.dataModels;

import org.junit.Test;

import static org.junit.Assert.*;

public class MonomialTest {
    private Monomial firstMonomial;
    private Monomial secondMonomial;
    public void setUp() {
        this.firstMonomial = new Monomial(7, 3);
        this.secondMonomial = new Monomial(10, 3);
    }

    @Test
    public void addMonomial() {
        setUp();
        String result = "17X^3";
        firstMonomial.addMonomial(secondMonomial);
        String myResult = firstMonomial.getCoefficient().intValue() + "X^" + firstMonomial.getExponent();
        assertTrue(myResult.equals(result));
    }

    @Test
    public void substractMonomial() {
        setUp();
        String result = "-3X^3";
        firstMonomial.substractMonomial(secondMonomial);
        String myResult = firstMonomial.getCoefficient().intValue() + "X^" + firstMonomial.getExponent();
        assertTrue(myResult.equals(result));
    }

    @Test
    public void multiplyMonomial() {
        setUp();
        String result = "70X^6";
        Monomial monomialResult = firstMonomial.multiplyMonomial(secondMonomial);
        String myResult = monomialResult.getCoefficient().intValue() + "X^" + monomialResult.getExponent();
        assertTrue(myResult.equals(result));
    }

    @Test
    public void divideMonomial() {
        setUp();
        String result = "0.7";
        Monomial monomialResult = firstMonomial.divideMonomial(secondMonomial);
        String myResult = monomialResult.getCoefficient().doubleValue()+"";
        assertTrue(myResult.equals(result));
    }

    @Test
    public void integrateMonomial() {
        setUp();
        String result = "1.75X^4";
        firstMonomial.integrateMonomial();
        String myResult = firstMonomial.getCoefficient().doubleValue() + "X^" + firstMonomial.getExponent();
        assertTrue(myResult.equals(result));
    }

    @Test
    public void deriveMonomial() {
        setUp();
        String result = "21X^2";
        firstMonomial.deriveMonomial();
        String myResult = firstMonomial.getCoefficient().intValue() + "X^" + firstMonomial.getExponent();
        assertTrue(myResult.equals(result));
    }
}