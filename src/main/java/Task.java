abstract class Task {
    private String taskName;
    private boolean isCompleted;


    public Task (String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    private String outputTaskStatus () {
        return "[" + (isCompleted ? "X" : " ") + "] ";
    }


    @Override
    public String toString () {
        return this.outputTaskStatus() + this.taskName;
    }

    public void setCompleted () {
        this.isCompleted = true;
    }
}