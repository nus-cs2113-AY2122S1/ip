package com.task;

/**
 * Represents an event with deadline by, inherent from class Todo.
 */
public class Event extends Todo{
    protected String by;
    /**
     * Constructor of the Event, store the description and deadline time.
     *
     * @param description String object representing the information about the task.
     * @param ddl String object representing the time of the object.
     */
    public Event(String description, String ddl) {
        super(description);
        this.isDone = false;
        this.by = ddl;
        this.type= "[E]";
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
