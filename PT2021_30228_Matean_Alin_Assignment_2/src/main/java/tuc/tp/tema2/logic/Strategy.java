package tuc.tp.tema2.logic;

import tuc.tp.tema2.dataModels.Server;
import tuc.tp.tema2.dataModels.Task;

import java.util.List;

public interface Strategy {
    public void addTask(List<Server> servers, Task t);
}
