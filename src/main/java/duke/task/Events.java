package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    private final LocalDate eventDate;

    public Events(String taskName, LocalDate eventDate) {
        super(taskName);
        this.eventDate = eventDate;
    }

    public String getDate() {
        return eventDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getDate() + ")";
    }

    @Override
    public String storageText () {
        return EVENT_E + super.storageText() + "|" + getDate();
    }
}
