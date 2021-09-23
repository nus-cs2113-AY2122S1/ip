package duke.task;

import java.time.LocalDateTime;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void setDone() {
        isDone = true;
    }

    public String getType() {
        return null;
    }

    public LocalDateTime getByDateTime() { return null; }

    public LocalDateTime getStartTime() { return null; }

    public LocalDateTime getEndTime() { return null; }
}
