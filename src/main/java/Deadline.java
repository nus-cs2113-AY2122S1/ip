/**
 * An extension of the Task class.
 * Contains a description of the task as well as the details the task's deadline.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs an instance of Deadline.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
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