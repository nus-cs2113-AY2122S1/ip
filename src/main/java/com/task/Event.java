package com.task;

public class Event extends Todo{
    protected String by;

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
