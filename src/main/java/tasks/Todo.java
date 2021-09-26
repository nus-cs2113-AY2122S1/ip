package tasks;

public class Todo extends Task {
    protected boolean isDone;

    public Todo(String description) {
        super(description);
        this.isDone = false;
    }

    /**
     * Returns the task information in String format.
     *
     * @return Task information.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
