package duke;

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
    @Override
    public String toString() {
        if(getDone()) {
            return ("[X] " + description);
        } else {
            return ("[ ] " + description);
        }
    }



}
