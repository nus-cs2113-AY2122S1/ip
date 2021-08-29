public class Task {
    protected boolean isTaskDone;
    protected String taskDescription;

    // Class Level Members
    protected static int numberOfTasks = 0;

    public Task (String taskDescription) {
        this.taskDescription = taskDescription;
        this.isTaskDone = false;
        numberOfTasks += 1;
    }

    protected void markAsDone() {
        this.isTaskDone = true;
        System.out.println("This task is done homie:\n"
                + "[" + this.getStatusIcon() + "] "
                + this.taskDescription);
    }

    protected String getStatusIcon() {
        return (isTaskDone ? "X" : " ");
    }

    public void describe() {
        System.out.println( "[" + this.getStatusIcon() + "] "
                + this.taskDescription);
    }

}
