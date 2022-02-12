package tuc.tp.tema2.dataModels;

public class Task implements Comparable<Task>{
    private int arrivalTime;
    private int processingTime;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public Task(int id, int arrivalTime, int processingTime){
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

    @Override
    public int compareTo(Task o) {
        return arrivalTime - o.arrivalTime;
    }

    public String toString(){
        return "(" + id + " " + arrivalTime + " " + processingTime + ")";
    }
}
