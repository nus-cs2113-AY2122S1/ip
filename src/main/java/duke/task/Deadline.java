package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a due date
 * This class inherits the <code>Task</code> class
 *
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor method for <code>Deadline</code>
     *
     * @param description the description of the deadline
     * @param deadline due date of the deadline
     * @param isDone true if the deadline is finished, false otherwise
     */
    public Deadline(String description, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Gets due date
     *
     * @return due date of the deadline
     */
    public String getDate() {
        return this.deadline.format(dateTimeFormat);
    }

    /**
     * Formats the content of the deadline task as String
     *
     * @return String representation of the deadline task
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", isDone ? "X" : " ", this.taskDescription,
                this.deadline.format(dateTimeFormat));
    }
}
