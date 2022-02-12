package tuc.tp.tema4.business;

import java.io.Serializable;

/**
 * Client
 */
public class Client implements Serializable {
    private int id;
    private String user;
    private String pass;

    public Client(String user, String pass)
    {
        this.user = user;
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }
    public String getPass(){
        return pass;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

}
