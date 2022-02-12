package tuc.tp.tema2.userInterface;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame{

    public int getMinProcessingTime(){
        return Integer.parseInt(minProcessingTime.getText());
    }

    public int getMaxProcessingTime(){
        return Integer.parseInt(maxProcessingTime.getText());
    }

    public int getMaxArrivalTime(){
        return Integer.parseInt(maxArrivalTime.getText());
    }

    public int getMinArrivalTime(){
        return Integer.parseInt(minArrivalTime.getText());
    }

    public int getNumberOfServers(){
        return Integer.parseInt(numberOfServers.getText());
    }

    public int getNumberOfTasks(){
        return Integer.parseInt(numberOfTasks.getText());
    }

    public int getTimeLimit(){
        return Integer.parseInt(timeLimit.getText());
    }

    public String getOutputFile() {
        return outputFile.getText();
    }

    public String getStrategy() {
        return strategy.getSelectedItem().toString();
    }

    public JButton getStartBtn() {
        return startBtn;
    }

    public void setEvolution(String output) {
        evolution.setText("" + output);
    }

    private JTextField timeLimit;
    private JTextField minProcessingTime;
    private JTextField maxProcessingTime;
    private JTextField maxArrivalTime;
    private JTextField minArrivalTime;
    private JTextField numberOfServers;
    private JTextField numberOfTasks;
    private JTextField outputFile;
    private JComboBox strategy;
    private JTextArea evolution;
    private JButton startBtn = new JButton("Start");

    public View(){
        this.setTitle("Queues Simulator");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel mainPanel = new JPanel();

        minProcessingTime = new JTextField(3);
        maxProcessingTime = new JTextField(3);
        maxArrivalTime = new JTextField(3);
        minArrivalTime = new JTextField(3);
        numberOfServers = new JTextField(3);
        numberOfTasks = new JTextField(3);
        timeLimit = new JTextField(3);
        outputFile = new JTextField(10);
        strategy = new JComboBox(new String[]{"STRATEGY_QUEUE", "STRATEGY_TIME"});
        strategy.setEditable(false);
        evolution = new JTextArea(5, 35);
        evolution.setLineWrap(true);

        panel1.add(new Label("Number of Tasks:"));
        panel1.add(numberOfTasks);
        panel1.add(new Label("Number of Servers:"));
        panel1.add(numberOfServers);

        panel2.add(new Label("Min arrival time:"));
        panel2.add(minArrivalTime);
        panel2.add(new Label("Max arrival time:"));
        panel2.add(maxArrivalTime);

        panel3.add(new Label("Min processing time:"));
        panel3.add(minProcessingTime);
        panel3.add(new Label("Max processing time:"));
        panel3.add(maxProcessingTime);

        panel4.add(new Label("Time limit:"));
        panel4.add(timeLimit);
        panel4.add(new Label("Output file:"));
        panel4.add(outputFile);

        panel5.add(strategy);
        panel5.add(startBtn);
        panel6.add(evolution);

        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        mainPanel.add(panel4);
        mainPanel.add(panel5);
        mainPanel.add(panel6);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        this.setContentPane(mainPanel);
        this.setVisible(true);
    }


}
