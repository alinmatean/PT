package tuc.tp.tema4.presentation;

import tuc.tp.tema4.business.Client;
import tuc.tp.tema4.business.DeliveryService;
import tuc.tp.tema4.data.Serializator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Login extends JFrame {
    private JFrame loginFrame;
    private JButton loginButton;
    private JButton createAccountButton;
    private JTextField userField;
    private JPasswordField passField;
    private JLabel username;
    private JLabel password;
    DeliveryService deliveryService = new DeliveryService();

    private String getUsername(){
        return userField.getText();
    }

    public Login(){
        this.setTitle("Login wwindow");
        this.setSize(230, 200);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        loginButton = new JButton("LOGIN");
        createAccountButton = new JButton("CREATE ACCOUNT");
        userField = new JTextField(10);
        passField = new JPasswordField(10);
        username = new JLabel("username");
        password = new JLabel("password");
        panel.add(username);
        panel.add(userField);
        panel.add(password);
        panel.add(passField);
        panel.add(loginButton);
        panel.add(createAccountButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(getUsername());
                deliveryService.deserializeClientList("client.ser");
                deliveryService.deserializeOrders("orders.ser");
                char[] password = passField.getPassword();
                String passwordString = String.copyValueOf(password);
                List<Client> clientList = deliveryService.getClientList()
                        .stream()
                        .filter(x -> x.getUser().equals(getUsername()))
                        .collect(Collectors.toList());
                if(passwordString.equals("admin") && getUsername().equals("admin")){
                    if (!clientList.isEmpty())
                        deliveryService.setLoggedInClient(clientList.get(0));
                    AdministratorGUI administratorGUI = new AdministratorGUI(deliveryService);
                }
                else if(!clientList.isEmpty()){
                    if(!clientList.get(0).getPass().equals(passwordString)){
                        JOptionPane.showMessageDialog(loginFrame, "Parola incorecta!");
                        return;
                    }
                    deliveryService.setLoggedInClient(clientList.get(0));
                    ClientGUI clientGUI = new ClientGUI(deliveryService);
                    EmployeeGUI employeeGUI = new EmployeeGUI(deliveryService);
                    deliveryService.addObserver(employeeGUI);
                }
                else {
                    JOptionPane.showMessageDialog(loginFrame, "Contul nu exista!");
                }
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryService.deserializeClientList("client.ser");
                char[] password = passField.getPassword();
                String passwordString = String.copyValueOf(password);
                Client client = new Client(getUsername(), passwordString);
                client.setId(deliveryService.getMaxClientId() + 1);
                deliveryService.registerClient(client);
                if(passField.equals("") || getUsername().equals(""))
                    JOptionPane.showMessageDialog(loginFrame, "Introduceti username si password!");
                else
                    JOptionPane.showMessageDialog(loginFrame, "Contul a fost creat cu succes!");
                Serializator.serialize(deliveryService.getClientList(), "client.ser");
            }
        });

        this.setContentPane(panel);
        this.setVisible(true);
    }

}
