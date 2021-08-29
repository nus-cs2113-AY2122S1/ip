public class Event extends Task {
    protected String taskType;
    protected String taskTime;

    public Event (String taskDescription, String taskTime) {
        super(taskDescription);
        this.taskType = "E";
        this.taskTime = taskTime;
    }

    @Override
    protected void markAsDone() {
        this.isTaskDone = true;
        System.out.println("This task is done homie:\n"
                + "[" + this.taskType +"]"
                + "[" + this.getStatusIcon() + "] "
                + this.taskDescription);
    }

    @Override
    public void describe() {
        System.out.println( "[" + this.taskType + "]"
                + "[" + this.getStatusIcon() + "] "
                + this.taskDescription
                + "(at:"
                + (this.taskTime.replaceAll("at", "")) + ")");
    }
}
