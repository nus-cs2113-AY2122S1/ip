package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends TimedTask {
    public static final char TASK_TYPE_DEADLINE = 'D';
    /**
     * Constructor of Deadline from console
     * @param description of the deadline task
     * @param date is a datetime object
     */
    public Deadline(String description, LocalDateTime date) {
        super(description, TASK_TYPE_DEADLINE, date);
    }
    /**
     * Constructor of Deadline from file
     * @param description of the deadline task
     * @param isDone status of the task
     * @param date is a datetime object
     */
    public Deadline(String description, boolean isDone, LocalDateTime date) {
        super(description, TASK_TYPE_DEADLINE, isDone, date);
    }

    /**
     * Convert event task to string upon printing on console
     * @return string of deadline task
     */
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String formatDateTime = getStartDate().format(format);
        return "[" + taskType + "][" + getStatusIcon() + "] "
                + description + " (by: " + formatDateTime + ")";
    }
    /**
     * Convert datetime object to string for storing in file
     * @return string consist of deadline datetime
     */
    public String getDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String formatDateTime = getStartDate().format(format);
        return formatDateTime;
    }
}
