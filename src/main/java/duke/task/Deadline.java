package duke.task;


public class Deadline extends Task {
    private static final TaskType taskType = TaskType.DEADLINE;
    private final String dueDate;

    /**
     * Deadline constructor
     *
     * @param title Title of deadline
     * @param dueDate Due date of deadline
     */
    public Deadline(String title, String dueDate) {
        super(title);
        this.dueDate = dueDate;
    }

    /**
     * @return Type of Task
     */
    @Override
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * @return String representation of Deadline for display
     */
    @Override
    public String toString() {
        String SYMBOL = "D";
        return "[" + SYMBOL + "]" + super.toString() + " (by: " + dueDate + ")";
    }

    /**
     * @return Due date of Deadline
     */
    @Override
    public String getTime() {
        return dueDate;
    }
}
