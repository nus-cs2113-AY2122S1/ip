public class Task {
    private String taskName;
    private Boolean isCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
        printAddTaskLine();
        System.out.println("Task Added: " + this.taskName + "\n");
        printAddTaskLine();
    }

    public static void printAddTaskLine() {
        System.out.println("********************************");
    }

    public void markTaskCompleted() {
        isCompleted = true;
    }

    public String getTask() {
        return taskName;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }
}
