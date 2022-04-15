package com.task;
import com.time.Time;


/**
 * Represents a task with deadline by.
 */
public class Deadline extends Todo {
    protected Time by;


    /**
     * Constructor of the Deadline, stores the description and deadline time.
     *
     * @param description String object representing the information about the task.
     * @param ddl String object representing the time of the object.
     */
    public Deadline(String description, String ddl) {
        super(description);
        this.isDone = false;
        this.by = new Time(ddl);
        this.type= "[D]";
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