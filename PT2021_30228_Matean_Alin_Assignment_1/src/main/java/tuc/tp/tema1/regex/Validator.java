package tuc.tp.tema1.regex;

import tuc.tp.tema1.dataModels.Monomial;
import tuc.tp.tema1.dataModels.Polynomial;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private Pattern pattern;
    private Pattern invalidPattern;
    //private static final String VALID_PATTERN = " ([+-]?(?:(?:\\d+[xX]\\^\\d+)|(?:\\d+x)|(?:\\d+)|(?:x))) " ;
    private static final String VALID_PATTERN = "([+-]?\\d+)?[xX]\\^?(\\d+)?|([+-]?\\d+)[xX]?|(-[xX])";
    private static final String INVALID_PATTERN = "[^xX0123456789^+-]";

    /* o singura problema am aici, mi-am dat seama mai tarziu, trebuie sa introduc si -1 in cazul in care doresc coeficientul
    unui monom sa fie -1, asta doar daca gradul monomului este mai are decat 1
    exemplu: pot scrie -x dar nu pot scrie -x^2 trebuie -1x^2
    */
    public Validator(){
        this.pattern = Pattern.compile(VALID_PATTERN);
        this.invalidPattern = Pattern.compile(INVALID_PATTERN);
    }

    public boolean validateMonomial(String s){
        Matcher matcher = pattern.matcher(s);
        Matcher notMatcher = invalidPattern.matcher(s);
        if(notMatcher.matches())
            return false;
        else
            return matcher.matches();
    }

    public boolean validatePolynomial(String s){
        Matcher matcher = pattern.matcher(s);
        Matcher notMatcher = invalidPattern.matcher(s);
        if(notMatcher.find())
            return false;
        else
        {
            while (matcher.find()) {
                if (validateMonomial(matcher.group()) == false)
                    return false;
            }
        }
        return true;
    }

    public Polynomial createPolynomial(String s){
        ArrayList<Monomial> list = new ArrayList<Monomial>();
        Matcher matcher = pattern.matcher(s);
        Matcher notMatcher = invalidPattern.matcher(s);
        if(!notMatcher.find()) {
            while (matcher.find()) {
                int coefficient;
                int exponent;
                if (matcher.group(1) == null && matcher.group(2) == null) {
                    if (matcher.group(3) == null) {
                        if(matcher.group(4) != null) {
                            coefficient = -1;
                            exponent = 1;
                        }
                        else{
                            coefficient = 1;
                            exponent = 1;
                        }
                    }
                    else {
                        if(matcher.group(4) != null) {
                            coefficient = -1;
                            exponent = Integer.parseInt(matcher.group(3));
                        }
                        else{
                            coefficient = Integer.parseInt(matcher.group(3));
                            exponent = 0;
                        }
                    }
                }
                else if (matcher.group(1) == null && matcher.group(2) != null) {
                    coefficient = 1;
                    exponent = Integer.parseInt(matcher.group(2));
                }
                else if (matcher.group(2) == null && matcher.group(1) != null) {
                    coefficient = Integer.parseInt(matcher.group(1));
                    exponent = 1;
                }
                 else {
                    coefficient = Integer.parseInt(matcher.group(1));
                    exponent = Integer.parseInt(matcher.group(2));
                }
                list.add(new Monomial((Number) coefficient, exponent));
            }
        }
        for(Monomial m: list)
            System.out.println("coeficient: " + m.getCoefficient().intValue() + " exponent: " + m.getExponent());
        return new Polynomial(list);
    }
}
