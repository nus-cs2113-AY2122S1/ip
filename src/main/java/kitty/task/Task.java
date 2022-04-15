package kitty.task;

/**
 * The class <code>Task</code> includes methods that involves any form of tasks.
 */
public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String name) {
        this.taskName = name;
        this.isDone = false;
    }
    //Getters
    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    //Setters
    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}
