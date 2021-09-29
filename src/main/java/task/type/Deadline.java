package task.type;

public class Deadline extends Task {
    protected String by;
    public static final String type = "D";

    /**
     * Constructor.
     *
     * @param description description of the task.
     * @param by deadline to complete task by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns deadline to complete task by
     *
     * @return by which is the deadline
     */
    public String getBy(){
        return by;
    }

    /**
     * Prints the deadline task in the format
     *
     * @return deadline task in the correct format to be printed.
     */
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns type of task
     *
     * @return 'D' which is the task type for Deadline
     */
    @Override
    public String getTaskType() {
        return type;
    }
}