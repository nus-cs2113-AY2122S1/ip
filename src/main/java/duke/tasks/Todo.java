package duke.tasks;

/**
 * Todo is a Sub-class that inherits from Task Class
 * A Todo object is represented by a description of the task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T|" + super.toFileFormat();
    }
}
