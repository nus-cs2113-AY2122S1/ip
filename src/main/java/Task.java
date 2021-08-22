public class Task {
    protected String taskDescription;
    protected boolean isTaskDone;

    public Task (String taskDescription) {
        this.taskDescription = taskDescription;
        this.isTaskDone = false;
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

}
