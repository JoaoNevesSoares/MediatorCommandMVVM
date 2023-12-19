package org.PlayingMVVM;

public class Process {
    private int pid;
    private int burst;

    private int priority;

    private int arrivalTime;

    private State state;

    Process(int pid, int burst, int priority, int arrivalTime, int arrivalInstant){
        this.pid = pid;
        this.burst = burst;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
    }
    public void created(){
        setState(State.NEW);
        Mediator.getInstance().send(this, Mediator.Action.TESTE);
    }
    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setBurst(int burst) {
        this.burst = burst;
    }
    public void setState(State state){
        this.state = state;
    }


    public void Suspend(){
        this.state = State.WAITING;
        //launch a callback in the Mediator passing this process to update and
        // redirect it to Modelview to change Circle related to this process to Other HBox
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
