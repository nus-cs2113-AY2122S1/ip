package shima.task;

public abstract class Task {
    protected String task;
    protected boolean isDone;

    //Constructor
    public Task() {
        this.task = "";
        this.isDone = false;
    }

    public abstract String getTime();
    public abstract String getClassType();

    //getters and setters
    public String getTask() {
        return task;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }
}
