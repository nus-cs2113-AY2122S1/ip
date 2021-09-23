package alfred.task;

import java.time.LocalDate;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public abstract String getType();

    public abstract LocalDate getDate();

    @Override
    public String toString() {
        return "[" + getStatusIcon() +"] " + description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setTaskDone() {
        this.isDone = true;
    }
}
