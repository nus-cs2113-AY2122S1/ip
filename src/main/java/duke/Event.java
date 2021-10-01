package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    //constructs a Event instance
    public Event(String description, LocalDate at) {
        super(description);
        setAt(at);
    }

    //sets the at field to a date entered by user
    public void setAt(LocalDate atDate) {
        this.at = atDate;
    }

    //returns the type of the task, in this case Event which is E
    @Override
    public String getType()  {
        return "E";
    }

    //returns the date entered by the user of the Event
    @Override
    public LocalDate getWhen() {
        return this.at;
    }

    //prints the EVent task
    @Override
    public String toString() {
        return ("[E]" + "[" + getStatusIcon() + "] " + description + "(at: " + at.format(DateTimeFormatter.ofPattern((Task.DATE_FORMAT))) + ")");
    }

}
