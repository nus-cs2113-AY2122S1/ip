package duke.processes.tasks;

import java.time.LocalDateTime;

public class Task {
    public String description;
    public boolean isDone;
    public LocalDateTime date;

    public Task(String description, LocalDateTime date) {
        this.description = description;
        this.date = date;
        this.isDone = false;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void undo() {
        this.isDone = false;
    }

    public LocalDateTime getDateValue() { return date; }

    public String getTaskType() {
        return getTaskType();
    }

    public String getTaskID() {
        return getTaskID();
    }

    public String getDate() {
        return getDate();
    }

}
