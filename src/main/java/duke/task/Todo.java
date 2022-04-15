package duke.task;

public class Todo extends Task {
    public Todo(String description, TaskType type) {
        super(description, type);
        this.type = TaskType.TODO;
    }

    public Todo(String description, TaskType type, boolean isDone) {
        super(description, type);
        this.type = TaskType.TODO;
        this.isDone = isDone;
    }

    @Override
    public String getFileStringFormat() {
        return String.format("T | %s", super.getFileStringFormat());
    }
}
