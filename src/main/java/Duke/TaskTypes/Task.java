package Duke.TaskTypes;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class
     *
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //Add Getter and Setter
    public String getDescription() {
        return description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getStatusIcon() {
        // mark done task with X
        return (this.isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" +getStatusIcon() + "] " + description;
    }

    public String getType() {
        return "";
    }
}
