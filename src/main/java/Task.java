public class Task {
    protected String description;
    protected String taskType;
    protected boolean isDone;
    protected String eventDate;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "";
        this.eventDate = "";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getType() {
        return taskType;
    }

    public String getWhen() {
        return eventDate;
    }

}
