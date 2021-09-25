package duke.task;

import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getType(){
        return (" ");
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public String getOriginalDescription() {
        return description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public LocalDateTime getDateTime() {
        return null;
    }
}
