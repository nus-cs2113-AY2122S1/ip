package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline which has a specific date and time by which the task
 * has to be completed.
 */
public class Deadline extends Task {
    /** Deadline date and time */
    protected LocalDateTime dueDateTime;

    /**
     * Constructs a new deadline task.
     *
     * @param description Deadline task description
     * @param dueDateTime Date and time details of the deadline task
     */
    public Deadline(String description, LocalDateTime dueDateTime) {
        super(description);
        this.dueDateTime = dueDateTime;
    }

    /**
     * Converts deadline task object into a String, which is used to store it in the file.
     *
     * @return String consisting of all the information related to the task
     */
    @Override
    public String toFileFormat() {
        return "D # " + (isDone ? "1" : "0") + " # " + description + " # " +
                dueDateTime + "\n";
    }

    /**
     * Converts event task object into a String, which is used to display in the console.
     *
     * @return String consisting of all the information related to the task
     */
    @Override
    public String toString() {
        return "[D] [" + getStatus() + "] " + getDescription() + " (by: " +
                dueDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) +
                ")";
    }

    /**
     * @return The date and time details of the event task.
     */
    @Override
    public LocalDateTime getDT() {
        return dueDateTime;
    }

}