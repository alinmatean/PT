package tuc.tp.tema3.presentation;

import tuc.tp.tema3.bll.ClientBLL;
import tuc.tp.tema3.bll.OrderBLL;
import tuc.tp.tema3.bll.ProductBLL;
import tuc.tp.tema3.start.App;
import tuc.tp.tema3.start.Reflection;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class View extends JFrame {
    public JButton getAddClient() {
        return addClient;
    }
    public JButton getDeleteClient(){
        return deleteClient;
    }

    public JButton getUpdateClient(){
        return updateClient;
    }
    public JButton getAddProduct(){
        return addProduct;
    }
    public JButton getDeleteProduct(){
        return deleteProduct;
    }
    public JButton getUpdateProduct(){
        return updateProduct;
    }
    public JButton getAddOrder(){
        return addOrder;
    }

    public int getIdClient() {
        return Integer.parseInt(idClient.getText());
    }
    public int getIdClientDelete() {
        return Integer.parseInt(idClientDelete.getText());
    }

    public String getNameClient() {
        return nameClient.getText();
    }

    public String getAddress() {
        return address.getText();
    }

    public int getIdProduct(){
        return Integer.parseInt(idProduct.getText());
    }
    public int getIdProductDelete(){
        return Integer.parseInt(idProductDelete.getText());
    }
    public int getQuantityProduct(){
        return Integer.parseInt(quantityProduct.getText());
    }
    public Double getPriceProduct(){
        return Double.parseDouble(priceProduct.getText());
    }
    public String getNameProduct() {
        return nameProduct.getText();
    }
    public int getIdOrder(){
        return Integer.parseInt(idOrder.getText());
    }
    public int getIdClientOrder(){
        return Integer.parseInt(id_client.getText());
    }
    public int getIdProductOrder(){
        return Integer.parseInt(id_product.getText());
    }
    public int getQuantityOrder(){
        return Integer.parseInt(quantityOrder.getText());
    }

    private JButton addClient = new JButton("ADD");
    private JButton deleteClient = new JButton("DELETE");
    private JButton updateClient = new JButton("UPDATE");
    private JButton addProduct = new JButton("ADD");
    private JButton deleteProduct = new JButton("DELETE");
    private JButton updateProduct = new JButton("UPDATE");
    private JButton addOrder = new JButton("ADD");
    private JButton deleteOrder = new JButton("DELETE");

    private JScrollPane clientTable;
    private JScrollPane productTable;
    private JScrollPane orderTable;
    ///panel-uri
    JPanel p1c = new JPanel();
    JPanel p2c = new JPanel();
    JPanel p3c = new JPanel();

    JPanel p1p = new JPanel();
    JPanel p2p = new JPanel();
    JPanel p3p = new JPanel();

    JPanel p1o = new JPanel();
    JPanel p2o = new JPanel();

    private JTextField idClient = new JTextField(3);
    private  JTextField nameClient = new JTextField(10);
    private JTextField address = new JTextField(10);
    private JTextField idClientDelete = new JTextField(3);

    private JTextField idProduct;
    private JTextField nameProduct;
    private JTextField quantityProduct;
    private JTextField priceProduct;
    private JTextField idProductDelete;

    private JTextField idOrder;
    private JTextField id_client;
    private JTextField id_product;
    private JTextField quantityOrder;
    private JTextField total;

    private ClientBLL clientBLL = new ClientBLL();
    private ProductBLL productBLL = new ProductBLL();
    private OrderBLL orderBLL = new OrderBLL();
    protected static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public void update(){
        p1c.removeAll();
        p2c.removeAll();
        p3c.removeAll();
        p1p.removeAll();
        p2p.removeAll();
        p3p.removeAll();
        p1o.removeAll();
        p2o.removeAll();

        try {
            clientTable = new JScrollPane(Reflection.createTable(clientBLL.findAll()));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        try {
            productTable = new JScrollPane(Reflection.createTable(productBLL.findAll()));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        try {
            orderTable = new JScrollPane(Reflection.createTable(orderBLL.findAll()));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        p1c.add(new Label("id:"));
        p1c.add(idClient);
        p1c.add(new Label("name:"));
        p1c.add(nameClient);
        p1c.add(new Label("address:"));
        p1c.add(address);
        p1c.add(addClient);
        p1c.add(updateClient);

        p2c.add(new JLabel("id:"));
        p2c.add(idClientDelete);
        p2c.add(deleteClient);

        p3c.add(clientTable);

        p1p.add(new JLabel("id:"));
        p1p.add(idProduct);
        p1p.add(new JLabel("name:"));
        p1p.add(nameProduct);
        p1p.add(new JLabel("quantity:"));
        p1p.add(quantityProduct);
        p1p.add(new JLabel("price:"));
        p1p.add(priceProduct);
        p1p.add(addProduct);
        p1p.add(updateProduct);

        p2p.add(new JLabel("id:"));
        p2p.add(idProductDelete);
        p2p.add(deleteProduct);

        p3p.add(productTable);

        p1o.add(new JLabel("id:"));
        p1o.add(idOrder);
        p1o.add(new JLabel("clientId:"));
        p1o.add(id_client);
        p1o.add(new JLabel("productId:"));
        p1o.add(id_product);
        p1o.add(new JLabel("quantity:"));
        p1o.add(quantityOrder);
        p1o.add(addOrder);

        p2o.add(orderTable);

    }

    public View(){
        this.setTitle("Order Management");
        this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelClient = new JPanel();
        JPanel panelProduct = new JPanel();
        JPanel orderPanel = new JPanel();

        JTabbedPane tp = new JTabbedPane();

        idClient = new JTextField(3);
        nameClient = new JTextField(10);
        address = new JTextField(10);
        idClientDelete = new JTextField(3);

        idProduct = new JTextField(3);
        nameProduct = new JTextField(10);
        quantityProduct = new JTextField(5);
        priceProduct = new JTextField(5);
        idProductDelete = new JTextField(3);

        idOrder = new JTextField(3);
        id_client = new JTextField(3);
        id_product = new JTextField(3);
        quantityOrder = new JTextField(5);

        update();

        panelClient.add(p1c);
        panelClient.add(p2c);
        panelClient.add(p3c);

        panelClient.setLayout(new BoxLayout(panelClient, BoxLayout.Y_AXIS));

        panelProduct.add(p1p);
        panelProduct.add(p2p);
        panelProduct.add(p3p);

        panelProduct.setLayout(new BoxLayout(panelProduct, BoxLayout.Y_AXIS));

        orderPanel.add(p1o);
        orderPanel.add(p2o);

        tp.add(panelClient);
        tp.add(panelProduct);
        tp.add(orderPanel);

        tp.setTitleAt(0, "Client");
        tp.setTitleAt(1, "Product");
        tp.setTitleAt(2, "Order");

        this.setContentPane(tp);
        this.setVisible(true);
    }
}
