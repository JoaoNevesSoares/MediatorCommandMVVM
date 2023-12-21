package org.PlayingMVVM;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProcessInfoDialogController {

    @FXML
    private Label processState;
    @FXML
    private Label pid;
    @FXML
    private Label burst;
    @FXML
    private Label arrival;
    @FXML
    private Label priority;

    private Process proc;

    // Method to initialize the controller
    public void initialize() {
        // Assuming the proc object is set externally
        // Bind the properties here
        if (proc != null) {
            bindProcessProperties();
        }
    }

    public void setProcess(Process proc) {
        this.proc = proc;
        bindProcessProperties();
    }

    private void bindProcessProperties() {
        // Bind the label text properties to the corresponding properties of the Process object
        processState.textProperty().bind(proc.processStateProperty());
        pid.textProperty().bind(proc.processPidProperty().asString());
        burst.textProperty().bind(proc.processBurstProperty().asString());
        arrival.textProperty().bind(proc.processArrivalProperty().asString());
        priority.textProperty().bind(proc.processPriorityProperty().asString());
    }
}
