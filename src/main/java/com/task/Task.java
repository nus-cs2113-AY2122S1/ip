package com.task;

public class Task {
    String description;
    protected boolean isDone = false;

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
