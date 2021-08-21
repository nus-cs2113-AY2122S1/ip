public class Task {
    protected String taskname;
    protected boolean isDone;

    // Constructor
    public Task(String taskname) {
        this.taskname = taskname;
        this.isDone = false;
    }

    // Getters and Setters
    public String getTaskname() {
        return taskname;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    // Function to get StatusIcon based on isDone
    public String getStatusIcon() {
        return (isDone() ? "X" : " ");
    }
}
