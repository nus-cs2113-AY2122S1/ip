package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final TaskType taskType = TaskType.EVENT;
    private final LocalDate timeslot;

    /**
     * Event constructor
     *
     * @param title Title of event
     * @param timeslot Time of event
     */
    public Event(String title, LocalDate timeslot) {
        super(title);
        this.timeslot = timeslot;
    }

    /**
     * @return Type of Task
     */
    @Override
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * @return String representation of Event for display
     */
    @Override
    public String toString() {
        String SYMBOL = "E";
        return "[" + SYMBOL + "]" + super.toString() + " (at: " + getTime() + ")";
    }

    /**
     * @return Due date of Deadline in yyyy-mm-dd format
     */
    @Override
    public String getStandardTime() {
        return timeslot.toString();
    }

    /**
     * @return Due date of Deadline in MMM dd yyyy format
     */
    @Override
    public String getTime() {
        return timeslot.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
