package duke.tasks;

public abstract class Task {
    protected String task;
    protected boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getTask() {
        return task;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setDone() {
        isDone = true;
    }
}
