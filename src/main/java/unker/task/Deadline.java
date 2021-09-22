package unker.task;

import java.time.LocalDateTime;
import unker.util.StringUtil;

public class Deadline extends Task {

    public static final String DEADLINE_DATA_PATTERN = "^(.+) /[bB][yY] " 
            + "([\\d]{4}-(?:(?:0[1-9])|(?:1[0-2]))-(?:(?:0[1-9])|(?:[1-2]\\d)|(?:3[01]))) " // Validates Date 
            + "((?:(?:[0-1]\\d)|(?:2[0-3])):[0-5]?\\d(?::[0-5]?\\d)?)$"; // Validates Time
    protected LocalDateTime by;

    public Deadline(String task, LocalDateTime by) {
        super(task);
        this.by = by;
    }

    /**
     * Gets the due date of the task.
     * 
     * @return The due date of the task.
     */
    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), StringUtil.toFriendlyDateTimeString(this.by));
    }

    @Override
    public String getSaveableString() {
        return String.format("%s,%d,%s /by %s", "D", isDone ? 1 : 0, description, StringUtil.toISODateTimeString(this.by));
    }
}
