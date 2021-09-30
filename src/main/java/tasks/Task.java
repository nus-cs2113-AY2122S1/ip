package tasks;

import java.time.LocalDate;

/**
 * Parent class representing a Task user stores.
 * Contains taskName and isDone flag indicating whether a task is done or not
 */
public abstract class Task {
    protected boolean isDone;
    protected String taskName;

    // constructors
    public Task(boolean isDone, String taskName) {
        this.isDone = isDone;
        this.taskName = taskName;
    }

    public Task() {
        this(false, "Nothing");
    }

    // getters
    public boolean isDone() {
        return isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    // setters
    public void setDone(boolean done) {
        isDone = done;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    // get Prefix for child classes
    public abstract String getPrefix();

    // date for each instance;
    public abstract LocalDate getTaskDate();

    // override toString method
    @Override
    public String toString() {
        System.out.print(">");
        String checkbox = "[ ]";
        if (isDone()) {
            checkbox = "[X]";
        }
        return checkbox + getTaskName();
    }
}