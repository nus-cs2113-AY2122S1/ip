public class Task {

    private String taskDetails;
    private static int numberOfTasks = 0;
    private boolean isDone;

    public Task(String taskDetails) {
        isDone = false;
        setTaskDetails(taskDetails);
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
        numberOfTasks++;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }
}
