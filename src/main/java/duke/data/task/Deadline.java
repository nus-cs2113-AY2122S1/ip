package duke.data.task;

import duke.data.exception.InvalidDateInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) throws InvalidDateInputException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new InvalidDateInputException(by);
        }
    }

    public String getType() {
        return "D";
    }


    @Override
    public String toString() {
        String timeFormatted = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + timeFormatted + ")";
    }

    public String store() {
        //1 for tasks done, 0 for tasks not done
        int statusStored = isDone ? 1 : 0;
        String timeFormatted = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "D | " + statusStored + " | " +
                super.getDescription() + " | " + timeFormatted;
    }

}
