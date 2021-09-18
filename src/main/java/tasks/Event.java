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

    @Override
    public String toString() {
        return String.format("[E] %s (%s)", super.toString(), this.completeTime);
    }


    public String save() {
        return String.format("E | %d | %s | %s\n", super.hasCompleted(), this.getTaskName(), this.completeTime);
    }
}
