package duke.task;

public abstract class Task {
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

    /**
     * Displays an icon showing whether a task has been marked as completed.
     *
     * @return String depicting the completion status of the task.
     */
    public String getStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    public void finishTask() {
        isDone = true;
    }

    public abstract String exportTask();

    @Override
    public String toString() {
        return getTaskName();
    }
}
