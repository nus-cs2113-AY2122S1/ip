package duke;

abstract public class Task {

    protected String description;
    protected boolean isDone;

    private static int numOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    
    public void setDone() {
        this.isDone = true;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public abstract String taskString();
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            @SuppressWarnings("unchecked")
            Task t = (Task) obj;
            return this.description.equals(t.description) && this.isDone == (t.isDone);
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

