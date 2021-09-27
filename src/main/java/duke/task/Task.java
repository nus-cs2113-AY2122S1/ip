package duke.task;

import java.time.LocalDateTime;

/**
 * General task parent class.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates an incomplete task with description.
     */
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
