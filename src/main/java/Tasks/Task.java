package Tasks;

public class Task {
    protected boolean isTaskDone;
    protected String taskDescription;

    // Class Level Members
    public static int numberOfTasks = 0;

    public Task (String taskDescription) {
        this.taskDescription = taskDescription;
        this.isTaskDone = false;
        numberOfTasks += 1;
    }

    public String getTaskDescription (Task task) {
        return (task.taskDescription);
    }

    public void markAsDone() {
        this.isTaskDone = true;
    }

    protected String getStatusIcon() {
        return (isTaskDone ? "X" : " ");
    }

    public void describePrint() {
        System.out.println( "[" + this.getStatusIcon() + "] "
                + this.taskDescription);
    }

    public String describeString() {
        return ("[" + this.getStatusIcon() + "] "
                + this.taskDescription);
    }
}
