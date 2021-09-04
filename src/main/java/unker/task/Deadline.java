package unker.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    /**
     * Gets the due date of the task.
     * 
     * @return The due date of the task.
     */
    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.by);
    }
}
