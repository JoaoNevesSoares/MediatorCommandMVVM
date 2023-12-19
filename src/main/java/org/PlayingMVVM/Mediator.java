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
        TESTE,
        VISUALIZAR,
    }
    public final static String EVENT_UPDATE = "update";
    public final static String ON_CREATE = "create";
    public final static String ON_SCHEDULE = "schedule";

    private final Map<String, List<SubscriberObject>> subscribers = new LinkedHashMap<>();
    private final Map<Process, Circle> processCircleMap = new HashMap<>();
    private static final Mediator instance = new Mediator();
    public static Mediator getInstance(){
        return instance;
    }

    private Kernel kernel;
    private ModelView modelView;

    public void register(Object ob, Component component) {
        switch (component){
            case GUI:
                modelView = (ModelView) ob;
                break;
            case KERNEL:
                kernel = (Kernel) ob;
                break;
        }
    }
    public void send(Object object, Action action){
        switch (action){
            case TESTE:
                Circle c = processCircleMap.get(object);
                break;
            case CREATE_PROC:
                DialogReturnPOJO pojo = modelView.createProcessDialog();
                Circle newCircle = modelView.createCircle();
                processCircleMap.put(kernel.createProcess(Process.Type.SIMPLE, pojo.getBurst(), 0, 0),newCircle);
                modelView.addCircleToCreatedProcessList(newCircle);
                break;
            case VISUALIZAR:
                listAllProcessInfo();
                break;
        }
    }

    public void publish(String event) {

        Platform.runLater(() ->{
            List<SubscriberObject> subscriberList = instance.subscribers.get(event);
            if(subscriberList != null){
                subscriberList.forEach(subscriberObject -> subscriberObject.getCb().accept(event));
            }

        });
    }

    private void listAllProcessInfo(){
        processCircleMap.forEach((process, circle) -> {
            System.out.println("Processo: " + process.getPid() + " Burst: " + process.getBurst());
        });
    }

    public void subscribe(String event, Object subscriber, Consumer<String> cb) {
        if (!instance.subscribers.containsKey(event)) {
            List<SubscriberObject> slist = new ArrayList<>();
            instance.subscribers.put(event,slist);
        }
        List<SubscriberObject> subscriberList = instance.subscribers.get(event);
        subscriberList.add(new SubscriberObject(subscriber,cb));
    }

    static class SubscriberObject {

        private final Object subscriber;
        private final Consumer<String> cb;

        public SubscriberObject(Object subscriber, Consumer<String> cb){
            this.subscriber = subscriber;
            this.cb = cb;
        }
        public Object getSubscriber(){
            return subscriber;
        }
        public Consumer<String> getCb() {
            return cb;
        }
    }

}
