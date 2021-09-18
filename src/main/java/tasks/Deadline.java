package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * <h1>The <b>Deadline</b> type {@link Task} from users</h1>
 */
public class Deadline extends Task {

    private String deadline;

    public Deadline (String taskName, String deadline, boolean isCompleted) {
        super(taskName, isCompleted);
        this.deadline = deadline;
    }


    @Override
    public String toString() {
        return String.format("[D] %s (%s)", super.toString(), this.deadline);
    }

    public String save() {
        return String.format("D | %d | %s | %s\n", super.hasCompleted(), this.getTaskName(), this.deadline);
    }


}
