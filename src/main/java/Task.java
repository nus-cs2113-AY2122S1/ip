public class Task {
    private String taskName;
    private boolean isDone;

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
}
