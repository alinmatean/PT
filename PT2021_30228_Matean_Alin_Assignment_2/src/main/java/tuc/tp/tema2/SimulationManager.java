package tuc.tp.tema2;

import tuc.tp.tema2.dataModels.Scheduler;
import tuc.tp.tema2.dataModels.Server;
import tuc.tp.tema2.dataModels.Task;
import tuc.tp.tema2.userInterface.Controller;
import tuc.tp.tema2.userInterface.View;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SimulationManager implements Runnable{
    private int timeLimit;
    private int minProcessingTime;
    private int maxProcessingTime;
    private int maxArrivalTime;
    private int minArrivalTime;
    private int numberOfServers;
    private int numberOfTasks;
    private Scheduler scheduler;
    private Scheduler.SelectionPolicy selectionPolicy;
    private ArrayList<Task> generatedTasks = new ArrayList<Task>();
    private ArrayList<Server> servers = new ArrayList<Server>();
    private String outFile;
    private View simulatorView;

    public SimulationManager(View simulatorView){
        this.simulatorView = simulatorView;
        this.timeLimit = simulatorView.getTimeLimit();
        this.minArrivalTime = simulatorView.getMinArrivalTime();
        this.maxArrivalTime = simulatorView.getMaxArrivalTime();
        this.minProcessingTime = simulatorView.getMinProcessingTime();
        this.maxProcessingTime = simulatorView.getMaxProcessingTime();
        this.numberOfTasks = simulatorView.getNumberOfTasks();
        this.numberOfServers = simulatorView.getNumberOfServers();
        this.outFile = simulatorView.getOutputFile();

        initializeScheduler();
        scheduler.changeStrategy(getSelectionPolicy(simulatorView));

        generateRandomTasks();
    }

    public Scheduler.SelectionPolicy getSelectionPolicy(View simulatorView) {
        if(simulatorView.getStrategy().equals("STRATEGY_QUEUE"))
            selectionPolicy = Scheduler.SelectionPolicy.SHORTEST_QUEUE;
        else
            selectionPolicy = Scheduler.SelectionPolicy.SHORTEST_TIME;
        return selectionPolicy;
    }

    private void generateRandomTasks(){
        Random random = new Random();
        this.generatedTasks = new ArrayList<Task>(numberOfTasks);
        for(int i = 1; i <= numberOfTasks; i++){
            int arrivalTime = random.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime;
            int processingTime = random.nextInt(maxProcessingTime - minProcessingTime) + minProcessingTime;
            generatedTasks.add(new Task(0, arrivalTime, processingTime));
        }
        Collections.sort(generatedTasks);
        for(int i = 0; i < generatedTasks.size(); i++){
            generatedTasks.get(i).setId(i+1);
        }
    }

    @Override
    public void run() {
        FileWriter fileWriter = null;
        StringBuilder result;
        try { fileWriter = new FileWriter(System.getProperty("user.dir") + "\\" + this.outFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try{
            fileWriter.write("Queues Simulator\n");
        }
        catch (IOException e){
            e.printStackTrace();
        }

        int currentTime = 0;

        while (currentTime <= timeLimit){
            boolean ok = true;
            result = new StringBuilder();
            result.append("Time: ").append(currentTime).append("\n");
            System.out.println("Time: " + currentTime);

            while(generatedTasks.size() > 0 && ok){
                ok = false;
                if(generatedTasks.get(0).getArrivalTime() <= currentTime){
                    Task t = generatedTasks.get(0);
                    scheduler.dispatchTask(t);
                    generatedTasks.remove(0);
                    ok = true;
                }
                scheduler.serve();
            }
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            result.append("Waiting: ").append(currentServer(generatedTasks)).append("\n");
            result.append(serverStatus(scheduler.getServers(), currentTime)).append(("\n"));
            System.out.println("Waiting: " + currentServer(generatedTasks));
            System.out.println(serverStatus(scheduler.getServers(), currentTime));
            simulatorView.setEvolution(result.toString());
            try{
                fileWriter.write("\n" + "Time: " + currentTime + "\n" + "Waiting: " + currentServer(generatedTasks) + "\n" + serverStatus(scheduler.getServers(), currentTime));
            }
            catch (IOException e){
                e.printStackTrace();
            }
            currentTime++;
            if(!status())
                break;
        }
        scheduler.stopServers();
        System.out.println("Average waiting time: " + averageTime(scheduler.getServers()));

        try{
            fileWriter.write("\n" + "Average waiting time: " + averageTime(scheduler.getServers()));
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try {
            fileWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void initializeScheduler(){
        this.scheduler = new Scheduler(numberOfServers, numberOfTasks);
        for(int i = 1; i <= numberOfServers; i++){
            Server s = new Server(i);
            scheduler.addServer(s);
        }
    }

    public String serverStatus(ArrayList<Server> serv, int currentTime){
        String s = "";
        for(int i = 0; i < serv.size(); i++){
            s += serv.get(i).toString(currentTime) + "\n";
        }
        return s;
    }

    public String currentServer(ArrayList<Task> clients){
        String res = "";
        for(Task t: clients)
            res += t.toString();
        return res;
    }

    public boolean status(){
        boolean ok = false;
        if(!generatedTasks.isEmpty())
            ok = true;
        for(Server s: scheduler.getServers())
            if(!s.getTasks().isEmpty())
                ok = true;
        return ok;
    }

    public float averageTime(ArrayList<Server> serv){
        int avgTime = 0;
        for(Server s: serv){
            avgTime += s.getWaitingPeriod().get();
        }
        return (float)avgTime/numberOfTasks;
    }

    public static void main(String[] args){
        View view = new View();
        Controller controller = new Controller(view);
    }
}
