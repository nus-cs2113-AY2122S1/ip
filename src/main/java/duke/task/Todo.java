package duke.task;

/**
 * Represents a Task item containing only description
 */
public class Todo extends Task {

    public Todo(String description) {
        this(description, false);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Get the string describing the status of the todo task
     * 
     * @return String formatted by todo, completion status, description
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), this.description);
    }

    /**
     * Get the string format of todo task to be saved
     * 
     * @return String formatted by todo, description, completion status
     */
    @Override
    public String toSave() {
        return String.format("todo | %s | %b", this.description, this.isDone);
    }
}
