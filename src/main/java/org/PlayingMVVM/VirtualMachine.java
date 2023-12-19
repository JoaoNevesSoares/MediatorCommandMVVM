package org.PlayingMVVM;

import java.util.ArrayList;

public class VirtualMachine {

    private static final Mediator  mediator = new Mediator();
    static int timeSlice = 0 ;
    public static ArrayList<Process> running = new ArrayList<>();

    public static void run() {

        ArrayList<Process> processes = new ArrayList<>();
        if(running.isEmpty()){
            running.add(processes.get(0));
            mediator.publish(Mediator.ON_SCHEDULE);
        }
        else{
            if(timeSlice > 2){
                processes.add(running.remove(0));
                running.add(processes.get(0));
                timeSlice = 0;
                mediator.publish(Mediator.ON_SCHEDULE);
            }
            else{
                timeSlice += 1;
            }
        }
    }
}
