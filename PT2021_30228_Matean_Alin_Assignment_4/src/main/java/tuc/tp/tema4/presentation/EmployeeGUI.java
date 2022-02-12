package tuc.tp.tema4.presentation;

import tuc.tp.tema4.business.DeliveryService;
import tuc.tp.tema4.business.MenuItem;
import tuc.tp.tema4.business.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class EmployeeGUI extends JFrame implements Observer {
    private JPanel employeePanel = new JPanel();
    private JTable ordersTable;
    private JScrollPane scrollPane;
    private DeliveryService deliveryService = new DeliveryService();

    public EmployeeGUI(DeliveryService deliveryService){
        this.deliveryService = deliveryService;
        this.setTitle("Employee window");
        this.setSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        repaintOrder();

        this.setContentPane(employeePanel);
        this.setVisible(true);
    }

    public DefaultTableModel showOrders(){
        String[] columns = new String[]{"orderID", "clientID", "orderDate", "orderPrice"};
        deliveryService.getOrders();
        int noRows = deliveryService.getOrders().size();
        String[][] data = new String[noRows][4];
        int rowIndex = 0;
        for(Order order: deliveryService.getOrders().keySet()){
            data[rowIndex][0] = String.valueOf(order.getOrderID());
            data[rowIndex][1] = String.valueOf(order.getClientID());
            data[rowIndex][2] = String.valueOf(order.getOrderDate());
            data[rowIndex][3] = String.valueOf(order.getPrice());
            rowIndex++;
        }
        DefaultTableModel defaultTableModel =  new DefaultTableModel(data, columns);
        return defaultTableModel;
    }

    public void repaintOrder(){
        employeePanel = new JPanel();
        ordersTable = new JTable();
        ordersTable.setModel(showOrders());
        scrollPane = new JScrollPane(ordersTable);
        employeePanel.add(scrollPane);
        this.setContentPane(employeePanel);
        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaintOrder();
        JOptionPane.showMessageDialog(employeePanel, "A fost plasata o comanda noua!");
    }
}
