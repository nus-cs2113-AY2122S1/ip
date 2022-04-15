package duke.tasks;

import java.time.LocalDateTime;

/**
 * Task is a super class representing a generic task. Its sub-classes are: Todo, Event, Deadline
 * A Task object is represented by a description and a status of whether the task is done.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime dateTime;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.dateTime = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

    public String toFileFormat() {
        return isDone + "|" + description;
    }
}
