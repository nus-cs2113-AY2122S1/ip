package tasks;

import java.io.Serializable;

public class Event extends Task implements Serializable {

    String completeTime;

    public Event (String taskName, String completeTime, boolean isCompleted) {
        super(taskName, isCompleted);
        this.completeTime = completeTime;
    }

    public String toString() {
        return "[E]" + super.toString() + "(" + this.completeTime + ")";
    }


    public String save() {
        return "E | " + (super.hasCompleted()? "1 | " : "0 | ") + this.getTaskName() + " | " + this.completeTime + "\n";
    }
}
