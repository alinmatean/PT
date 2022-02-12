package tuc.tp.tema4.presentation;

import tuc.tp.tema4.business.BaseProduct;
import tuc.tp.tema4.business.CompositeProduct;
import tuc.tp.tema4.business.DeliveryService;
import tuc.tp.tema4.business.MenuItem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * AdminGUI
 */
public class AdministratorGUI extends JFrame implements ActionListener {
    private JPanel adminWindow = new JPanel();

    private JButton addButton = new JButton(" Adauga produs de baza");
    private JButton addButton2 = new JButton("Adauga produs compus");
    private JButton addToComposite = new JButton("Adauga la produs compus");
    private JButton editButton = new JButton("Editeaza produs");
    private JButton deleteButton = new JButton("Sterge produs");
    private JButton raport1Button = new JButton("Generare raport 1");
    private JButton raport2Button = new JButton("Generare raport 2");
    private JButton raport3Button = new JButton("Generare raport 3");
    private JButton raport4Button = new JButton("Generare raport 4");
    private JComboBox menuItemsCombo;

    private JPanel panel;
    private JPanel panelAddItems;
    private JPanel reportPanel;
    private JPanel r1panel;
    private JPanel r2panel;
    private JPanel r3panel;
    private JPanel r4panel;
    private JTextField title;
    private JTextField rating;
    private JTextField calories;
    private JTextField protein;
    private JTextField fat;
    private JTextField sodium;
    private JTextField price;
    private JTextField startHour;
    private JTextField endHour;
    private JTextField productsOrdered;
    private JTextField orderTimes;
    private JTextField amount;
    private JTextField specificDate;

    DeliveryService deliveryService = new DeliveryService();
    private JTable menuTable;
    private JScrollPane scrollPane;

    public AdministratorGUI(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        this.deliveryService.deserializeMenuItemList("delivery.ser");

        this.setTitle("Administrator window");
        this.setSize(new Dimension(800, 700));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        menuTable = new JTable();
        menuTable.setModel(showMenu());
        scrollPane = new JScrollPane(menuTable);

        panel = new JPanel();
        panelAddItems = new JPanel();
        reportPanel = new JPanel();
        title = new JTextField(10);
        rating = new JTextField(10);
        calories = new JTextField(10);
        protein = new JTextField(10);
        fat = new JTextField(10);
        sodium = new JTextField(10);
        price = new JTextField(10);
        //menuItemsCombo = new JComboBox();
        //menuItemsCombo.setPreferredSize(new Dimension(100, 20));

        panelAddItems.add(new JLabel("Title"));
        panelAddItems.add(title);
        panelAddItems.add(new JLabel("Rating"));
        panelAddItems.add(rating);
        panelAddItems.add(new JLabel("Calories"));
        panelAddItems.add(calories);
        panelAddItems.add(new JLabel("Proteins"));
        panelAddItems.add(protein);
        panelAddItems.add(new JLabel("Fat"));
        panelAddItems.add(fat);
        panelAddItems.add(new JLabel("Sodium"));
        panelAddItems.add(sodium);
        panelAddItems.add(new JLabel("Price"));
        panelAddItems.add(price);
        panelAddItems.add(addButton);
        panelAddItems.add(addButton2);
        panelAddItems.add(editButton);
        panelAddItems.add(deleteButton);
        panelAddItems.add(addToComposite);
        addButton.addActionListener(this);
        addButton2.addActionListener(this);
        editButton.addActionListener(this);
        deleteButton.addActionListener(this);
        addToComposite.addActionListener(this);
        raport1Button.addActionListener(this);
        raport2Button.addActionListener(this);
        raport3Button.addActionListener(this);
        raport4Button.addActionListener(this);
        //panelAddItems.add(menuItemsCombo);
        panelAddItems.setLayout(new BoxLayout(panelAddItems, BoxLayout.Y_AXIS));

        panel.add(panelAddItems);
        startHour = new JTextField(3);
        endHour = new JTextField(3);
        productsOrdered = new JTextField(5);
        orderTimes = new JTextField(3);
        amount = new JTextField(3);
        specificDate = new JTextField(10);

        r1panel = new JPanel();
        r2panel = new JPanel();
        r3panel = new JPanel();
        r4panel = new JPanel();

        r1panel.add(new JLabel("Start hour:"));
        r1panel.add(startHour);
        r1panel.add(new JLabel("End hour:"));
        r1panel.add(endHour);
        r1panel.add(raport1Button);

        r2panel.add(new JLabel("Produse comandate mai mult de: "));
        r2panel.add(productsOrdered);
        r2panel.add(new JLabel("ori"));
        r2panel.add(raport2Button);

        r3panel.add(new JLabel("Clienti care au comandat de mai mult de: "));
        r3panel.add(orderTimes);
        r3panel.add(new JLabel("ori si valoarea comenzii a fost mai mare de: "));
        r3panel.add(amount);
        r3panel.add(raport3Button);

        r4panel.add(new JLabel("Produsele comandate in data de:(format ziua:luna)"));
        r4panel.add(specificDate);
        r4panel.add(raport4Button);

        reportPanel.add(r1panel);
        reportPanel.add(r2panel);
        reportPanel.add(r3panel);
        reportPanel.add(r4panel);
        reportPanel.setLayout(new BoxLayout(reportPanel, BoxLayout.Y_AXIS));

        adminWindow.add(scrollPane, BorderLayout.WEST);
        adminWindow.add(panel, BorderLayout.EAST);
        adminWindow.add(reportPanel, BorderLayout.SOUTH);

        this.setLayout(new BoxLayout(adminWindow, BoxLayout.X_AXIS));
        this.setContentPane(adminWindow);
        this.setVisible(true);
    }

