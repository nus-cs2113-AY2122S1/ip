package Duke.Task;

/**
 * Represents the attributes of all the Tasks stored in Duke
 * It is the parent object for ToDos, Deadline and Events
 */

public class Task {
    protected boolean isDone;
    protected String description;
    private int id;
    private static int totalTasks = 0;
    private String taskIcon;

    public Task(String description) {
        this.description = description;
        this.isDone = false;

        totalTasks++;
        id = totalTasks;
    }

    public int getID() {
        return id;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTaskIcon() {
        return taskIcon;
    }

    public String getDescription(){
        return description;
    }

    public String getOriginalDescription() { return description;}

}
