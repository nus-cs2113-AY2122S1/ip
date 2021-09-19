package duke.task;

import duke.parser.Parser;

import java.time.LocalDateTime;

import static duke.constants.DukeCommandStrings.DATE_TIME_OUTPUT_FORMAT;

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

    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (by: " + Parser.dateTimeToString(by, DATE_TIME_OUTPUT_FORMAT) + ")";
    }
}
