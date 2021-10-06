package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the symbol representing Todo tasks.
     *
     * @return Returns the character "T"
     */
    public String getTypeIcon() {
        return "T";
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return Returns the task as a String
     */
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}