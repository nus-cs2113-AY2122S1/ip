package InputHandle.Tasks;

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
}
