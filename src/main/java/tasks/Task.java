package tasks;

import java.io.IOException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + getDescription();
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "] ";
    }

    public String getDescription() {
        return description;
    }

    public Boolean getDone() {
        return isDone;
    }

    public abstract String getClassType();

    public void setIsDone() {
        isDone = true;
    }

    public void setIsFalse() {
        isDone = false;
    }

    public abstract void saveTask(String filePath) throws IOException;
}
