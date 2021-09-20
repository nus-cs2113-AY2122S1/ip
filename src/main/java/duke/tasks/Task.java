package duke.tasks;

import java.time.LocalDateTime;

public abstract class Task {
    protected String task;
    protected boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }
    
    public abstract String toData(); 

    public String getTask() {
        return task;
    }
    
    public LocalDateTime getDT() {
        return null;
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
