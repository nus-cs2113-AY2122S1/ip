package duke.task;

import duke.common.CommonFormat;
import java.time.LocalDateTime;

/**
 * Class that represents a Deadline Task.
 */
public class Deadline extends Task {

    final private static String FLAG_TYPE = "[D]";

    private LocalDateTime dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = convertToLocalDateTime(dueDate);
    }

    /**
     * Method used to get the localdate only from the localdatetime dueDate.
     *
     * @return A String representing the date of dueDate.
     */
    @Override
    public String getDate() {
        return dueDate.format(CommonFormat.formatterDateOnly);
    }

    @Override
    public String getStatusIcon() {
        return FLAG_TYPE + super.getStatusIcon();
    }

    /**
     * Get all information of the task.
     *
     * @return String containing all information of the task.
     */
    @Override
    public String getTaskInfo() {
        return getDescription() + " (by: " + dueDate.format(CommonFormat.formatterPrint) + ")";
    }

    @Override
    public String toString() {
        return FLAG_TYPE + " | " + getDoneStatus() + " | " + this.getDescription() + " | " + dueDate.format(
                CommonFormat.formatter);
    }
}
