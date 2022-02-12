package tuc.tp.tema1.dataModels;

import org.jetbrains.annotations.NotNull;

public class Monomial implements Comparable<Monomial>{
    private Number coefficient;
    private int exponent;

    public Monomial(Number coefficient, int exponent) {
        if(coefficient.doubleValue() != 0.0) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }
    }

    public Number getCoefficient() {
        return coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    public void setCoefficient(int coefficient) {

        this.coefficient = coefficient;
    }

    public void setCoefficient(double coefficient) {

        this.coefficient = coefficient;
    }

    public void setExponent(int exponent) {

        this.exponent = exponent;
    }

    public void addMonomial(Monomial x){
        this.setCoefficient(this.coefficient.intValue() + x.coefficient.intValue());
    }

    public void substractMonomial(Monomial x){
        if(this.exponent == x.getExponent())
            this.setCoefficient(this.coefficient.doubleValue() - x.getCoefficient().doubleValue());
        else
            return;
    }

    public Monomial multiplyMonomial(Monomial x){
        return new Monomial(this.coefficient.doubleValue() * x.getCoefficient().doubleValue(), this.exponent + x.getExponent());
    }

    public Monomial divideMonomial(Monomial x){
        double coef = this.getCoefficient().doubleValue()/x.getCoefficient().doubleValue();
        int exp = this.exponent - x.getExponent();
        Monomial res = new Monomial(coef, exp);
        return res;
    }

    public void integrateMonomial(){
        double b = this.exponent + 1;
        double a = this.coefficient.doubleValue()/b;
        this.setCoefficient(a);
        this.setExponent(this.exponent +1);
    }

    public void deriveMonomial(){
        this.setCoefficient(this.coefficient.intValue() * this.exponent);
        this.setExponent(this.exponent - 1);
    }

    public Monomial changeSignMonomial(){

        return new Monomial(this.coefficient.intValue()*(-1), this.exponent);
    }

    @Override
    public int compareTo(Monomial o) {
        return o.exponent - this.exponent;
    }

}
