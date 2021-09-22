package Type.task;

import Type.Serializable;

public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone = false;

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

}
