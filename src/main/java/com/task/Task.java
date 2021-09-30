package com.task;


/**
 * Represents a task with boolean isDone, true representing the task is done, false the otherwise.
 */
public class Task {
    String description;
    protected boolean isDone = false;


    /**
     * Constructor of the Task, stores the description.
     *
     * @param description String object representing the information about the task.
     */
    public Task (String description) {
        this.description = description;
    }


    public boolean isDone() {
        return this.isDone;
    }


    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }


    public String getDescription() {
        return description;
    }


    public String toString() {
        return description;
    }

}