    public DefaultTableModel showMenu() {

        String[] columns = new String[]{"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
        deliveryService.getMenuItemList();
        int noRows = deliveryService.getMenuItemList().size();
        String[][] data = new String[noRows][7];
        int rowIndex = 0;
        for (MenuItem menuItem : deliveryService.getMenuItemList()) {
            data[rowIndex][0] = menuItem.getTitle();
            data[rowIndex][1] = String.valueOf(menuItem.computeRating());
            data[rowIndex][2] = String.valueOf(menuItem.computeCalories());
            data[rowIndex][3] = String.valueOf(menuItem.computeProteins());
            data[rowIndex][4] = String.valueOf(menuItem.computeFat());

            data[rowIndex][5] = String.valueOf(menuItem.computeSodium());
            data[rowIndex][6] = String.valueOf(menuItem.computePrice());
            rowIndex++;
        }
        DefaultTableModel defaultTableModel = new DefaultTableModel(data, columns);
        return defaultTableModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            //String menuLine = (String) menuTable.getValueAt(menuTable.getSelectedRow(), 0);
            //fillComboBox(menuItemsCombo);
            deliveryService.addProduct(getTitle(), getRating(), getCalories(), getProtein(), getFat(), getSodium(), getPrice());
//            deliveryService.getMenuItemList().add(new BaseProduct(getTitle(), getRating(), getCalories(), getProtein(), getFat(), getSodium(), getPrice()));
            menuTable.setModel(showMenu());
        }
        if (e.getSource() == addButton2) {
            deliveryService.addProduct(getTitle());
            menuTable.setModel(showMenu());
        }
        if (e.getSource() == editButton) {
            int row = menuTable.getSelectedRow();
            String value = menuTable.getValueAt(row, 0).toString();
            deliveryService.editProduct(deliveryService.getByTitle(value), getPrice());
            menuTable.setModel(showMenu());
        }
        if (e.getSource() == deleteButton) {
            int row = menuTable.getSelectedRow();
            String value = menuTable.getValueAt(row, 0).toString();
            deliveryService.deleteProduct(deliveryService.getByTitle(value));
            menuTable.setModel(showMenu());
        }
        if (e.getSource() == addToComposite) {
            int row = menuTable.getSelectedRow();
            String value = menuTable.getValueAt(row, 0).toString();
            deliveryService.addToComposite(deliveryService.getByTitle(value), getTitle());
            menuTable.setModel(showMenu());
        }
        if (e.getSource() == raport1Button) {
            if (getStartHour() == null || getEndHour() == null)
                showErrorPane("Va rugam adaugati o ora de start si de sfarsit");
            else {
                deliveryService.generateReport1(getStartHour(), getEndHour());
                JOptionPane.showMessageDialog(adminWindow, "Raportul 1 a fost generat");
            }
        }
        if (e.getSource() == raport2Button) {
            if (getProductsOrdered() == null)
                showErrorPane("Va rugam adaugati o valoare");
            else {
                deliveryService.generateReport2(getProductsOrdered());
                JOptionPane.showMessageDialog(adminWindow, "Raportul 2 a fost generat");

            }
        }
        if (e.getSource() == raport3Button) {
            if (getOrderTimes() == null || getAmount() == null)
                showErrorPane("Va rugam adaugati valori");
            else {
                deliveryService.generateReport3(getOrderTimes(), getAmount());
                JOptionPane.showMessageDialog(adminWindow, "Raportul 3 a fost generat");

            }
        }
        if (e.getSource() == raport4Button) {
            if (getSpecificDate() == null)
                showErrorPane("Va rugam introduceti o data");
            else {
                deliveryService.generateReport4(getSpecificDate());
                JOptionPane.showMessageDialog(adminWindow, "Raportul 4 a fost generat");
            }
        }
    }

    private void showErrorPane(String message) {
        JOptionPane.showMessageDialog(adminWindow, message);
    }

    public String getTitle() {
        return title.getText();
    }

    public Double getRating() {
        return Double.parseDouble(rating.getText());
    }

    public Integer getCalories() {
        return Integer.parseInt(calories.getText());
    }

    public Integer getProtein() {
        return Integer.parseInt(protein.getText());
    }

    public Integer getFat() {
        return Integer.parseInt(fat.getText());
    }

    public Integer getSodium() {
        return Integer.parseInt(sodium.getText());
    }

    public Integer getPrice() {
        return Integer.parseInt(price.getText());
    }

    public Integer getStartHour() {
        if (!startHour.getText().equals(""))
            return Integer.parseInt(startHour.getText());
        return null;
    }

    public Integer getEndHour() {
        if (!endHour.getText().equals(""))
            return Integer.parseInt(endHour.getText());
        return null;
    }

    public Integer getProductsOrdered() {
        if (!productsOrdered.getText().equals(""))
            return Integer.parseInt(productsOrdered.getText());
        return null;
    }

    public Integer getOrderTimes() {
        if (!orderTimes.getText().equals(""))
            return Integer.parseInt(orderTimes.getText());
        return null;
    }

    public Integer getAmount() {
        if (!amount.getText().equals(""))
            return Integer.parseInt(amount.getText());
        return null;
    }

    public String getSpecificDate() {
        if (!specificDate.getText().equals(""))
            return specificDate.getText();
        return null;
    }
}

