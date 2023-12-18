package org.PlayingMVVM;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Model {

    private static final Mediator  mediator = new Mediator();
    static int x =0 ;

    public static ArrayList<String> proceses = new ArrayList<>();
    public static ArrayList<String> running = new ArrayList<>();

    public static void createProcess(int processId) {
        proceses.add("proc - " + Integer.toString(processId));
        mediator.publish(Mediator.ON_CREATE);
    }


    public static void run() {

        if(running.isEmpty()){
            running.add(proceses.get(0));
            mediator.publish(Mediator.ON_SCHEDULE);
        }
        else{
            if(x > 2){
                proceses.add(running.remove(0));
                running.add(proceses.get(0));
                x = 0;
                mediator.publish(Mediator.ON_SCHEDULE);
            }
            else{
                x +=1;
            }
        }
    }
}
