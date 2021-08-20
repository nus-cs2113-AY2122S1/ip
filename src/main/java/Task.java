public class Task {
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
        System.out.println("added: " + this.taskName);
    }

    public String getTask() {
        return taskName;
    }
}
