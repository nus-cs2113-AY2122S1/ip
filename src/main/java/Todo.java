/**
 * Child class ToDo
 */
public class Todo extends Task{
    protected String type = "T";

    /**
     *
     * @param description Description of the task
     */
    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[" + this.type + "]" + "[" + this.done + "] " + this.description;
    }
}
