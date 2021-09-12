package duke.task;

public class Todo extends Task {
    protected final String TASK_TYPE = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString();
    }
}
