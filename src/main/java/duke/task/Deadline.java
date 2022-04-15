package main.java.duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Represents a {@code Task} which is of type Deadline where it has a specified deadline. */
public class Deadline extends Task {

    protected LocalDateTime dueTime;
    protected String by;

    /**
     * Constructs a {@code Deadline} task with the basic information of
     * its description and the deadline time of the task.
     *
     * @param description Description of the task found in the task list.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.dueTime = by;
        this.by = by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }

    public String getTaskType() {
        return "D";
    }

    public String printOk() {
        return super.printOkStart() + "  [D][" + getStatusIcon() + "] "
                + description + " (by: " + by + ")\n"  + super.printOkEnd();
    }

    public String printTask() {
        return "." + "[" + getTaskType() + "]" + "["
                + getStatusIcon() + "] " + description + " (by: " + by + ")";
    }

    public String printDone() {
        return HORIZONTAL_LINE + " Nice! I've marked this task as done:\n   [" + getTaskType() + "]["
                + getStatusIcon() + "] " + description + " (by: " + by + ")" + HORIZONTAL_LINE;
    }

    public String printDelete() {
        return HORIZONTAL_LINE + " Noted. I've removed this task:\n   [" + getTaskType() + "]["
                + getStatusIcon() + "] " + description + " (by: " + by + ")";
    }

    public String getFileContent() {
        return "D [" + getStatusIcon() + "] " + description + " by: " + dueTime;
    }

}
