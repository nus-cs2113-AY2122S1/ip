public class Task {
    private String description;
    private boolean isDone;

    private static int numberOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        String statusIcon;
        if (isDone) {
            statusIcon = "X";
        } else {
            statusIcon = " ";
        }
        return "[" + statusIcon + "] " + description;
    }
}
