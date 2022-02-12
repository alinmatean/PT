package tuc.tp.tema2.userInterface;

import tuc.tp.tema2.SimulationManager;
import tuc.tp.tema2.userInterface.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private View simulatorView;
    private SimulationManager simulationManager;

    public Controller(final View simulatorView){
        this.simulatorView = simulatorView;
        simulatorView.getStartBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulationManager = new SimulationManager(simulatorView);
                Thread t = new Thread(simulationManager);
                t.start();
            }
        });
    }
}
