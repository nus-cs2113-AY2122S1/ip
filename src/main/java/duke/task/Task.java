package duke.task;

public abstract class Task {

    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        if (isDone()) {
            return "[✓] " + taskName;
        }
        else {
            return "[ ] " + taskName;
        }
    }
}
