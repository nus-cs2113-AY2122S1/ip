public class Task {

    private String taskDetails;
    private static int numberOfTasks = 0;

    public Task(String taskDetails) {
        setTaskDetails(taskDetails);
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
        numberOfTasks++;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }
}
