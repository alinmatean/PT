package tuc.tp.tema1.dataModels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Polynomial {
    ArrayList<Monomial> monomials = new ArrayList<Monomial>();
    public Polynomial(ArrayList<Monomial> monomials) {
        this.monomials = monomials;
    }

    public void setMonomials(ArrayList<Monomial> monomials) {
        this.monomials = monomials;
    }

    public ArrayList<Monomial> getMonomials() {
        return monomials;
    }

    public boolean existExponent(int e){
        for(Monomial x: monomials)
            if(x.getExponent() == e)
                return true;
        return false;
    }

    public double getMaxExponent(ArrayList<Monomial> polynomsList)
    {
        int max = 0;
        for(Monomial x: polynomsList)
        {
            if(x.getExponent() > max)
            {
                max = x.getExponent();
            }
        }
        return max;
    }

    public Monomial getMaxMonomial(Polynomial d) {
        for(Monomial monomial: d.getMonomials())
            if(monomial.getExponent() == d.getMaxExponent(d.getMonomials()))
                return monomial;
        return null;
    }

    public String toString(){
        String result = "";
        Collections.sort(this.monomials);
        if(monomials.size() == 0)
            result += "0";
        for(Monomial monomial: this.monomials) {
            if (monomial.getCoefficient().doubleValue() < 0.0)
                result += " - ";
            else if (monomial != monomials.get(0))
                result += " + ";

            if(monomial.getCoefficient().doubleValue()-monomial.getCoefficient().intValue() == 0.0) {
                if (monomial.getCoefficient().intValue() == 0)
                    result = "";
                else if (monomial.getCoefficient().intValue() == 1 || monomial.getCoefficient().intValue() == -1) {
                    if (monomial.getExponent() == 0)
                        result += 1;
                    else if(monomial.getExponent() == 1)
                        result += "X";
                    else
                        result += "X^" + monomial.getExponent();
                } else if (monomial.getExponent() == 0)
                    result += Math.abs(monomial.getCoefficient().intValue());
                else if(monomial.getExponent() == 1)
                    result += Math.abs(monomial.getCoefficient().intValue()) + "X";
                else
                    result += Math.abs(monomial.getCoefficient().intValue()) + "X^" + monomial.getExponent();
            }else {
                if (monomial.getExponent() == 0)
                    result += Math.abs(monomial.getCoefficient().doubleValue());
                else if(monomial.getExponent() == 1)
                    result += Math.abs(monomial.getCoefficient().doubleValue()) + "X";
                else
                    result += Math.abs(monomial.getCoefficient().doubleValue()) + "X^" + monomial.getExponent();
            }
        }
        return result;
    }
}
