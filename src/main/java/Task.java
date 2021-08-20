public class Task {
    private String taskName;
    private Boolean isCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
        isCompleted = false;
        Display.printAddTaskLine();
        System.out.println("Task Added: " + taskName + "\n");
        Display.printAddTaskLine();
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
}
