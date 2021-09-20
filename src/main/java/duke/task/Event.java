package duke.task;

import duke.Util.Util;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description, Task.TYPE_EVENT);
        this.at = at;
    }

    /**
     * Returns string in list entry format.
     * eg. [T][ ] task1 (at: Aug 6th 2-4pm)
     *
     * @return The formatted string.
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), Util.getFormattedDateTime(at, DATETIME_FORMAT_OUTPUT));
    }

    @Override
    public String toFileString() {
        return generateFileString(new String[]{
                Character.toString(taskType),
                Integer.toString((isDone) ? 1 : 0),
                description,
                Util.getFormattedDateTime(at, DATETIME_FORMAT_INPUT)
        });
    }
}
