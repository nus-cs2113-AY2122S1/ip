/**
 * An extension of the Task class.
 * Contains a description of the task.
 */
public class ToDo extends Task {

    /**
     * Constructs an instance of ToDo.
     *
     * @param description Description of the todo.
     */
    public ToDo(String description) {
        super(description);
        this.category = "T";
    }

    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        String categoryIcon = this.getCategoryIcon();
        return categoryIcon + statusIcon + " " + description;
    }

}