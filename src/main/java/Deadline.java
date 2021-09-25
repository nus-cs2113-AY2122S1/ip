package ip.src.main.java;

/**
 * Represents a Deadline which is an extension of the Task class. A <code>Deadline</code> corresponds to
 * a Task represented by a description and date e.g., <code>Buy milk by 3pm</code>
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Adding a Deadline.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     * @throws DukeException If description is empty.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
    }

    /**
     * Returning the description of a Deadline.
     * @return description of a Deadline.
     */
    @Override
    public String description() {
        return description + " (by: " + by + ")";
    }

}