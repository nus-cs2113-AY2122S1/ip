package duke.task;


public class Deadline extends Task {
    private static final TaskType taskType = TaskType.DEADLINE;
    private final String dueDate;

    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    @Override
    public TaskType getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        String SYMBOL = "D";
        return "[" + SYMBOL + "]" + super.toString() + " (by: " + dueDate + ")";
    }

    @Override
    public String getTime() {
        return dueDate;
    }
}
