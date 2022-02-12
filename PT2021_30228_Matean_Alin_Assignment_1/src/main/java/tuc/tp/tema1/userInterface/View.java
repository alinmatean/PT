package tuc.tp.tema1.userInterface;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    public JButton getAddBtn() {
        return addBtn;
    }

    public JButton getSubBtn() {
        return subBtn;
    }

    public JButton getMulBtn() {
        return mulBtn;
    }

    public JButton getDivBtn() {
        return divBtn;
    }

    public JButton getIntBtn() {
        return intBtn;
    }

    public JButton getDerBtn() {
        return derBtn;
    }

    public JButton getClearp1() {
        return clearp1;
    }

    public JButton getClearp2() {
        return clearp2;
    }

    public JTextField getPol1() {
        return pol1;
    }

    public JTextField getPol2() {
        return pol2;
    }

    public JTextField getResult() {
        return result;
    }
    public void setResult(String s) {
        result.setText(s);
    }

    private JButton addBtn = new JButton("Add");
    private JButton subBtn = new JButton("Substract");
    private JButton mulBtn = new JButton("Multiply");
    private JButton divBtn = new JButton("Divide");
    private JButton intBtn = new JButton("Integrate");
    private JButton derBtn = new JButton("Derive");
    private JButton clearp1 = new JButton("Clear");
    private JButton clearp2 = new JButton("Clear");
    private JTextField pol1;
    private JTextField pol2;
    private JTextField result;

    public View(){
        this.setTitle("Polynomial Calculator");
        this.setSize(400, 280);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pol1 = new JTextField(25);
        pol2 = new JTextField(25);
        result = new JTextField(25);
        result.setEditable(false);
        JPanel polynomialPanel1 = new JPanel();
        JPanel polynomialPanel2 = new JPanel();
        JPanel operationsPanel = new JPanel();
        JPanel resultPanel = new JPanel();
        JPanel mainPanel = new JPanel();

        polynomialPanel1.add(new Label("First Polynomial:"));
        polynomialPanel1.add(pol1);
        polynomialPanel1.add(clearp1);
        polynomialPanel2.add(new Label("Second Polynomial:"));
        polynomialPanel2.add(pol2);
        polynomialPanel2.add(clearp2);

        operationsPanel.add(addBtn);
        operationsPanel.add(subBtn);
        operationsPanel.add(mulBtn);
        operationsPanel.add(divBtn);
        operationsPanel.add(intBtn);
        operationsPanel.add(derBtn);

        resultPanel.add(result);

        mainPanel.add(polynomialPanel1);
        mainPanel.add(polynomialPanel2);
        mainPanel.add(operationsPanel);
        mainPanel.add(resultPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        this.setContentPane(mainPanel);
        this.setVisible(true);
    }

    public void setFirstPolynomial(String s){
        pol1.setText(s);
    }

    public void setSecondPolynomial(String s){
        pol2.setText(s);
    }

    public String getFirstPolynomial(){
        return pol1.getText();
    }

    public String getSecondPolynomial(){
        return pol2.getText();
    }

    public void displayErrorMessage(String s){
        JOptionPane.showMessageDialog(this, s);
    }

}
