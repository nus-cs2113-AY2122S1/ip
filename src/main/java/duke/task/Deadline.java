package duke.task;

/**
 * Represents a Task item containing description and the latest time for the task to be completed
 */
public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        this(description, by, false);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Get the string describing the status of the deadline task
     * 
     * @return String formatted by deadline, completion status, description, by
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), this.description, this.by);
    }

    /**
     * Get the string format of deadline task to be saved
     * 
     * @return String formatted by deadline, description, completion status, by
     */
    @Override
    public String toSave() {
        return String.format("deadline | %s | %b | %s", this.description, this.isDone, this.by);
    }
}
