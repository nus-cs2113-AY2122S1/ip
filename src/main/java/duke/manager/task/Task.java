package duke.manager.task;

public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String description) {
        this.taskDescription = description;
        isDone = false;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getTaskDescriptionWithStatus() {
        // mark done task with X
        return (isDone ? "[X] " : "[ ] ") + taskDescription;
    }

    public void setDone() {
        isDone = true;
    }
}
