package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(int status, String description) {
        this.description = description;
        if (status == 1) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getStatusNumber() {
        return (isDone ? "1" : "0"); // mark done task with 1
    }

    public String getTime() {
        return "";
    };

    public String getType() {
        return "";
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
