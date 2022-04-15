package task;

/**
 * An extension of the task.Task class.
 * Contains a description of the task as well as the details the task's deadline.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs an instance of task.Deadline.
     *
     * @param description Description of the task.
     * @param by task.Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.category = "D";
    }

    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        String categoryIcon = this.getCategoryIcon();
        return categoryIcon + statusIcon + " " + description + " (by: " + by + ")";
    }

}