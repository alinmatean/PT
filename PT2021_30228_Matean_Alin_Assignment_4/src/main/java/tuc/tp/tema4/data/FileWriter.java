package tuc.tp.tema4.data;

import tuc.tp.tema4.business.Client;
import tuc.tp.tema4.business.CompositeProduct;
import tuc.tp.tema4.business.MenuItem;
import tuc.tp.tema4.business.Order;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileWriter {
    public static void generateBill(List<MenuItem> orderList){
        File file = null;
        try {
            file = new File("bill" + ".txt");
            java.io.FileWriter fileWriter = new java.io.FileWriter(file);
            int total = 0;
            for (MenuItem menuItem : orderList) {
                if(menuItem instanceof CompositeProduct){
                    fileWriter.write(menuItem.getTitle() + "....." + menuItem.computePrice() + ": ");
                    for(MenuItem m: ((CompositeProduct) menuItem).getItems()){
                        fileWriter.write(m.getTitle() + " ");
                    }
                    fileWriter.write("\n");
                }
                else {
                    fileWriter.write(menuItem.getTitle() + "....." + menuItem.computePrice() + '\n');
                }
                total += menuItem.computePrice();
            }
            fileWriter.write("\n");
            fileWriter.write("TOTAL " + total);
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void generateReport1(HashMap<Order, List<MenuItem>> orders) {
        File file = null;
        try {
            file = new File("report1" + ".txt");
            java.io.FileWriter fileWriter = new java.io.FileWriter(file);

            for (Map.Entry<Order, List<MenuItem>> pair : orders.entrySet()) {
                fileWriter.write("Order id: " +  pair.getKey().getOrderID() + ", ");
                fileWriter.write("client id: " +  pair.getKey().getClientID() + ", ");
                fileWriter.write("date: " +  pair.getKey().getOrderDate() + ", ");
                fileWriter.write("price: " +  pair.getKey().getPrice() + "\n");
                fileWriter.write("Items:");
                for (MenuItem item: pair.getValue()) {
                    fileWriter.write(item.toString() + "\n");
                }
                fileWriter.write("________________________________\n");
            }
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void generateReport2(List<String> items) {
        File file = null;
        try {
            file = new File("report2" + ".txt");
            java.io.FileWriter fileWriter = new java.io.FileWriter(file);

            for (String item: items) {
                fileWriter.write(item + "\n");
            }
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void generateReport3(List<Client> clients) {
        File file = null;
        try {
            file = new File("report3" + ".txt");
            java.io.FileWriter fileWriter = new java.io.FileWriter(file);

            for (Client client: clients) {
                fileWriter.write("Client id: " + client.getId() + ", username: " + client.getUser() + "\n");
            }
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateReport4(Map<String, Long> items){
        File file = null;
        try {
            file = new File("report4" + ".txt");
            java.io.FileWriter fileWriter = new java.io.FileWriter(file);

            for (Map.Entry<String, Long> pair : items.entrySet()) {
                if(pair.getValue() == 1){
                    fileWriter.write(pair.getKey() + " a fost comandat o data!\n");
                }
                else {
                    fileWriter.write(pair.getKey() + " a fost comandat de " + pair.getValue() + " ori!\n");
                }
            }

            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}