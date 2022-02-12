package tuc.tp.tema2.dataModels;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;

    private Object lock = new Object();

    private boolean isRunning = true;
    private boolean isClosed = false;

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }
    private int id;
    public Server(int id){
        this.id = id;
        tasks = new LinkedBlockingQueue<Task>();
        waitingPeriod = new AtomicInteger();
    }
    public void addTask(Task newTask){
        waitingPeriod.addAndGet(newTask.getProcessingTime());
        tasks.add(newTask);
    }
    @Override
    public void run() {
        synchronized (lock){
            while(isRunning){
                boolean ok = true;
                if(tasks.size() > 0 && getFirstTask().getProcessingTime() > 0 && ok){
                    try{
                        Thread.sleep(1005);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    getFirstTask().setProcessingTime(getFirstTask().getProcessingTime() - 1);

                    if(getFirstTask().getProcessingTime() == 0){
                        tasks.remove(getFirstTask());
                        if(tasks.isEmpty()){
                            this.close(true);
                        }
                        ok = false;
                    }
                }
            }
        }
    }

    public void close(boolean a) {
        isClosed = a;
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }
    public Task[] getTasksArray(){
        Task[] tasksArray = new Task[tasks.size()];
        tasks.toArray(tasksArray);
        return tasksArray;
    }

    public Task getFirstTask(){
        Task task1 = getTasksArray()[0];
        return task1;
    }

    public void setRunning(boolean b){
        this.isRunning = b;
    }

    public String toString(int currentTime)
    {
        String s = "Queue " + this.id + ": ";
        if(tasks.isEmpty())
            return s + "closed";
        for(Task t: tasks)
        {
            s += t.toString() + ";";
        }
        return s;
    }
}
