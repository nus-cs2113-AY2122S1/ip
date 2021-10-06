package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the symbol representing Event tasks.
     *
     * @return Returns the character "E"
     */
    public String getTypeIcon() {
        return "E";
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return Returns the task as a String
     */
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.getDescription() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}