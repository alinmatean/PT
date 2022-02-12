package tuc.tp.tema1.userInterface;

import tuc.tp.tema1.dataModels.Polynomial;
import tuc.tp.tema1.operations.Operation;
import tuc.tp.tema1.regex.Validator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private View pc_view;
    private Polynomial p1;
    private Polynomial p2;

    public Polynomial getP1() {
        return p1;
    }

    public Polynomial getP2() {
        return p2;
    }

    public Controller(Polynomial p1, Polynomial p2, View view){
        this.p1 = p1;
        this.p2 = p2;
        pc_view = view;

        pc_view.getAddBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getPolynomials();
                Polynomial p = Operation.addOperation(getP1(), getP2());
                pc_view.setResult(p.toString());
                //System.out.println(p);
            }
        });

        pc_view.getSubBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getPolynomials();
                Polynomial p = Operation.substractOperation(getP1(), getP2());
                pc_view.setResult(p.toString());
            }
        });

        pc_view.getMulBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getPolynomials();
                Polynomial p = Operation.multiplyOperation(getP1(), getP2());
                pc_view.setResult(p.toString());
            }
        });

        pc_view.getDivBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getPolynomials();
                Polynomial p = Operation.divideOperation(getP1(), getP2());
                pc_view.setResult(p.toString() + " REST: " + getP1().toString());
            }
        });

        pc_view.getIntBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getPolynomials();
                Operation.integrateOperation(getP1());
                pc_view.setResult(getP1().toString());
            }
        });

        pc_view.getDerBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getPolynomials();
                Operation.deriveOperation(getP1());
                pc_view.setResult(getP1().toString());
            }
        });

        pc_view.getClearp1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pc_view.setFirstPolynomial("");
            }
        });

        pc_view.getClearp2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pc_view.setSecondPolynomial("");
            }
        });

    }

    public void getPolynomials(){
        String s1, s2;
        s1 = pc_view.getFirstPolynomial();
        s2 = pc_view.getSecondPolynomial();
        Validator valid = new Validator();

        if(valid.validatePolynomial(s1)) {
            this.p1 = valid.createPolynomial(s1);
            System.out.println(valid.validatePolynomial(s1));
            if(valid.validatePolynomial(s2))
                this.p2 = valid.createPolynomial(s2);
            else
                this.pc_view.displayErrorMessage("Second polynomial is invalid!");
        }
        else
            this.pc_view.displayErrorMessage("First polynomial is invalid!");

    }


}
