package duke.task;

import java.time.LocalDate;

public class Deadline extends Task {

    /**
     * Date and time of deadline.
     */
    private LocalDate by;

    /**
     * Create a new Deadline task.
     *
     * @param description String description of the Deadline.
     * @param by          LocalDate object of the due date.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        super.setType("D");
        this.by = by;
    }

    /**
     * Returns the description of the deadline, along with the due date.
     *
     * @return String description of the Deadline task with due date.
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + getBy() + ")";
    }

    /**
     * Returns the due date.
     *
     * @return LocalDate object of the due date.
     */
    public LocalDate getBy() {
        return by;
    }
}
