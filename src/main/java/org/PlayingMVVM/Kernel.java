package org.PlayingMVVM;

import java.util.ArrayList;

public class Kernel {

    static int pid = 0;
    public Process createProcess(Process.Type type, int burst, int priority, int arrivalInstant) {

        return new Process(Kernel.pid++, burst, priority, arrivalInstant, arrivalInstant);
    }
}
