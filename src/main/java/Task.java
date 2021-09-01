public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDoneStatus() {
        if (isDone) {
            return "x";
        }
        return " ";
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    @Override
    public String toString() {
        return "[" + getDoneStatus() + "] " + description;
    }
}

