package duke.task;

public class Task {

    private String description;
    private static int numberOfTasks = 0;
    private boolean isDone;

    public Task(String description) {
        isDone = false;
        setDescription(description);
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }


    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

    public void setDescription(String description) {
        this.description = description;
        numberOfTasks++;
    }

    public boolean getisDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }
}
