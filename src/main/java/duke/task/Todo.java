package duke.task;

public class Todo extends Task {
    private static final TaskType taskType = TaskType.TODO;

    public Todo(String name) {
        super(name);
    }

    @Override
    public TaskType getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        String SYMBOL = "T";
        return "[" + SYMBOL + "]" + super.toString();
    }

    @Override
    public String getTime() {
        return null;
    }
}
