package duke.tasklist.task;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * @param description The description of the task given by the user
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {

        return (isDone ? "X" : " ");
    }
    public String getDescription() {

        return this.description;
    }
    public String getStatus() {
        return (isDone ? "1" : "0");
    }
    public String getDate() {
        return "empty";
    }

    public LocalDate getDeadline() {
        return null;
    }
    public void markDone() {

        this.isDone = true;
    }
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
