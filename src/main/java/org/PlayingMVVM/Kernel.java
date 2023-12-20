package org.PlayingMVVM;

import java.util.ArrayList;

public class Kernel {

    static int pid = 0;
    private static ArrayList<Process> processList = new ArrayList<>();
    public Process createProcess(Process.Type type, int burst, int priority, int arrivalInstant) {

        Process newProcess = new Process(pid++, burst, priority, arrivalInstant, arrivalInstant);
        processList.add(newProcess);
        return newProcess;
    }
    public Process requeueProcess() {
        Process process = processList.remove(0);
        processList.add(process);
        return process;
    }
}
