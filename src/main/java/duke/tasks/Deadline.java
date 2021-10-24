package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline that has a specific time by which it is to be completed
 */
public class Deadline extends Task {

    private final String by;
    private final LocalDateTime byDT;

    /**
     * Constructs Deadline with the "by" being a String
     *
     * @param description description of the deadline
     * @param by          time by which deadline is due
     * @param isDone      whether the deadline has been completed or not
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description);
        this.by = by;
        this.byDT = null;
        this.isDone = isDone;
    }

    /**
     * Constructs a Deadline with the "by" being a LocalDateTime
     *
     * @param description description of the deadline
     * @param byDT        time by which deadline is due
     * @param isDone      whether the deadline has been completed or not
     */
    public Deadline(String description, LocalDateTime byDT, boolean isDone) {
        super(description);
        this.by = null;
        this.byDT = byDT;
        this.isDone = isDone;
    }

    /**
     * @return the LocalDateTime that the task is due/at if it's a deadline or event
     */
    @Override
    public LocalDateTime getDT() {
        return byDT;
    }

    /**
     * Converts the task into a String fit for being stored into a data file
     *
     * @return String consisting of all the task information to be stored in a data file
     */
    @Override
    public String toData() {
        if (byDT == null) {
            return "D | " + ((isDone) ? 1 : 0) + " | " + task + " | " + by;
        }
        return "D | " + ((isDone) ? 1 : 0) + " | " + task + " | "
                + byDT.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    /**
     * Converts the task into a String to be printed for the user to see
     *
     * @return String consisting of all the task information to be shown to user
     */
    @Override
    public String toString() {
        if (byDT == null) {
            return "[D]" + (isDone ? "[X] " : "[ ] ") + task + " (by: " + by + ")";
        }
        return "[D]" + (isDone ? "[X] " : "[ ] ") + task + " (by: " +
                byDT.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }

}
