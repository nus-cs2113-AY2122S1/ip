package duke;

/**
 * Represents a task
 * A task object stores 2 characteristics: a description, and whether it is completed
 * Task class is inherited by 3 other classes Todos, Events, and Deadlines class
 */
public class Task {

    //variables
    protected String description;
    private boolean isDone;

    //constructor
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    //getters
    public String getDescription() {
        return description;
    }
    public boolean getDone() {
        return isDone;
    }

    //setters
    public void setDone() {
        this.isDone = true;
    }

    //other methods
    /**
     * @return String representation of the Task in the format [ ] description
     */
    @Override
    public String toString() {
        if(getDone()) {
            return ("[X] " + description);
        } else {
            return ("[ ] " + description);
        }
    }



}
