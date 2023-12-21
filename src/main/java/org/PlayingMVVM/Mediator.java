package org.PlayingMVVM;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.*;
public class Mediator {

    public enum Component {
        VM,
        KERNEL,
        GUI,
    }
    public enum Action {

        CREATE_PROC,
        VISUALIZE,
        PROC_TO_EXECUTE,
        RUN,
        ON_THIS_PROCESS_DISPATCHED,
        ON_THIS_PROCESS_INTERRUPTED,
        VIEW_PROCESS_INFO,
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
                CreateProcessDialog createProcessDialog = new CreateProcessDialog();
                IntegerProperty burst = new SimpleIntegerProperty();
                burst.bind(createProcessDialog.burstProperty());
                Optional<ButtonType> result = createProcessDialog.showAndWait();
                if(result.isPresent() && result.get() == ButtonType.OK){
                    System.out.println("Process created");
                    System.out.println("Burst: " + burst.get());
                    Circle newCircle = modelView.createCircle();
                    Process newProcess = kernel.createProcess(Process.Type.SIMPLE, burst.get(), 0, 0);
                    processCircleMap.put(newProcess,newCircle);
                    circleProcessMap.put(newCircle, newProcess);
                    modelView.addProcessToReadyList(newCircle);
                }
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
            case VIEW_PROCESS_INFO:
                Circle circle = (Circle) object;
                Process process = circleProcessMap.get(circle);
                Color col = (Color) circle.fillProperty().get();
                modelView.showProcessInfo(process,col);
                break;
        }
    }
    private void listAllProcessInfo() {
        processCircleMap.forEach((process, circle) -> {
            System.out.println("Processo: " + process.getPid() + " Burst: " + process.getBurst());
        });
    }
}
