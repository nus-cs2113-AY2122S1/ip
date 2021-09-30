package com.task;

/**
 * Represents a task with deadline by.
 */
public class Deadline extends Todo {
    protected String by;

    /**
     * Constructor of the Deadline, store the description and deadline time.
     *
     * @param description String object representing the information about the task.
     * @param ddl String object representing the time of the object.
     */
    public Deadline(String description, String ddl) {
        super(description);
        this.isDone = false;
        this.by = ddl;
        this.type= "[D]";
    }


    public String getBy() {
        return this.by;
    }


    public void setBy(String ddl) {
        this.by = ddl;
    }


    public String toString() {
        return super.toString() + "(" + this.by + ")";
    }
}