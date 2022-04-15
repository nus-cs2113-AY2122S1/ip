package com.task;

import com.time.Time;

/**
 * Represents an event with deadline by, inherent from class Todo.
 */

public class Event extends Todo{

    protected Time by;

    /**
     * Constructor of the Event, stores the description and deadline time.
     *
     * @param description String object representing the information about the task.
     * @param ddl String object representing the time of the object.
     */
    public Event(String description, String ddl) {
        super(description);
        this.isDone = false;
        this.by = new Time(ddl);
        this.type= "[E]";
    }


    public String getBy() {
        return this.by.toString();
    }


    public void setBy(String ddl) {
        this.by = new Time(ddl);
    }


    public String toString() {
        return super.toString() + "(" + this.by.toString() + ")";
    }
}
