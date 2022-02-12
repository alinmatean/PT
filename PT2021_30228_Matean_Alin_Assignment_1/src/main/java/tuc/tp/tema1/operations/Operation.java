package tuc.tp.tema1.operations;

import tuc.tp.tema1.dataModels.Monomial;
import tuc.tp.tema1.dataModels.Polynomial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Operation {

    public static Polynomial addOperation(Polynomial p1, Polynomial p2){
        ArrayList<Monomial> resultL= new ArrayList<Monomial>();
        for(Monomial i: p1.getMonomials())
            for (Monomial j: p2.getMonomials())
                if(i.getExponent() == j.getExponent())
                    i.addMonomial(j);

        resultL.addAll(p1.getMonomials());
        for(Monomial i: resultL)
            System.out.println(i.getCoefficient().intValue() + "X^" + i.getExponent());

        for(Monomial i: p2.getMonomials())
            if(!p1.existExponent(i.getExponent()))
                resultL.add(i);

        Iterator<Monomial> itr = resultL.iterator();
        while (itr.hasNext()){
            Monomial m = (Monomial) itr.next();
            if(m.getCoefficient().doubleValue() == 0.0)
                itr.remove();
        }

        Polynomial p = new Polynomial(resultL);
        return p;
    }

    public static Polynomial substractOperation(Polynomial p1, Polynomial p2){
        ArrayList<Monomial> resultL= new ArrayList<Monomial>();
        for(Monomial i: p1.getMonomials())
            for(Monomial j: p2.getMonomials())
                i.substractMonomial(j);
        resultL.addAll(p1.getMonomials());
        for(Monomial i: p2.getMonomials())
            if(!p1.existExponent(i.getExponent())) {
                resultL.add(i.changeSignMonomial());
            }
        Iterator<Monomial> itr = resultL.iterator();
        while (itr.hasNext()){
            Monomial m = (Monomial) itr.next();
            if(m.getCoefficient().doubleValue() == 0.0)
                itr.remove();
        }
        Polynomial p = new Polynomial(resultL);
        return p;
    }

    public static Polynomial multiplyOperation(Polynomial p1, Polynomial p2){
        ArrayList<Monomial> res = new ArrayList<Monomial>();
        boolean flag;
        for(Monomial i: p1.getMonomials())
            for(Monomial j: p2.getMonomials()){
                Monomial x = i.multiplyMonomial(j);
                flag = false;
                for(Monomial k: res) {
                    if (x.getExponent() == k.getExponent() && x.getCoefficient().intValue() != k.getCoefficient().intValue()*(-1)) {
                        k.addMonomial(x);
                        flag = true;
                    }
                }
                if(!flag)
                    res.add(x);
            }
        return new Polynomial(res);
    }

    public static Polynomial divideOperation(Polynomial p1, Polynomial p2){
        Collections.sort(p1.getMonomials());
        Collections.sort(p2.getMonomials());

        ArrayList<Monomial> resultList = new ArrayList<Monomial>();
        ArrayList<Monomial> divisorList = new ArrayList<Monomial>(p2.getMonomials());
        ArrayList<Monomial> dividendList = new ArrayList<Monomial>(p1.getMonomials());
        ArrayList<Monomial> aux = new ArrayList<Monomial> ();
        Polynomial dividend = new Polynomial(dividendList);
        Polynomial divisor= new Polynomial(divisorList);

        if(dividendList.size() == 1 && divisorList.size() == 1){
            Monomial monomial = dividend.getMonomials().get(0).divideMonomial(divisor.getMonomials().get(0));
            aux.add(monomial);
            Polynomial result = new Polynomial(aux);
            p1.setMonomials(new ArrayList<Monomial>());
            return result;
        }

        while (dividend.getMaxExponent(dividend.getMonomials()) >= divisor.getMaxExponent(divisor.getMonomials())){
            ///cat timp gradul numaratorului >= gradul numitorului continuam impartirea
            Monomial currentMonomial = dividend.getMaxMonomial(dividend).divideMonomial(divisor.getMaxMonomial(divisor));///gradul maxim numarator/grad maxim numitor
            resultList.add(currentMonomial);///lista de monome rezultata
            aux.add(currentMonomial);
            Polynomial partialSubstractResult = multiplyOperation(p2, new Polynomial(aux));///inmultirea numitorului cu monomul curent rezultat din impartire
            dividend = substractOperation(dividend, partialSubstractResult);///scaderea pt a rezulta "noul numarator"
            aux.remove(currentMonomial);
        }
        Polynomial result = new Polynomial(resultList);
        p1.setMonomials(dividend.getMonomials());
        return result;
    }

    public static void integrateOperation(Polynomial p1){
        for(Monomial m: p1.getMonomials())
            m.integrateMonomial();
    }

    public static void deriveOperation(Polynomial p1){
        for(Monomial m: p1.getMonomials())
            m.deriveMonomial();
        Iterator<Monomial> itr = p1.getMonomials().iterator();
        while (itr.hasNext()){
            Monomial m = (Monomial) itr.next();
            if(m.getExponent() < 0)
                itr.remove();
        }
    }
}
