package duke.task;

import duke.common.CommonFormat;
import java.time.LocalDateTime;

public class Event extends Task {

    final private static String FLAG_TYPE = "[E]";

    private LocalDateTime startDate;

    public Event(String description, String startDate) {
        super(description);
        this.startDate = convertToLocalDateTime(startDate);
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
        return FLAG_TYPE + " | " + getDoneStatus() + " | " + this.getDescription() + " | " + startDate.format(CommonFormat.formatter);
    }
}
