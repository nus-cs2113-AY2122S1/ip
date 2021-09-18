package duke.task;

import duke.parser.Parser;
import duke.exception.DukeInvalidAddTaskException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private static final String DEADLINE_TIME_KEYWORD = " /by";
    
    private LocalDate deadlineDate;

    public Deadline(String description) throws DukeInvalidAddTaskException {
        super(Parser.getDescription(description, DEADLINE_TIME_KEYWORD));
        try {
            this.deadlineDate = Parser.getTime(description, DEADLINE_TIME_KEYWORD);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidAddTaskException();
        }
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public String toString() {
        String formattedDate = deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
