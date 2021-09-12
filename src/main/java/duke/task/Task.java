package duke.task;

public class Task {
    protected String description;
    protected String type;
    protected boolean isDone;

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

}
