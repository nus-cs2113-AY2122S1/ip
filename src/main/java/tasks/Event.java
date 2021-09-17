package tasks;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task implements Serializable {

    LocalDateTime completeTime;

    public Event (String taskName, String completeTime, boolean isCompleted) {
        super(taskName, isCompleted);
        this.completeTime = LocalDateTime.parse(completeTime);
    }

    public String toString() {
        return "[E]" + super.toString() + "(" +
                this.completeTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:ss")) + ")";
    }


    public String save() {
        return "E | " + (super.hasCompleted()? "1 | " : "0 | ") + this.getTaskName() + " | "
                + this.completeTime.toString() + "\n";
    }
}
