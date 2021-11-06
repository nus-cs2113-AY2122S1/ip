package duke.task;

import duke.parser.Parser;

import java.time.LocalDateTime;

import static duke.constants.DukeCommandStrings.DATE_TIME_OUTPUT_FORMAT;

/**
 * Subclass of {@code Task} which has an extra attribute {@code by} which represents
 * the deadline of the task.
 */
public class Deadline extends Task {
    protected final String TASK_TYPE = "D";
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        setBy(by);
    }

    public String getTaskType() {
        return TASK_TYPE;
    }

    public LocalDateTime getBy() {
        return by;
    }

    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    /**
     * Converts {@code LocalDateTime} attribute to a formatted date and time {@code String} which looks nice.
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (by: " + Parser.dateTimeToString(by, DATE_TIME_OUTPUT_FORMAT) + ")";
    }
}
