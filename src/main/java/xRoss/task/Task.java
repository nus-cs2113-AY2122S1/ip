package xRoss.task;

import xRoss.exception.EmptyStringException;

/**
 * Abstract class used to implement Task subclasses.
 */
public abstract class Task {

    /**
     * name     Task description
     * isDone   Boolean value denotes whether Task has been done
     *
     * Protected access to be inherited by subclasses.
     */
    protected String name;
    protected boolean isDone;

    /**
     * Constructor for Task instance.
     *
     * @param name  Task description.
     * @throws EmptyStringException Exception thrown if "name" param is an empty string.
     */
    public Task(String name) throws EmptyStringException {
        if (name.isEmpty()){
            throw new EmptyStringException();
        }

        this.name = name;
        this.isDone = false;
    }

    /**Getters and Setters for name and isDone variables*/

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

    /**
     * Abstract method to be implemented by Task subclasses.
     */
    public abstract void printTask();

    /**
     * Converts Task instance to its String representation.
     *
     * @return  String representation of Task instance.
     */
    public String toString(){
        return " | " + (isDone()? "1": "0")
                + " | " + getName();
    }
}
