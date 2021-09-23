package duke.data.task;

import static duke.ui.Strings.FORMAT_DATE_OUT;
import static duke.ui.Strings.FORMAT_TIME_OUT;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    /* Task type indicator */
    public static final char TASK_TYPE = 'E';

    private final String deadline;
    private final LocalDate deadlineDate;
    private final LocalTime deadlineTime;

    /**
     * Constructor for Task of type Event
     *
     * @param description Description of the task to add.
     * @param deadline    Deadline of task
     */
    public Event(String description, String deadline) {
        super(description, TASK_TYPE);
        this.deadline = deadline;
        this.deadlineDate = null;
        this.deadlineTime = null;
    }

    /**
     * Constructor for Task of type Event using Date and Time objects
     *
     * @param description Description of the task to add.
     * @param deadlineDate    Date Deadline of task
     * @param deadlineTime    Time Deadline of task
     */
    public Event(String description, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(description, TASK_TYPE);
        this.deadline = null;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Gets the deadline of the task.
     *
     * @return deadline of the task.
     */
    @Override
    public String getDeadline() {
        return deadline;
    }

    /**
     * Gets the deadlineDate of the task.
     *
     * @return deadlineDate of the task.
     */
    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    /**
     * Gets the deadlineTime of the task.
     *
     * @return deadlineTime of the task.
     */
    public LocalTime getDeadlineTime() {
        return deadlineTime;
    }

    /**
     * Formats details of the Task to a printable string.
     *
     * @return Formatted string of a task.
     */
    public String toFormattedString() {
        if (deadline != null){
            return String.format("%s (at: %s)", super.toFormattedString(), this.deadline);
        } else {
            assert deadlineDate != null;
            String formattedDate = deadlineDate.format(DateTimeFormatter.ofPattern(FORMAT_DATE_OUT));
            if (deadlineTime != null){
                String formattedTime = deadlineTime.format(DateTimeFormatter.ofPattern(FORMAT_TIME_OUT));;
                return String.format("%s (at: %s %s)", super.toFormattedString(), formattedDate, formattedTime);
            } else {
                return String.format("%s (at: %s)", super.toFormattedString(), formattedDate);
            }
        }
    }
}
