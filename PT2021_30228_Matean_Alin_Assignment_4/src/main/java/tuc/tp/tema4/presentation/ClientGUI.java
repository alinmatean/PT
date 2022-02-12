package tuc.tp.tema4.presentation;

import tuc.tp.tema4.business.Client;
import tuc.tp.tema4.business.DeliveryService;
import tuc.tp.tema4.business.MenuItem;
import tuc.tp.tema4.business.Order;
import tuc.tp.tema4.data.FileWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientGUI extends JFrame implements ActionListener {

    private  JPanel clientWindow = new JPanel();
    private JPanel panelButtons = new JPanel(new GridLayout(2, 1, 10, 10));
    private DeliveryService deliveryService = new DeliveryService();

    private JTable menuTable;
    private JScrollPane scrollPane;
    private JComboBox searchCategory;
    String[] criteriaString = { "title", "rating", "calories", "protein", "fat", "sodium", "price" };

    private JButton viewAllItemsButton = new JButton("Toate produsele");
    private JButton searchButton = new JButton("Cauta");
    private JButton addToOrder = new JButton("Adauga la comanda");
    private JButton placeOrder = new JButton("Plaseaza comanda");

    private JTextField searchField;

    public ClientGUI(DeliveryService deliveryService){
        this.deliveryService = deliveryService;
        this.deliveryService.deserializeMenuItemList("delivery.ser");
        this.setTitle("Client window");
        this.setSize(new Dimension(800, 500));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        menuTable = new JTable();
        menuTable.setModel(showMenu());
        scrollPane = new JScrollPane(menuTable);
        searchField = new JTextField(10);

        searchCategory = new JComboBox(criteriaString);
        searchCategory.setPreferredSize(new Dimension(70, 24));

        viewAllItemsButton.addActionListener(this);
        searchButton.addActionListener(this);
        addToOrder.addActionListener(this);
        placeOrder.addActionListener(this);

        panelButtons.add(viewAllItemsButton);
        panelButtons.add(new JLabel("Alege criteriul de cautare:"));
        panelButtons.add(searchCategory);
        panelButtons.add(searchField);
        panelButtons.add(searchButton);
        panelButtons.add(addToOrder);
        panelButtons.add(placeOrder);
        panelButtons.setLayout(new GridLayout(10, 1, 10, 10));

        clientWindow.add(scrollPane, BorderLayout.WEST);
        clientWindow.add(panelButtons, BorderLayout.EAST);

        this.setLayout(new BoxLayout(clientWindow, BoxLayout.X_AXIS));
        this.setContentPane(clientWindow);
        this.setVisible(true);
    }

    public DefaultTableModel showMenu(){
        String[] columns = new String[]{"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
        deliveryService.getMenuItemList();
        int noRows = deliveryService.getMenuItemList().size();
        String[][] data = new String[noRows][7];
        int rowIndex = 0;
        for(MenuItem menuItem: deliveryService.getMenuItemList()){
            data[rowIndex][0] = menuItem.getTitle();
            data[rowIndex][1] = String.valueOf(menuItem.computeRating());
            data[rowIndex][2] = String.valueOf(menuItem.computeCalories());
            data[rowIndex][3] = String.valueOf(menuItem.computeProteins());
            data[rowIndex][4] = String.valueOf(menuItem.computeFat());

            data[rowIndex][5] = String.valueOf(menuItem.computeSodium());
            data[rowIndex][6] = String.valueOf(menuItem.computePrice());
            rowIndex++;
        }
        DefaultTableModel defaultTableModel =  new DefaultTableModel(data, columns);
        return defaultTableModel;
    }

    public DefaultTableModel showFiltered(){
        String[] columns = new String[]{"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
        List<MenuItem> menuItemList = deliveryService.getFilteredList(getSearchCategory(), getSearchField());
        int noRows = menuItemList.size();
        String[][] data = new String[noRows][7];
        int rowIndex = 0;
        for(MenuItem menuItem: menuItemList){
            data[rowIndex][0] = menuItem.getTitle();
            data[rowIndex][1] = String.valueOf(menuItem.computeRating());
            data[rowIndex][2] = String.valueOf(menuItem.computeCalories());
            data[rowIndex][3] = String.valueOf(menuItem.computeProteins());
            data[rowIndex][4] = String.valueOf(menuItem.computeFat());

            data[rowIndex][5] = String.valueOf(menuItem.computeSodium());
            data[rowIndex][6] = String.valueOf(menuItem.computePrice());
            rowIndex++;
        }
        DefaultTableModel defaultTableModel =  new DefaultTableModel(data, columns);
        return defaultTableModel;
    }

    public String getSearchCategory(){
        return searchCategory.getSelectedItem().toString();
    }

    public String getSearchField(){
        return searchField.getText();
//        MenuItem item = deliveryService.getByTitle(getSelected.getTitle())
       // deliveryService.addToOrder(item);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewAllItemsButton){
            menuTable.setModel(showMenu());
        }
        if(e.getSource() == searchButton){
            menuTable.setModel(showFiltered());
        }
        if(e.getSource() == addToOrder){
            int row = menuTable.getSelectedRow();
            String value = menuTable.getValueAt(row, 0).toString();
            MenuItem menuItem = deliveryService.getByTitle(value);
            deliveryService.addToOrder(menuItem);
        }
        if(e.getSource() == placeOrder){
            FileWriter.generateBill(deliveryService.getCurrentOrder());
            deliveryService.placeOrder();
            System.out.println(deliveryService.getOrders().entrySet());
            JOptionPane.showMessageDialog(clientWindow, "Comanda a fost plasata!");
            for (Order order: deliveryService.getOrders().keySet())
                System.out.println(order.getOrderID() + " " + order.getClientID() + " " + order.getOrderDate() + " " +  order.getPrice());
        }
    }
}

