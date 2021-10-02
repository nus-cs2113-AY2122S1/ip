package Task;

/**
 * inherit Task class as a ToDo task
 * contains description of ToDo task and boolean isDone
 */
public class ToDo extends Task {
    protected boolean isDone;

    public ToDo(String description) {
        super(description);
        isDone = false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
