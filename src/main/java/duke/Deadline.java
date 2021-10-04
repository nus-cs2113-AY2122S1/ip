package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    //constructs a Deadline instance
    public Deadline(String description, LocalDate by) {
        super(description);
        setBy(by);
    }

    //sets the by field to a date entered by user
    public void setBy(LocalDate byDate) {
        this.by = byDate;
    }

    //returns the type of the task, in this case Deadline which is D
    @Override
    public String getType() {
        return "D";
    }

    //returns the date entered by the user of the Deadline
    @Override
    public LocalDate getWhen() {
        return this.by;
    }

    //prints the Deadline task
    @Override
    public String toString() {
        return ("[D]" + "[" + getStatusIcon() + "] " + description + "(by: "  + by.format(DateTimeFormatter.ofPattern((Task.DATE_FORMAT))) + ")");
    }
}
