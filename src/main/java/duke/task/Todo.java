package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description, Task.TYPE_TODO);
    }

    @Override
    public String toFileString() {
        return String.format("%c | %d | %s", taskType, (isDone) ? 1 : 0, description);
    }
}
