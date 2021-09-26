package duke.tasks;

/**
 * Task is a super class representing a generic task. Its sub-classes are: Todo, Event, Deadline
 * A Task object is represented by a description and a status of whether the task is done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

    public String toFileFormat() {
        return isDone + "|" + description;
    }


}
