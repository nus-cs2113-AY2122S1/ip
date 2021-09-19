package duke.task;

import duke.parser.Parser;

import java.time.LocalDateTime;

import static duke.constants.DukeCommandStrings.DATE_TIME_OUTPUT_FORMAT;

public class Event extends Task {
    protected final String TASK_TYPE = "E";
    protected LocalDateTime when;

    public Event(String description, LocalDateTime when) {
        super(description);
        setWhen(when);
    }

    public String getTaskType() {
        return TASK_TYPE;
    }


    public LocalDateTime getWhen() {
        return when;
    }

    public void setWhen(LocalDateTime when) {
        this.when = when;
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (at: " + Parser.dateTimeToString(when, DATE_TIME_OUTPUT_FORMAT) + ")";
    }
}
