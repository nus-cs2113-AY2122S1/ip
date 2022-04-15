package duke.task;

import duke.common.CommonFormat;
import java.time.LocalDateTime;

/**
 * Class that represents an Event Task.
 */
public class Event extends Task {

    final public static String FLAG_TYPE = "[E]";

    final public static int totalArg = 2;

    private LocalDateTime startDate;

    public Event(String description, String startDate) {
        super(description);
        this.startDate = convertToLocalDateTime(startDate);
    }

    /**
     * Method used to get the localdate only from the localdatetime startDate.
     *
     * @return A String representing the date of startDate.
     */
    @Override
    public String getDate() {
        return startDate.format(CommonFormat.formatterDateOnly);
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
        return getDescription() + " (at: " + startDate.format(CommonFormat.formatterPrint) + ")";
    }

    @Override
    public String toString() {
        return FLAG_TYPE + CommonFormat.INFO_SEPARATOR
                + getDoneStatus()
                + CommonFormat.INFO_SEPARATOR
                + this.getDescription() + CommonFormat.INFO_SEPARATOR
                + startDate.format(CommonFormat.formatter);
    }
}
