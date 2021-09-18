package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h1>The <b>Deadline</b> type {@link Task} from users</h1>
 */
public class Deadline extends Task {

    private LocalDate deadline;

    public Deadline(String taskName, String deadline, boolean isCompleted) {
        super(taskName, isCompleted);
        this.deadline = LocalDate.parse(deadline);
    }

    public String toString() {
        return "[D]" + super.toString() + "(" +
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String save() {
        return "D | " + (super.hasCompleted() ? "1 | " : "0 | ") + this.getTaskName() + " | " +
                this.deadline.toString() + "\n";
    }
}
