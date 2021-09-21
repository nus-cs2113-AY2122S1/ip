package duke.task;

import duke.util.Util;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructor for Deadline class.
     *
     * @param description The task description.
     * @param by          The task deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, Task.TYPE_DEADLINE);
        this.by = by;
    }

    /**
     * Returns string in list entry format.
     * eg. [T][ ] task1 (by: <date/time>)
     *
     * @return The formatted string.
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), Util.getFormattedDateTime(by, DATETIME_FORMAT_OUTPUT));
    }

    /**
     * Returns string in file entry format.
     * eg. <taskType> | <isDone> | <description> | <date/time>
     *
     * @return The formatted string.
     */
    @Override
    public String toFileString() {
        return generateFileString(new String[]{
                Character.toString(taskType),
                Integer.toString((isDone) ? 1 : 0),
                description,
                Util.getFormattedDateTime(by, DATETIME_FORMAT_INPUT)
        });
    }
}
