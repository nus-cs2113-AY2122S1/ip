package xRoss.task;

import xRoss.exception.EmptyStringException;

public abstract class Task {

    protected String name;
    protected boolean isDone;

    public Task(String name) throws EmptyStringException {
        if (name.isEmpty()){
            throw new EmptyStringException();
        }

        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    public abstract void printTask();

    public String toString(){
        return " | " + (isDone()? "1": "0")
                + " | " + getName();
    }
}
