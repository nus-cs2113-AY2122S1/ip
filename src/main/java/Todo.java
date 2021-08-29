public class Todo extends Task {
    protected String taskType;

    public Todo (String taskDescription) {
        super(taskDescription);
        this.taskType = "T";
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
                + this.taskDescription);
    }
}
