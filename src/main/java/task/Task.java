package task;

public class Task {

    private String taskName;
    private Boolean isCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
        isCompleted = false;
    }

    public String getTask() {
        return taskName;
    }

    public void setTaskCompleted() {
        isCompleted = true;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    @Override
    public String toString() {
        return taskName;
    }
}
