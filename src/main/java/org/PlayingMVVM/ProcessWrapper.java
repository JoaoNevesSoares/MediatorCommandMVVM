package org.PlayingMVVM;

import javafx.scene.shape.Circle;

public class ProcessWrapper {

    private Process process;
    private Circle cicle;

    public ProcessWrapper(Process process, Circle cicle){
        this.process = process;
        this.cicle = cicle;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Circle getCicle() {
        return cicle;
    }

    public void setCicle(Circle cicle) {
        this.cicle = cicle;
    }
}
