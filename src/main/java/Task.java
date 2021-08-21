public class Task {
    private String taskName;
    private boolean isCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
        isCompleted = false;
    }

    public void setCompleted() {
        isCompleted = true;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void printTask() {
        String checkbox;
        if (this.isCompleted) {
            checkbox = "[X]";
        } else {
            checkbox = "[ ]";
        }
        System.out.println(checkbox + " " + taskName);
    }
}
