package task;

import task.Task;

public class Todo extends Task {
    public Todo(String taskname) {
        super(taskname);
    }

    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
