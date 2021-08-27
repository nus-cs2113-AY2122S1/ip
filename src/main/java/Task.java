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

    public String getStatusCheckbox() {
        String statusCheckbox = Display.createCheckboxDisplay(Display.TASK_INCOMPLETE);
        if (isCompleted) {
            statusCheckbox = Display.createCheckboxDisplay(Display.TASK_COMPLETE);
        }
        return statusCheckbox;
    }

    @Override
    public String toString() {
        return taskName;
    }
}
