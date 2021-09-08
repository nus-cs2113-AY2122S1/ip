package duke.task;

public class Deadline extends Task {

    protected String by;

    /**
     * Constructor to initialise the description
     * and the deadline of the task.
     *
     * @param description string with the task
     *                    description
     * @param by          deadline for the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the formatted Description of the task.
     *
     * @return returns a String with the deadline task description
     */
    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + " (by: " + by + ")";
    }
}
