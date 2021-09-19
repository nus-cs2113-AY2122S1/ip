package tasks;


import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;



/**
 * <h1>The <b>Event</b> type {@link Task} from users</h1>
 */
public class Event extends Task {

    LocalDateTime completeTime;

    public Event (String taskName, LocalDate completeDay, LocalTime completeTime, boolean isCompleted) {
        super(taskName, isCompleted);
        this.completeTime = LocalDateTime.of(completeDay, completeTime);
    }

    public Event (String taskName, LocalDateTime completeTime, boolean isCompleted) {
        super(taskName, isCompleted);
        this.completeTime = completeTime;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (%s)", super.toString(),
                this.completeTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:ss")));
    }


    public String save() {
        return String.format("E | %b | %s | %s\n", super.hasCompleted(), this.getTaskName(),
                this.completeTime.toString());
    }

    public LocalDateTime getTime() {
        return this.completeTime;
    }
}
