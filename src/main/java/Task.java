public class Task {
    protected String description;
    protected String deadline;
    protected String taskType;
    protected boolean isDone;

    public Task(String description, String deadline, String taskType) {
        this.description = description;
        this.deadline = deadline;
        this.taskType = taskType;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    public String getDeadline() {
        return this.deadline;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTaskType() {
        return taskType;
    }

    /* mark as done */
    public void markAsDone() {
        this.isDone = true;
    }
}
