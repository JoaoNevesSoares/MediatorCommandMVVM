package org.PlayingMVVM;

import javafx.scene.shape.Circle;
import javafx.application.Platform;
import java.util.*;
import java.util.function.Consumer;
public class Mediator {

    public enum Component {
        VM,
        KERNEL,
        GUI,
    }
    public enum Action {

        CREATE_PROC,
        KERNEL_NEW_PROCESS,
        GET_PROC_LIST,
        TEST,
        VISUALIZE,
        PROC_TO_EXECUTE,
        RUN,
        ON_SCHEDULE,
        ON_THIS_PROCESS_DISPATCHED,
        ON_THIS_PROCESS_INTERRUPTED,
    }
    private final Map<Process, Circle> processCircleMap = new HashMap<>();
    private final Map<Circle, Process> circleProcessMap = new HashMap<>();
    private static final Mediator instance = new Mediator();
    public static Mediator getInstance(){
        return instance;
    }

    private Kernel kernel;
    private ModelView modelView;

    private VirtualMachine vm;

    public void register(Object ob, Component component) {
        switch (component){
            case GUI:
                modelView = (ModelView) ob;
                break;
            case KERNEL:
                kernel = (Kernel) ob;
                break;
            case VM:
                vm = (VirtualMachine) ob;

        }
    }
    public void send(Object object, Action action) {
        switch (action){
            case CREATE_PROC:
                DialogReturnPOJO pojo = modelView.createProcessDialog();
                if(pojo == null){
                    return;
                }
                Circle newCircle = modelView.createCircle();
                Process newProcess = kernel.createProcess(Process.Type.SIMPLE, pojo.getBurst(), 0, 0);
                processCircleMap.put(newProcess,newCircle);
                circleProcessMap.put(newCircle, newProcess);
                modelView.addProcessToReadyList(newCircle);
                break;
            case VISUALIZE:
                listAllProcessInfo();
                break;
            case PROC_TO_EXECUTE:
                Process proc = kernel.requeueProcess();
                ((VirtualMachine)object).setProcess(proc);
                break;
            case RUN:
                vm.run();
                break;
            case ON_THIS_PROCESS_DISPATCHED:
                Circle c = processCircleMap.get((Process) object);
                modelView.addProcessToRunningList(c);
                break;
            case ON_THIS_PROCESS_INTERRUPTED:
                Circle circleToRemove = processCircleMap.get((Process) object);
                modelView.removeProcessFromRunningList(circleToRemove);
                modelView.addProcessToReadyList(circleToRemove);
                break;
        }
    }
    private void listAllProcessInfo() {
        processCircleMap.forEach((process, circle) -> {
            System.out.println("Processo: " + process.getPid() + " Burst: " + process.getBurst());
        });
    }
}
