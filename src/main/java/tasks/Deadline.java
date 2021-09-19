package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * <h1>The <b>Deadline</b> type {@link Task} from users</h1>
 */
public class Deadline extends Task {

    private LocalDateTime deadline;

    public Deadline (String taskName, LocalDate deadline, boolean isCompleted) {
        super(taskName, isCompleted);
        this.deadline = LocalDateTime.of(deadline, LocalTime.parse("00:00"));
    }

    public Deadline (String taskName, LocalDate deadline, LocalTime time, boolean isCompleted) {
        super(taskName, isCompleted);
        this.deadline = LocalDateTime.of(deadline, time);
    }


    @Override
    public String toString() {
        return String.format("[D]%s (%s)", super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }

    public String save() {
        return String.format("D | %b | %s | %s\n", super.hasCompleted(), this.getTaskName(),
                this.deadline.toString());
    }

    public LocalDateTime getTime() {
        return this.deadline;
    }


}
