package duke.task;

public class Task {
    private final String content; //need to change?
    private Boolean isDone;

    public Task(String content) {
        this.content = content;
        isDone = false;
    }

    public String TaskStatus() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getContent() {
        return this.content;
    }

    public Boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + this.TaskStatus() + "] " + this.content;
    }
}

