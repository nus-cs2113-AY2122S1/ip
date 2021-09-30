package com.task;

import com.time.Time;

public class Event extends Todo{
    protected Time by;

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
