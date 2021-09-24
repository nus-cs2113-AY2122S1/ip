package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class representing a Deadline task
 */
public class Deadline extends Task{
    protected String deadline;
    protected LocalDate deadlineDate;

    /**
     * Constructor for a Deadline task initialises the Deadline task's description and deadline
     * @param description Description of the task
     * @param deadlineDate Deadline of the task in LocalDate type
     */
    public Deadline(String description, LocalDate deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
        deadline = deadlineDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Gets the current Deadline task's description
     * @return Current task's deadline
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Converts the current Deadline task into a String
     * @return Deadline task in the form of a String
     */
    @Override
    public String toString() {
        if (description.contains("|")) {
            description = description.replace("|", ",");
        }
        return "[D]" + getStatusIcon() + description + " (by: " + deadline + ")";
    }

    /**
     * Converts the current Deadline task object into a format that can be stored in a CSV file.
     * @return Deadline task in the form of a comma separated value
     */
    @Override
    public String convertToCSV() {
        if (description.contains(",")) {
            description = description.replace(",", "|");
        }
        return ("D," + getStatusIcon() + "," + description + "," + deadlineDate);
    }
}
