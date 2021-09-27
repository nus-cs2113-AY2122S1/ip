package duke.task;

import duke.parser.Parser;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructor to create a Deadline task.
     * Constructor takes in the description and time when the deadline is due.
     * @param description Description of the deadline type task.
     * @param by Date when the deadline is due.
     */
    public Deadline(String description, String by) {
        super(description, "D");
        setBy(by);
    }


    /**
     * Retrieves the date of the deadline.
     * @return String representing the deadline.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Sets the deadline of the task
     * @param deadline Date of the deadline
     */
    public void setBy(String deadline) {
        this.by = Parser.parseLocalDate(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.formatLocalDate(by) + ")";
    }
}
