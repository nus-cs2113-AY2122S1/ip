package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected char taskType;

    public Task(String description, char taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getWrittenIcon() {
        return (isDone ? "1" : "0");
    }

    public char getTaskType() {
        return taskType;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return description;
    }

    public String toFileFormat() {
        return description;
    }
}
