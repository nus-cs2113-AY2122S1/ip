package duke.task;

import duke.parser.Parser;
import duke.exception.DukeInvalidAddTaskException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private static final String DEADLINE_TIME_KEYWORD = " /by";
    
    private LocalDate deadlineDate;

    /**
     * Constructor of deadline objects by first initializing a task object
     * then the deadlineDate of this object.
     * 
     * @param description task description from user's input, containing the task
     *                    description and deadline date.
     * @throws DukeInvalidAddTaskException if task description contains "--", which
     * is a format reserved for only when saving the task to the text file.
     */
    public Deadline(String description) throws DukeInvalidAddTaskException {
        super(Parser.getDescription(description, DEADLINE_TIME_KEYWORD));
        try {
            this.deadlineDate = Parser.getTime(description, DEADLINE_TIME_KEYWORD);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidAddTaskException();
        }
    }

    /**
     * Return the date of this deadline.
     * 
     * @return the deadline date.
     */
    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    /**
     * Return this deadline's representation as a string.
     * 
     * @return string representation.
     */
    public String toString() {
        String formattedDate = deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
