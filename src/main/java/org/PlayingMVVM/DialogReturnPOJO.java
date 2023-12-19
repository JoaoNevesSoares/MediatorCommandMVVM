package org.PlayingMVVM;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;

public class DialogReturnPOJO {

    private final IntegerProperty pid;
    private final IntegerProperty burst;
    public DialogReturnPOJO(int pid, int burst) {
        this.pid = new SimpleIntegerProperty(pid);
        this.burst = new SimpleIntegerProperty(burst);
    }

    public int getPid() {
        return pid.get();
    }

    public int getBurst() {
        return burst.get();
    }

}
