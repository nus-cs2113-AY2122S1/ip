package duke.data.task;

import duke.data.exception.InvalidDateInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Event extends Task {

    protected LocalDate at;

    public Event(String description, String at) throws InvalidDateInputException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new InvalidDateInputException(at);
        }
    }


    @Override
    public String getType() {
        return "E";
    }

    public String toString() {
        String timeFormatted = at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (at: " + timeFormatted + ")";
    }

    public String store() {
        int statusStored = isDone ? 1 : 0;
        String timeFormatted = at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "E | " + statusStored + " | " +
                super.getDescription() + " | " + timeFormatted;
    }
}
