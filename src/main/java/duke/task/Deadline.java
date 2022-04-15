package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final LocalDate deadlineDate;
    private final String deadlineTime;

    /**
     * Deadline task constructor.
     * @param deadlineName the name/description of the deadline task
     * @param deadlineDate the date by which should finish the deadline task
     * @param deadlineTime the time by which should finish the deadline task
     */
    public Deadline(String deadlineName, LocalDate deadlineDate, String deadlineTime) {
        super(deadlineName);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Get the deadline date and time.
     * @param isNewFormat whether show dates in a new format or old format from the user
     * @return the deadline date and time as String
     */
    public String getDeadlineDateTime(boolean isNewFormat) {
        int minutesStartIndex = 0;
        int minutesEndIndex = 2;
        int hoursStartIndex = 2;
        String deadlineDateTime;
        if (isNewFormat) {
            deadlineDateTime = this.deadlineDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                    + " "
                    + this.deadlineTime.substring(minutesStartIndex, minutesEndIndex)
                    + ":"
                    + this.deadlineTime.substring(hoursStartIndex);

        } else {
            deadlineDateTime = this.deadlineDate.toString()
                    + " "
                    + this.deadlineTime.substring(minutesStartIndex, minutesEndIndex)
                    + this.deadlineTime.substring(hoursStartIndex);
        }
        return deadlineDateTime;
    }

    /**
     * Show the full information of the deadline task.
     * @return the full information of the deadline task as String
     */
    @Override
    public String toString() {
        return "[D]" + "[" + this.getTaskStatusInString() + "] " + this.getTaskName()
                + "(by: " + this.getDeadlineDateTime(true) + ")";
    }

}