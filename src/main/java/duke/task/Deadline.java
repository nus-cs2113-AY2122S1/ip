package duke.task;
import java.time.LocalDate;

public class Deadline extends Task {
    LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        setBy(by);
    }

    /*
     * Get the deadline of the task
     * @return The deadline
     */
    public LocalDate getBy() {
        return by;
    }

    /*
     * Set the deadline of the task
     * @param The new deadline to be set
     */
    public void setBy(LocalDate by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + getBy().format(formatter) + ")";
    }

    @Override
    protected String getType() {
        return "deadline";
    }
}
