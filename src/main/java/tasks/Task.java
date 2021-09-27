package tasks;

import java.time.LocalDate;
import java.time.LocalTime;

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

    // get Prefix
    public String getPrefix() {
        return "[ ]";
    }

    // date for each instance;
    public abstract LocalDate getTaskDate();

    // override tostring method
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