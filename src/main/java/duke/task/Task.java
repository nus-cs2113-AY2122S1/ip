package duke.task;

public class Task {
    private final String taskName; //need to change?
    private Boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    public String TaskStatus() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public Boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + this.TaskStatus() + "] " + this.getTaskName();
    }
}

