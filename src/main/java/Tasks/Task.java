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

    public void markAsDone() {
        this.isTaskDone = true;
        System.out.print("This task is done homie:\n");
        this.describe();
    }

    protected String getStatusIcon() {
        return (isTaskDone ? "X" : " ");
    }

    public void describe() {
        System.out.println( "[" + this.getStatusIcon() + "] "
                + this.taskDescription);
    }

}
