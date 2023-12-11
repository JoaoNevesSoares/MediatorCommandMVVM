package org.PlayingMVVM;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Flow;
import java.util.function.Consumer;

public class Mediator {

    public final static String EVENT_UPDATE = "update";
    public final static String ON_CREATE = "create";
    public final static String ON_SCHEDULE = "schedule";

    private final Map<String, List<SubscriberObject>> subscribers = new LinkedHashMap<>();

    private static Mediator instance = new Mediator();

    public void publish(String event){

        Platform.runLater(() ->{
            List<SubscriberObject> subscriberList = instance.subscribers.get(event);
            if(subscriberList != null){
                subscriberList.forEach(subscriberObject -> subscriberObject.getCb().accept(event));
            }

        });
    }
    public void subscribe(String event, Object subscriber, Consumer<String> cb){
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
