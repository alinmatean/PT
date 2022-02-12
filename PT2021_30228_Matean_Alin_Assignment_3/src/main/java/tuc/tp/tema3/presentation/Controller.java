package tuc.tp.tema3.presentation;

import tuc.tp.tema3.bll.ClientBLL;
import tuc.tp.tema3.bll.OrderBLL;
import tuc.tp.tema3.bll.ProductBLL;
import tuc.tp.tema3.model.Client;
import tuc.tp.tema3.model.Orders;
import tuc.tp.tema3.model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Controller {
    private View view;

    public Controller(final View view){
        this.view = view;
        final ClientBLL clientBLL = new ClientBLL();
        final ProductBLL productBLL = new ProductBLL();
        final OrderBLL orderBLL = new OrderBLL();

        view.getAddClient().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = view.getIdClient();
                String name = view.getNameClient();
                String address = view.getAddress();
                Client client = new Client(id, name, address);
                try {
                    clientBLL.insertClient(client);
                    view.update();
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        view.getDeleteClient().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = view.getIdClientDelete();
                try {
                    clientBLL.deleteClient(id);
                    view.update();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        view.getUpdateClient().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = view.getIdClient();
                String name = view.getNameClient();
                String address = view.getAddress();
                Client client = new Client(id, name, address);
                try {
                    clientBLL.updateClient(client);
                    view.update();
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        view.getAddProduct().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = view.getIdProduct();
                String name = view.getNameProduct();
                int quantity = view.getQuantityProduct();
                double price = view.getPriceProduct();
                Product product = new Product(id, name, quantity, price);
                try {
                    productBLL.insertProduct(product);
                    view.update();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        view.getDeleteProduct().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = view.getIdProductDelete();
                try {
                    productBLL.deleteProduct(id);
                    view.update();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        view.getUpdateProduct().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = view.getIdProduct();
                String name = view.getNameProduct();
                int quantity = view.getQuantityProduct();
                double price = view.getPriceProduct();
                Product product = new Product(id, name, quantity, price);
                try {
                    productBLL.updateProduct(product);
                    view.update();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        view.getAddOrder().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idOrder = view.getIdOrder();
                int idc = view.getIdClientOrder();
                int idp = view.getIdProductOrder();
                int q = view.getQuantityOrder();

                Orders orders = new Orders(idOrder, idc, idp, q, 0);
                if(productBLL.getQuantity(idp) < q){
                    JOptionPane.showMessageDialog(view.p1o, "Stoc insuficient");
                }
                else {
                    try {
                        orderBLL.insertOrder(orders);
                        orderBLL.updateTotal(idOrder, productBLL.getPrice(idp) * q);
                        view.update();
                        generateBill(idOrder, idc, idp);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
    public void generateBill(int idOrder, int idClient, int idProduct){
        ClientBLL clientBLL = new ClientBLL();
        ProductBLL productBLL = new ProductBLL();
        OrderBLL orderBLL = new OrderBLL();
        FileWriter fileWriter = null;
        String fileName = "Order" + idOrder + ".txt";
        try { fileWriter = new FileWriter(System.getProperty("user.dir") + "\\" + fileName);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        try {
            fileWriter.write("Comanda: " + idOrder + '\n');
            fileWriter.write("Nume client: " + clientBLL.getName(idClient) + '\n');
            fileWriter.write("Produs: " + productBLL.getName(idProduct) + '\n');
            fileWriter.write("Cantitate: " + view.getQuantityOrder() + '\n');
            fileWriter.write("Total: " + orderBLL.getTotal(idOrder));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        try {
            fileWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
