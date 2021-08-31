package main.java;

public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    public void finishTask() {
        isDone = true;
    }

    @Override
    public String toString() {
        return getTaskName();
    }
}
