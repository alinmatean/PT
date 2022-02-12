package tuc.tp.tema4.business;

import tuc.tp.tema4.data.FileWriter;
import tuc.tp.tema4.data.Serializator;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * DeliveryService
 * @invariant isWellFormed()
 */

public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {

    private HashMap<Order, List<MenuItem>> orders = new HashMap<>();
    private List<MenuItem> menuItemList = new ArrayList<>();
    private List<Client> clientList = new ArrayList<>();

    boolean firstUse = false;

    //current order
    private Client loggedInClient;
    private List<MenuItem> currentOrder = new ArrayList<>();
    private int price = 0;

    /**
     * adauga un produs de baza
     * @param title
     * @param rating
     * @param calories
     * @param proteins
     * @param fat
     * @param sodium
     * @param price
     */
    @Override
    public void addProduct(String title, Double rating, Integer calories, Integer proteins, Integer fat, Integer sodium, Integer price) {
        assert title != null;
        assert price > 0;
        menuItemList.add(new BaseProduct(title, rating, calories, proteins, fat, sodium, price));
        Serializator.serialize(menuItemList, "delivery.ser");
        assert isWellFormed();
    }

    /**
     * adauga un produs compus
     * @param title
     */
    @Override
    public void addProduct(String title) {
        assert  title != null;
        menuItemList.add(new CompositeProduct(title));
        Serializator.serialize(menuItemList, "delivery.ser");
        assert isWellFormed();
    }

    /**
     * adauga un produs la un produs compus
     * @param menuItem
     * @param title
     */
    @Override
    public void addToComposite(MenuItem menuItem, String title) {
        assert isWellFormed() && title != null;
        for (MenuItem menuItem1 : menuItemList) {
            if (menuItem1.getTitle().equals(title)) {
                if (menuItem1 instanceof CompositeProduct) {
                    ((CompositeProduct) menuItem1).addItem(menuItem);
                }
            }
        }
        Serializator.serialize(menuItemList, "delivery.ser");
    }

    /**
     * verifica sa fie bine formata clasa
     * @return true daca clasa e bine formata
     */
    @Override
    public boolean isWellFormed() {
        for(MenuItem menuItem: menuItemList){
            if(menuItem instanceof BaseProduct)
                if(menuItem.computePrice() <= 0){
                    return false;
                }
        }
        for(Order order: orders.keySet()){
            if(order == null)
                return false;
        }
        if(menuItemList == null){
            return false;
        }
        return true;
    }

    /**
     * sterge un produs
     * @param product
     */
    @Override
    public void deleteProduct(MenuItem product) {
        assert product != null;
        for (MenuItem menuItem : menuItemList) {
            if (menuItem instanceof CompositeProduct) {
                ((CompositeProduct) menuItem).removeItem(product);
            }
        }
        menuItemList.remove(product);
        Serializator.serialize(menuItemList, "delivery.ser");
        assert isWellFormed();
    }

    /**
     * editeaza un produs
     * @param menuItem
     * @param price
     */
    @Override
    public void editProduct(MenuItem menuItem, int price) {
        assert menuItem != null && price != 0;
        for (MenuItem menuItem1 : menuItemList) {
            if (menuItem1.getTitle().equals(menuItem.getTitle())) {
                if (menuItem1 instanceof BaseProduct) {
                    ((BaseProduct) menuItem1).setPrice(price);
                }
            }
        }
        assert isWellFormed();
        Serializator.serialize(menuItemList, "delivery.ser");
    }

    /**
     * importa produsele din csv
     */
    public void importProducts() {
        try {
            File input = new File("products.csv");
            InputStream inputStream = new FileInputStream(input);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            menuItemList = bufferedReader.lines().skip(1).map(mapToItem).filter(distinctByKey(MenuItem::getTitle)).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Function<String, MenuItem> mapToItem = (line) -> {
        String[] stringsLine = line.split(",");

        return new BaseProduct(stringsLine[0], Double.parseDouble(stringsLine[1]), Integer.parseInt(stringsLine[2]), Integer.parseInt(stringsLine[3]), Integer.parseInt(stringsLine[4]), Integer.parseInt(stringsLine[5]), Integer.parseInt(stringsLine[6]));

    };

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * @return lista de menuItems
     */
    public List<MenuItem> getMenuItemList() {
        if (firstUse == false) {
            //importProducts();
            firstUse = true;
        }
        return menuItemList;
    }

    /**
     *
     * @param title
     * @return produsul corespunzator titlului
     */
    public MenuItem getByTitle(String title) {
        for (MenuItem menuItem : menuItemList) {
            if (menuItem.getTitle().equals(title))
                return menuItem;
        }
        return null;
    }

    /**
     *
     * @return lista clientilor intregistrati
     */
    public List<Client> getClientList() {
        return clientList;
    }

    /**
     *
     * @return clientul curent
     */
    public Client getLoggedInClient() {
        return loggedInClient;
    }

    /**
     * seteaza clientul curent
     * @param loggedInClient
     */
    public void setLoggedInClient(Client loggedInClient) {
        this.loggedInClient = loggedInClient;
    }

    public void deserializeMenuItemList(String filename) {
        this.menuItemList = (List<MenuItem>) Serializator.deserialize(filename);
    }

    public void deserializeClientList(String filename) {
        this.clientList = (List<Client>) Serializator.deserialize(filename);
    }

    public void deserializeOrders(String filename) {
        File file = new File(filename);
        if (file.exists() && !file.isDirectory()) {
            this.orders = (HashMap<Order, List<MenuItem>>) Serializator.deserialize(filename);
        }
    }

    /**
     *realizeaza inregistrarea unui nou client
     * @param client
     */
    public void registerClient(Client client) {
        if (clientList == null)
            clientList = new ArrayList<>();
        this.clientList.add(client);
    }

    /**
     * @return id-ul ultmului client inregistrat
     */
    public int getMaxClientId() {
        int max = 0;
        if (clientList != null)
            for (Client client : clientList)
                if (client.getId() > max)
                    max = client.getId();

        return max;
    }

    /**
     *
     * @return id-ul ultimei comenzi inregistrate
     */
    public int getMaxOrderId() {
        int max = 0;
        if (orders != null)
            for (Order order : orders.keySet())
                if (order.getOrderID() > max)
                    max = order.getOrderID();

        return max;
    }

    /**
     *extrage produsele care corespund criteriului
     * @param category
     * @param searchCriteria
     * @return lista de menuItems corespunzatoare
     */
    public List<MenuItem> getFilteredList(String category, String searchCriteria) {
        //"title", "rating", "calories", "protein", "fat", "sodium", "price"
        switch (category) {
            case "title":
                return menuItemList.stream()
                        .filter(menuItem -> menuItem.getTitle().contains(searchCriteria))
                        .collect(Collectors.toList());
            case "rating":
                return menuItemList.stream()
                        .filter(menuItem -> menuItem.computeRating() == Double.parseDouble(searchCriteria))
                        .collect(Collectors.toList());
            case "calories":
                return menuItemList.stream()
                        .filter(menuItem -> menuItem.computeCalories() == Integer.parseInt(searchCriteria))
                        .collect(Collectors.toList());
            case "protein":
                return menuItemList.stream()
                        .filter(menuItem -> menuItem.computeProteins() == Integer.parseInt(searchCriteria))
                        .collect(Collectors.toList());
            case "fat":
                return menuItemList.stream()
                        .filter(menuItem -> menuItem.computeFat() == Integer.parseInt(searchCriteria))
                        .collect(Collectors.toList());
            case "sodium":
                return menuItemList.stream()
                        .filter(menuItem -> menuItem.computeSodium() == Integer.parseInt(searchCriteria))
                        .collect(Collectors.toList());
            case "price":
                return menuItemList.stream()
                        .filter(menuItem -> menuItem.computePrice() == Integer.parseInt(searchCriteria))
                        .collect(Collectors.toList());
            default:
                return null;
        }
    }

    /**
     * adauga un nou produs la comanda curenta
     * @param item
     */
    public void addToOrder(MenuItem item) {
        price += item.computePrice();
        currentOrder.add(item);
    }

    /**
     *
     * @return lista cu produsele din comanda curenta
     */
    public List<MenuItem> getCurrentOrder() {
        return currentOrder;
    }

    /**
     * plaseaza o noua comanda si notifica angajatul
     */
    public void placeOrder() {
        Date date = new Date();
        Order order = new Order(getMaxOrderId() + 1, loggedInClient.getId(), date, price);
        orders.put(order, currentOrder);
//        System.out.println(order.getOrderDate());
//        SimpleDateFormat sdf = new SimpleDateFormat("d:M");
//        System.out.println(sdf.format(order.getOrderDate()));
        Serializator.serialize(orders, "orders.ser");
        setChanged();
        notifyObservers(currentOrder);
        currentOrder = new ArrayList<>();
        price = 0;
    }

    /**
     *
     * @return toate comenzile procesate
     */
    public HashMap<Order, List<MenuItem>> getOrders() {
        return orders;
    }

    /**
     * genereaza raportul de tip 1
     * @param min
     * @param max
     */
    public void generateReport1(int min, int max) {
        HashMap<Order, List<MenuItem>> filteredOrders =  new HashMap<>();
        orders.entrySet().stream()
                .filter(pair -> {
                    pair.getKey().getOrderDate();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH");
                    if (Integer.parseInt(sdf.format(pair.getKey().getOrderDate())) <= max &&
                        Integer.parseInt(sdf.format(pair.getKey().getOrderDate())) >= min)
                        return true;

                    return false;
                })
                .forEach(pair -> filteredOrders.put(pair.getKey(), pair.getValue()));
        FileWriter.generateReport1(filteredOrders);
        System.out.println(orders);
    }

    /**
     * genereaza raportul de tip 2
     * @param amount
     */
    public void generateReport2(int amount) {
        List<String> items =
                orders.values().stream()
                        .flatMap(List::stream)
                        .map(item -> item.getTitle())
                        .collect(Collectors.toList()).stream()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream()
                        .filter(pair -> pair.getValue() >= amount)
                        .map(pair -> pair.getKey())
                        .collect(Collectors.toList());
        FileWriter.generateReport2(items);
    }

    /**
     * genereaza raportul de tip 3
     * @param orderTimes
     * @param amount
     */
    public void generateReport3(int orderTimes, int amount) {
       List<Integer> filteredClientsId = orders.keySet().stream()
                .collect(Collectors.groupingBy(
                        Order::getClientID,
                        Collectors.mapping(
                                Order::getPrice, Collectors.toList()
                        )
                )).entrySet().stream()
                .filter(pair -> {
                    int total = 0;
                    for (int itemPrice: pair.getValue())
                        total += itemPrice;
                    if (total >= amount && pair.getValue().size() >= orderTimes)
                        return true;
                    return false;
                })
                .map(pair -> pair.getKey())
                .collect(Collectors.toList());

       List<Client> filteredClients = clientList.stream()
               .filter(client -> filteredClientsId.contains(client.getId()))
               .collect(Collectors.toList());

        FileWriter.generateReport3(filteredClients);
    }

    /**
     * genereaza raportul de tip 4
     * @param data
     */
    public void generateReport4(String data) {

        Map<String, Long> items = orders.entrySet().stream()
                .filter(pair -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("d:M");
                    if (sdf.format(pair.getKey().getOrderDate()).equals(data))
                        return true;
                    return false;
                })
                .map(Map.Entry::getValue)
                .flatMap(List::stream)
                .map(MenuItem::getTitle)
                .collect(Collectors.toList()).stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        FileWriter.generateReport4(items);
    }
}
