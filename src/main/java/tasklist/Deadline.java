package tasklist;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")) + ")";
    }
    //by.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))

    @Override
    public String getDescription() {
        return description + "," + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
