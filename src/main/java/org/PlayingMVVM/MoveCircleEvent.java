package org.PlayingMVVM;

import javafx.event.Event;
import javafx.event.EventType;

public class MoveCircleEvent extends Event {


    public MoveCircleEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
}
