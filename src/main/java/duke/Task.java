package duke;

public class Task {
    //attributes
    protected String description;
    protected boolean isDone;

    //constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //getters
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    //setters
    public void markAsDone() {
        isDone = true;
    }

    //Returns description of task added
    public String toString() {
        return description;
    }

    public String toSave() {
        int done = 0;
        if (this.isDone) {
            done = 1;
        }
        return "taskType" + description + " | " + done;
    }
}

