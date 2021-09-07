package kitty.task;

public class Task {
    private String taskName;
    private boolean isDone;
    public static int totalTasksCount = 0;

    //Constructor
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
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

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
