package com.task;

import com.time.Time;

public class Deadline extends Todo {
    protected Time by;

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