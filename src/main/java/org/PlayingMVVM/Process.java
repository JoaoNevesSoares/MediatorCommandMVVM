package org.PlayingMVVM;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Process {
    private int pid;
    private int burst;
    private int priority;
    private int arrivalTime;

    StringProperty processState = new SimpleStringProperty();
    IntegerProperty processPid = new SimpleIntegerProperty();
    IntegerProperty processBurst = new SimpleIntegerProperty();
    IntegerProperty processPriority = new SimpleIntegerProperty();
    IntegerProperty processArrival = new SimpleIntegerProperty();

    private State state;
    Process(int pid, int burst, int priority, int arrivalTime, int arrivalInstant) {
        this.pid = pid;
        this.burst = burst;
        this.priority = priority;
        this.arrivalTime = arrivalTime;

        state = State.READY;
        processPid.set(pid);
        processBurst.set(burst);
        processPriority.set(priority);
        processArrival.set(arrivalTime);
        processState.set(state.toString());
    }
    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setBurst(int burst) {
        this.burst = burst;
    }
    public void setState(State state){
        this.state = state;
        setProcessState(state.toString());
    }

    public void suspend(){
        this.state = State.WAITING;
        //launch a callback in the Mediator passing this process to update and
        // redirect it to Modelview to change Circle related to this process to Other HBox
    }
    public void dispatch(){
        if(getState() != State.READY){
            throw new IllegalStateException("Process must be in READY state to be dispatched");
        }
        else{
            setState(State.RUNNING);
            Mediator.getInstance().send(this, Mediator.Action.ON_THIS_PROCESS_DISPATCHED);
        }
    }
    public void interrupt(){
        if(getState() != State.RUNNING){
            throw new IllegalStateException("Process must be in RUNNING state to be interrupted");
        }
        else{
            setState(State.READY);
            Mediator.getInstance().send(this, Mediator.Action.ON_THIS_PROCESS_INTERRUPTED);
        }
    }

    public int getPid() {
        return pid;
    }

    public int getBurst() {
        return burst;
    }

    public int getPriority() {
        return priority;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public State getState() {
        return state;
    }

    public String getProcessState() {
        return processState.get();
    }

    public StringProperty processStateProperty() {
        return processState;
    }

    public void setProcessState(String processState) {
        this.processState.set(processState);
    }

    public int getProcessPid() {
        return processPid.get();
    }

    public IntegerProperty processPidProperty() {
        return processPid;
    }

    public void setProcessPid(int processPid) {
        this.processPid.set(processPid);
    }

    public int getProcessBurst() {
        return processBurst.get();
    }

    public IntegerProperty processBurstProperty() {
        return processBurst;
    }

    public void setProcessBurst(int processBurst) {
        this.processBurst.set(processBurst);
    }

    public int getProcessPriority() {
        return processPriority.get();
    }

    public IntegerProperty processPriorityProperty() {
        return processPriority;
    }

    public void setProcessPriority(int processPriority) {
        this.processPriority.set(processPriority);
    }

    public int getProcessArrival() {
        return processArrival.get();
    }

    public IntegerProperty processArrivalProperty() {
        return processArrival;
    }

    public void setProcessArrival(int processArrival) {
        this.processArrival.set(processArrival);
    }

    public enum State {
        /**
         * The Process has been just instantiated but not assigned to CPU Core.
         */
        NEW,

        /**
         * The Process has been assigned to a Scheduler Queue to be later executed.
         */
        READY,
        /**
         * The Process is currently being executed by a CPU Core.
         */
        RUNNING,

        /**
         * The Process is currently waiting for an I/O operation to be completed.
         */
        WAITING,

        /**
         * The Process has been terminated.
         */
        TERMINATED
    }

    public enum Type {

        /**
         * The Process does not have memory and Register requirements
         */
        SIMPLE,

    }
}
