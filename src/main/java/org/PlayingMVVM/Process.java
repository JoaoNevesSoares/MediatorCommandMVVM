package org.PlayingMVVM;

public class Process {

    private int pid;
    private int burst;

    Process(int pid, int burst){
        this.pid = pid;
        this.burst = burst;
    };
    public String getPidandBurst(){
        return "pid = " + Integer.toString(pid) + "burst = " + Integer.toString(burst);
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setBurst(int burst) {
        this.burst = burst;
    }
}
