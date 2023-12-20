package org.PlayingMVVM;

import java.util.ArrayList;

public class VirtualMachine {

    static int timeSlice = 0 ;
    public static ArrayList<Process> running = new ArrayList<>();

    private Process process;

    public void setProcess(Process process) {
        this.process = process;
    }

    public void run() {

        if(running.isEmpty()){
            Mediator.getInstance().send(this, Mediator.Action.PROC_TO_EXECUTE);
            process.dispatch();
            running.add(process);
        }
        else{
            if(timeSlice > 2){
                Process p = running.remove(0);
                p.interrupt();
                Mediator.getInstance().send(this, Mediator.Action.PROC_TO_EXECUTE);
                process.dispatch();
                running.add(process);
                timeSlice = 0;
            }
            else{
                timeSlice += 1;
            }
        }
    }
}
