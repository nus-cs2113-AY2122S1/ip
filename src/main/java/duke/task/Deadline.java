package duke.task;

import duke.common.CommonFormat;
import duke.common.Messages;
import java.time.LocalDateTime;

/**
 * Class that represents a Deadline Task.
 */
public class Deadline extends Task {

    final public static String FLAG_TYPE = "[D]";

    final public static int totalArg = 2;

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
        return FLAG_TYPE + CommonFormat.INFO_SEPARATOR + getDoneStatus() + CommonFormat.INFO_SEPARATOR
                + this.getDescription() + CommonFormat.INFO_SEPARATOR
                + dueDate.format(CommonFormat.formatter);
    }
}
