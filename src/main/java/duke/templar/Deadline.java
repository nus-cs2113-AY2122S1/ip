package duke.templar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Defines the type of task known as Deadline, with a description and a date
 */
public class Deadline extends Task
{
    protected LocalDateTime deadlineDate;


    public Deadline(String deadlineDescription, LocalDateTime deadlineDate)
    {
        super(deadlineDescription);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy h.mma")) + ") ";
    }
}
