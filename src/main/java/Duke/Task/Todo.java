package Duke.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the icon, status and task name of the Todo task in a single string.
     *
     * @return the icon, status and task name of the Todo task in a single string.
     */
    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + super.toString();
    }
}
