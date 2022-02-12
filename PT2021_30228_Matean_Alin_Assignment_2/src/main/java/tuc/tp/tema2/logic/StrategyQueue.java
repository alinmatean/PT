package tuc.tp.tema2.logic;

import tuc.tp.tema2.dataModels.Server;
import tuc.tp.tema2.dataModels.Task;

import java.util.List;

public class StrategyQueue implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task t) {
        Server bestServer = servers.get(0);
        for(Server server1: servers) {
            if (server1.getTasks().size() < bestServer.getTasks().size()){
                bestServer = server1;
            }
        }
        bestServer.addTask(t);
    }
}
