package duke.task;

public class Deadline extends Task {

    /* Dateline of task */
    protected String by;

    /**
     * Constructor for deadline type task
     *
     * @param description Description of the deadline task processed by TaskManager
     * @param by          Date and time of deadline processed by TaskManager
     */
    public Deadline(String description, String by) {
        super(description, 'D');
        this.by = by;
    }

    /* Getter for deadline of deadline task */
    public String getBy() {
        return by;
    }

    /**
     * Formats description of deadline task type to be displayed to user
     *
     * @return Formatted string of an deadline task type
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", this.getBy());
    }
}