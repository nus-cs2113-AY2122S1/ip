package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate event;

    public Event(String description, int index, String event) {
        super(description, index);
        this.type = Type.E;
        this.event = LocalDate.parse(event);
    }
    public void printTask(){
        System.out.println(index + ". [" + type + "] [" + getStatusIcon()
                + "] " + description + " (at: " + event.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")");
    }

    public String fileFormat() {
        return (type + " | " + getStatusIcon() + " | " + description + " | " + event);
    }
}