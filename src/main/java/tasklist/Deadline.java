package tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    /**
     * Returns additional information which is the type icon and deadline date
     * & time at the end when toString method is called.
     * @return the type icon followed by the usual toString method of Task and
     * the deadline date & time at the end
     */
    @Override
    public String toString() {
        return "[" + getType() + "]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")) + ")";
    }

    /**
     * Adds additional character to support file format when saving deadline
     * @return adds a comma between 'description' and 'by' strings to follow
     * the CSV format when saving to text file
     */
    @Override
    public String getDescription() {
        return description + "," + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
