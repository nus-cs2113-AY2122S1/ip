public class Task {
    private String taskDescription;
    private boolean isDone;

    public Task(String description) {
        this.taskDescription = description;
        this.isDone = false;
    }

    public void markTaskDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone == true) ? "X" : " ";
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}
