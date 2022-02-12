package tuc.tp.tema2.dataModels;

import tuc.tp.tema2.logic.Strategy;
import tuc.tp.tema2.logic.StrategyQueue;
import tuc.tp.tema2.logic.StrategyTime;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    public ArrayList<Server> getServers() {
        return (ArrayList<Server>) servers;
    }

    private List<Server> servers;

    private int maxNoServers;
    private int maxCustomersPerServer;
    private Strategy strategy;

    public enum SelectionPolicy{
        SHORTEST_QUEUE, SHORTEST_TIME;
    }

    public Scheduler(int maxNoServers, int maxCustomersPerServer){
        this.maxCustomersPerServer = maxCustomersPerServer;
        this.maxNoServers = maxNoServers;
        servers = new ArrayList<Server>(maxNoServers);
    }

    public void addServer(Server s){
        servers.add(s);
    }

    public void serve(){
        for(Server s: servers){
            Thread t = new Thread(s);
            t.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy){
        if(policy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new StrategyQueue();
        }
        if(policy == SelectionPolicy.SHORTEST_TIME){
            strategy = new StrategyTime();
        }
    }

    public void dispatchTask(Task t){
        strategy.addTask(servers, t);
    }

    public void stopServers(){
        for(Server s: servers){
            s.setRunning(false);
        }
    }

}
