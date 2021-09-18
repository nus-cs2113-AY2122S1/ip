package tasks;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



/**
 * <h1>The <b>Event</b> type {@link Task} from users</h1>
 */
public class Event extends Task {

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
