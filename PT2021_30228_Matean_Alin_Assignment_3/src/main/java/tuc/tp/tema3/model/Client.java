package tuc.tp.tema3.model;

/**
 * maparea tabelei Client din baza de date
 */
public class Client {
    private int idClient;
    private String name;
    private String address;

    public Client(){

    }

    public Client(int id, String name, String address){
        this.idClient = id;
        this.name = name;
        this.address = address;
    }

    public Client(String name, String address){
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int id) {
        this.idClient = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString(){
        return "Client [id =  " + idClient + ", name =  " + name + ", address =  " + address + "]";
    }
}
