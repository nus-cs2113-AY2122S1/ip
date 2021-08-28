public class Task {
    private String description;
    private boolean isDone;

    private static int numberOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public String getTaskType() {
        return null;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
